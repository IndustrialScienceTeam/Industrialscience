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
        ITileEntityMultiBlockController implements IHatchSupport,IThermometerSupport,IChimneySupport {
    public static final int INPUTSLOT = 0;
    public static final int FUELSLOT = 1;
    public static final int OUTPUTSLOT = 2;
    public static final int DEFCOOKTIME=300;
    protected ItemStack[] furnaceslots = new ItemStack[3];
    protected RelativeCoordinate[] itemhatchcoords;
    protected RelativeCoordinate[] interfacehatchcoords;
    protected AbsoluteCoordinate[] chimneys={};
    protected int deftemperature = 20;
    protected float temperature = 20;
    protected int currenfuelburntime = 0;
    protected int cookedticks = 0;

    public TileEntityMultiBlockFurnace(int deftemperature,
            RelativeCoordinate[] itemhatchcoords,
            RelativeCoordinate[] interfacehatchcoords) {
        super();
        this.deftemperature = deftemperature;
        temperature = deftemperature;
        this.itemhatchcoords = itemhatchcoords;
        this.interfacehatchcoords = interfacehatchcoords;
    }
    

    public TileEntityMultiBlockFurnace() {
        super();
    }


    @Override
    public int getSizeInventory() {
        return furnaceslots.length;
    }

    @Override
    public void updateEntity() {
        super.updateEntity();
        if(!worldObj.isRemote){
        updateChimneys();
        burnFuel();
        if (this.canSmelt()) {
            cookedticks++;
            if (cookedticks == this.getCookTime()) {
                this.smeltItem();
                cookedticks = 0;
            }
        } else if (cookedticks > 0) {
            cookedticks = 0;
        }
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
        if (currenfuelburntime == 0 && furnaceslots[FUELSLOT] != null&&activepart) {
            currenfuelburntime = this.getItemBurnTime(furnaceslots[FUELSLOT]);
            furnaceslots[FUELSLOT].stackSize--;
            if (furnaceslots[FUELSLOT].stackSize == 0) {
                furnaceslots[FUELSLOT] = null;
            }
        }
    }


    protected void smeltItem() {
        if (this.canSmelt()) {
            ItemStack result = SmeltingRegristry
                    .getSmeltingResult(furnaceslots[INPUTSLOT]);
            if (furnaceslots[OUTPUTSLOT] == null) {
                furnaceslots[OUTPUTSLOT] = result.copy();
            } else if (furnaceslots[OUTPUTSLOT].getItem() == result.getItem()) {
                furnaceslots[OUTPUTSLOT].stackSize += result.stackSize;
            }
            furnaceslots[INPUTSLOT].stackSize--;
            if (furnaceslots[INPUTSLOT].stackSize == 0) {
                furnaceslots[INPUTSLOT] = null;
            }
        }

    }

    protected int getCookTime() {
        float cooktime= DEFCOOKTIME-(temperature-SmeltingRegristry.getSmeltTemp(furnaceslots[INPUTSLOT]))/10;
        return (int) (cooktime>20?cooktime:20);
                
    }

    protected boolean canSmelt() {
        if (furnaceslots[INPUTSLOT] == null||!activepart) {
            return false;
        } else {
            ItemStack result = SmeltingRegristry
                    .getSmeltingResult(furnaceslots[INPUTSLOT]);
            if (result == null) {
                return false;
            }
            if (temperature < SmeltingRegristry
                    .getSmeltTemp(furnaceslots[INPUTSLOT])) {
                return false;
            }
            if (furnaceslots[OUTPUTSLOT] == null) {
                return true;
            }
            if (!furnaceslots[OUTPUTSLOT].isItemEqual(result)) {
                return false;
            }
            int resultstacksize = furnaceslots[OUTPUTSLOT].stackSize
                    + result.stackSize;
            return resultstacksize <= this.getInventoryStackLimit()
                    && resultstacksize <= furnaceslots[OUTPUTSLOT]
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
    public ItemStack getStackInSlot(int var1) {
        return furnaceslots[var1];
    }

    @Override
    public ItemStack decrStackSize(int var1, int var2) {
        if (furnaceslots[var1] != null) {
            ItemStack result;
            if (furnaceslots[var1].stackSize <= var2) {
                result = furnaceslots[var1];
                furnaceslots[var1] = null;
                return result;
            } else {
                result = furnaceslots[var1].splitStack(var2);
                if (furnaceslots[var1].stackSize == 0) {
                    furnaceslots[var1] = null;
                }
                return result;
            }
        } else {
            return null;
        }
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int var1) {
        if (furnaceslots[var1] != null) {
            ItemStack result = furnaceslots[var1];
            furnaceslots[var1] = null;
            return result;
        }
        return null;
    }

    @Override
    public void setInventorySlotContents(int var1, ItemStack var2) {
        furnaceslots[var1] = var2;
        if (var2 != null && var2.stackSize > this.getInventoryStackLimit()) {
            var2.stackSize = this.getInventoryStackLimit();
        }

    }

    @Override
    public String getInventoryName() {
        return "";
    }

    @Override
    public boolean hasCustomInventoryName() {
        return false;
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer var1) {
        return worldObj.getTileEntity(xCoord, yCoord, zCoord) != this ? false
                : var1.getDistanceSq(xCoord + 0.5D, yCoord + 0.5D,
                        zCoord + 0.5D) <= 64.0D;
    }

    @Override
    public void openInventory() {
    }

    @Override
    public void closeInventory() {
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
    public RelativeCoordinate[] getRelativeInterfaceHatchCoords() {
        return interfacehatchcoords;
    }

    @Override
    public boolean canExtractItem(int var1, ItemStack var2) {
        return var1 == OUTPUTSLOT;
    }

    @Override
    public RelativeCoordinate[] getRelativeItemHatchCoords() {
        return itemhatchcoords;
    }

    @Override
    public int[] getSlots() {
        return new int[] { FUELSLOT, INPUTSLOT, OUTPUTSLOT };
    }

    @Override
    public void readFromNBT(NBTTagCompound tagCompound) {
        super.readFromNBT(tagCompound);
        NBTTagList nbttaglist = tagCompound.getTagList("Items", 10);
        furnaceslots = new ItemStack[this.getSizeInventory()];

        for (int i = 0; i < nbttaglist.tagCount(); ++i) {
            NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
            byte b0 = nbttagcompound1.getByte("Slot");

            if (b0 >= 0 && b0 < furnaceslots.length) {
                furnaceslots[b0] = ItemStack
                        .loadItemStackFromNBT(nbttagcompound1);
            }
        }
        deftemperature = tagCompound.getInteger("deftemperature");
        temperature = tagCompound.getFloat("temperature");
        currenfuelburntime = tagCompound.getInteger("currenfuelburntime");
        cookedticks = tagCompound.getInteger("cookedticks");
        itemhatchcoords = RelativeCoordinate.getArrayFromNBTTagList(tagCompound
                .getTagList("itemhatchcoords", new NBTTagCompound().getId()));
        interfacehatchcoords = RelativeCoordinate
                .getArrayFromNBTTagList(tagCompound.getTagList(
                        "interfacehatchcoords", new NBTTagCompound().getId()));
    }

    @Override
    public void writeToNBT(NBTTagCompound tagCompound) {
        super.writeToNBT(tagCompound);
        tagCompound.setInteger("deftemperature", deftemperature);
        tagCompound.setFloat("temperature", temperature);
        tagCompound.setInteger("currenfuelburntime", currenfuelburntime);
        tagCompound.setInteger("cookedticks", cookedticks);
        NBTTagList nbttaglist = new NBTTagList();

        for (int i = 0; i < furnaceslots.length; ++i) {
            if (furnaceslots[i] != null) {
                NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                nbttagcompound1.setByte("Slot", (byte) i);
                furnaceslots[i].writeToNBT(nbttagcompound1);
                nbttaglist.appendTag(nbttagcompound1);
            }
        }

        tagCompound.setTag("Items", nbttaglist);
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
