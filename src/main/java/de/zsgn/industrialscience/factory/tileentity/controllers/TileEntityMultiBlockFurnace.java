package de.zsgn.industrialscience.factory.tileentity.controllers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntityFurnace;

import org.apache.commons.lang3.ArrayUtils;

import de.zsgn.industrialscience.factory.SmeltingRegristry;
import de.zsgn.industrialscience.util.AbsoluteCoordinate;
import de.zsgn.industrialscience.util.RelativeCoordinate;

public class TileEntityMultiBlockFurnace extends
ITileEntityMultiBlockMachine implements IThermometerSupport,IChimneySupport {
    public static final int INPUTSLOT = 0;
    public static final int FUELSLOT = 1;
    public static final int OUTPUTSLOT = 2;
    public static final int DEFCOOKTIME=300;
    protected AbsoluteCoordinate[] chimneys={};
    protected int deftemperature = 20;
    protected float temperature = 20;
    protected int currenfuelburntime = 0;

    public TileEntityMultiBlockFurnace(int deftemperature,
            RelativeCoordinate[] itemhatchcoords,
            RelativeCoordinate[] interfacehatchcoords) {
        super(itemhatchcoords, interfacehatchcoords, 3);
        this.deftemperature = deftemperature;
        temperature = deftemperature;
    }


    public TileEntityMultiBlockFurnace() {
        super();
    }

    @Override
    public void updateEntity() {
        if(!worldObj.isRemote){
            burnFuel();
            super.updateEntity();
            updateChimneys();
        }
    }

    protected void updateChimneys() {
        for (AbsoluteCoordinate coordinate : chimneys) {
            if ((worldObj.getBlockMetadata(coordinate.xCoord, coordinate.yCoord, coordinate.zCoord) & 1) != (this
                    .isProcessing() ? 1 : 0)) {
                int newmeta = worldObj.getBlockMetadata(coordinate.xCoord, coordinate.yCoord, coordinate.zCoord) >> 1 << 1
                        | (this.isProcessing() ? 1 : 0);
                worldObj.setBlockMetadataWithNotify(coordinate.xCoord, coordinate.yCoord, coordinate.zCoord,
                        newmeta, 2);
            }
        }

    }


    protected void burnFuel() {
        if (currenfuelburntime > 0) {
            currenfuelburntime--;
            temperature = temperature + 0.025F;
        } else if (temperature > deftemperature) {
            temperature = temperature - (activepart ? 0.05F : 0.1F);
        }
        if (currenfuelburntime == 0 && inventory[FUELSLOT] != null&&activepart) {
            currenfuelburntime = this.getItemBurnTime(inventory[FUELSLOT]);
            inventory[FUELSLOT].stackSize--;
            if (inventory[FUELSLOT].stackSize == 0) {
                inventory[FUELSLOT] = null;
            }
        }
    }


    protected void doOperation() {
        if (this.canDoOperation()) {
            ItemStack result = SmeltingRegristry
                    .getSmeltingResult(inventory[INPUTSLOT]);
            if (inventory[OUTPUTSLOT] == null) {
                inventory[OUTPUTSLOT] = result.copy();
            } else if (inventory[OUTPUTSLOT].getItem() == result.getItem()) {
                inventory[OUTPUTSLOT].stackSize += result.stackSize;
            }
            inventory[INPUTSLOT].stackSize--;
            if (inventory[INPUTSLOT].stackSize == 0) {
                inventory[INPUTSLOT] = null;
            }
        }

    }

    protected int getOperationTime() {
        float cooktime= DEFCOOKTIME-(temperature-SmeltingRegristry.getSmeltTemp(inventory[INPUTSLOT]))/10;
        return (int) (cooktime>20?cooktime:20);

    }

    protected boolean canDoOperation() {
        if (inventory[INPUTSLOT] == null||!activepart) {
            return false;
        } else {
            ItemStack result = SmeltingRegristry
                    .getSmeltingResult(inventory[INPUTSLOT]);
            if (result == null) {
                return false;
            }
            if (temperature < SmeltingRegristry
                    .getSmeltTemp(inventory[INPUTSLOT])) {
                return false;
            }
            if (inventory[OUTPUTSLOT] == null) {
                return true;
            }
            if (!inventory[OUTPUTSLOT].isItemEqual(result)) {
                return false;
            }
            int resultstacksize = inventory[OUTPUTSLOT].stackSize
                    + result.stackSize;
            return resultstacksize <= this.getInventoryStackLimit()
                    && resultstacksize <= inventory[OUTPUTSLOT]
                            .getMaxStackSize();
        }
    }

    protected int getItemBurnTime(ItemStack itemStack) {
        if (itemStack != null && itemStack.getItem() != Items.lava_bucket) {
            return TileEntityFurnace.getItemBurnTime(itemStack);
        } else {
            return 0;
        }
    }

    @Override
    public boolean isItemValidForSlot(int var1, ItemStack var2) {
        if (var1 == INPUTSLOT
                && SmeltingRegristry.getSmeltingResult(var2) != null) {
            return true;
        } else if (var1 == FUELSLOT && this.getItemBurnTime(var2) > 0) {
            return true;
        } else {
            return false;
        }
    }


    @Override
    public boolean canExtractItem(int var1, ItemStack var2) {
        return var1 == OUTPUTSLOT;
    }

    @Override
    public int[] getSlots() {
        return new int[] { FUELSLOT, INPUTSLOT, OUTPUTSLOT };
    }

    @Override
    public void readFromNBT(NBTTagCompound tagCompound) {
        super.readFromNBT(tagCompound);
        deftemperature = tagCompound.getInteger("deftemperature");
        temperature = tagCompound.getFloat("temperature");
        currenfuelburntime = tagCompound.getInteger("currenfuelburntime");
        chimneys=AbsoluteCoordinate.getArrayFromNBTTagList(tagCompound.getTagList(
                        "chimneys", new NBTTagCompound().getId()));
    }

    @Override
    public void writeToNBT(NBTTagCompound tagCompound) {
        super.writeToNBT(tagCompound);
        tagCompound.setInteger("deftemperature", deftemperature);
        tagCompound.setFloat("temperature", temperature);
        tagCompound.setInteger("currenfuelburntime", currenfuelburntime);
        tagCompound.setTag("chimneys",
                AbsoluteCoordinate.getArrayAsNBTTagList(chimneys));
    }

    public float getTemperature() {
        return temperature;
    }


    @Override
    public boolean isProcessing() {
        return temperature>deftemperature;
    }


    @Override
    public void addChimney(AbsoluteCoordinate chimneyCoord) {
        chimneys=ArrayUtils.addAll(chimneys, new AbsoluteCoordinate[]{chimneyCoord});
    }


    @Override
    public void removeChimney(AbsoluteCoordinate chimneyCoord) {
        chimneys=ArrayUtils.removeElement(chimneys, chimneyCoord);

    }

}
