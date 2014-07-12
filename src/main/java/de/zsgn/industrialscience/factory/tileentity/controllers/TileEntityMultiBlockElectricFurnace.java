package de.zsgn.industrialscience.factory.tileentity.controllers;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import de.zsgn.industrialscience.factory.SmeltingRegristry;
import de.zsgn.industrialscience.util.AbsoluteCoordinate;
import de.zsgn.industrialscience.util.RelativeCoordinate;

public class TileEntityMultiBlockElectricFurnace extends
        ITileEntityMultiBlockMachine implements IThermometerSupport, IEnergyLinkSupport{
    public static final int INPUTSLOT = 0;
    public static final int OUTPUTSLOT = 1;
    public static final int DEFCOOKTIME=300;
    protected int deftemperature = 20;
    protected float temperature = 20;
    protected double storedenergy=0;
    protected int maxstoredenergy=40000;

    public TileEntityMultiBlockElectricFurnace(int deftemperature,
            RelativeCoordinate[] itemhatchcoords,
            RelativeCoordinate[] interfacehatchcoords) {
        super(itemhatchcoords, interfacehatchcoords, 2);
        this.deftemperature = deftemperature;
        temperature = deftemperature;
    }
    

    public TileEntityMultiBlockElectricFurnace() {
        super();
    }
    @Override
    public void updateEntity() {
        if(!worldObj.isRemote){
            consumeEnergy();
            super.updateEntity();
        }
    }

    protected void consumeEnergy() {
        if (storedenergy > 0) {
            storedenergy--;
            temperature = temperature + 0.05F;
        } else if (temperature > deftemperature) {
            temperature = temperature - (activepart ? 0.05F : 0.1F);
        }
        
    }

    @Override
    public int[] getSlots() {
        return new int[]{INPUTSLOT,OUTPUTSLOT};
    }

    @Override
    public boolean isItemValidForSlot(int var1, ItemStack var2) {
        return (var1 == INPUTSLOT&& SmeltingRegristry.getSmeltingResult(var2) != null);
    }


    @Override
    public boolean canExtractItem(int var1, ItemStack var2) {
        return var1 == OUTPUTSLOT;
    }

    @Override
    protected int getOperationTime() {
        float cooktime= DEFCOOKTIME-(temperature-SmeltingRegristry.getSmeltTemp(inventory[INPUTSLOT]))/10;
        return (int) (cooktime>20?cooktime:20);
    }

    @Override
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

    @Override
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

    @Override
    public float getTemperature() {
        return temperature;
    }
    @Override
    public boolean isProcessing() {
        return temperature>deftemperature;
    }


    @Override
    public double getRequestedEnergy() {
        if(storedenergy<maxstoredenergy){
            return maxstoredenergy-storedenergy;
        }else {
            return 0;
        }
    }


    @Override
    public double injectEnergy(double energy) {
        if(storedenergy+energy<maxstoredenergy){
            storedenergy+=energy;
            return 0;
        }else {
            double consumedenergy=maxstoredenergy-storedenergy;
            storedenergy=maxstoredenergy;
            return energy-consumedenergy;
        }
    }
    @Override
    public void readFromNBT(NBTTagCompound tagCompound) {
        super.readFromNBT(tagCompound);
        deftemperature = tagCompound.getInteger("deftemperature");
        temperature = tagCompound.getFloat("temperature");
        storedenergy=tagCompound.getDouble("storedenergy");
        maxstoredenergy=tagCompound.getInteger("maxstoredenergy");
    }

    @Override
    public void writeToNBT(NBTTagCompound tagCompound) {
        super.writeToNBT(tagCompound);
        tagCompound.setInteger("deftemperature", deftemperature);
        tagCompound.setFloat("temperature", temperature);
        tagCompound.setDouble("storedenergy", storedenergy);
        tagCompound.setInteger("maxstoredenergy", maxstoredenergy);
    }

}
