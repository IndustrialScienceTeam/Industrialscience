package de.zsgn.industrialscience.factory.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import de.zsgn.industrialscience.RelativeCoordinate;

public class TileEntityStoneFurnace extends
ITileEntityMultiBlockController implements IHatchSupport{
    protected ItemStack[] testslots=new ItemStack[2];
    protected int waitingticks=0;
    
    @Override
    public int getSizeInventory() {
        return testslots.length;
    }

    @Override
    public void updateEntity() {
        waitingticks++;
        if(waitingticks==20){
            waitingticks=0;
            if(testslots[0]!=null &&testslots[1]==null){
                testslots[1]=testslots[0];
                testslots[0]=null;
            }
        }
    }

    @Override
    public ItemStack getStackInSlot(int var1) {
        return testslots[var1];
    }

    @Override
    public ItemStack decrStackSize(int var1, int var2) {
        if(testslots[var1]!=null){
            ItemStack result;
            if(testslots[var1].stackSize<=var2){
                result=testslots[var1];
                testslots[var1]=null;
                return result;
            }else {
                result=testslots[var1].splitStack(var2);
                if(testslots[var1].stackSize==0){
                    testslots[var1]=null;
                }
                return null;
            }
        }else {
            return null;
        }
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int var1) {
        if(testslots[var1]!=null){
            ItemStack result=testslots[var1];
            testslots[var1]=null;
            return result;
        }
        return null;
    }

    @Override
    public void setInventorySlotContents(int var1, ItemStack var2) {
        testslots[var1]=var2;
        if (var2!= null && var2.stackSize > this.getInventoryStackLimit()){
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
        return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : var1.getDistanceSq((double)this.xCoord + 0.5D, (double)this.yCoord + 0.5D, (double)this.zCoord + 0.5D) <= 64.0D;
    }

    @Override
    public void openInventory() {
    }

    @Override
    public void closeInventory() {
    }

    @Override
    public boolean isItemValidForSlot(int var1, ItemStack var2) {
        return var1==0;
    }

    @Override
    public RelativeCoordinate[] getRelativeOutputHatchCoords() {
        return new RelativeCoordinate[]{new RelativeCoordinate(0, -1, 1)};
    }

    @Override
    public RelativeCoordinate[] getRelativeInputHatchCoords() {
        return new RelativeCoordinate[]{new RelativeCoordinate(0, 1, 1)};
    }

    @Override
    public RelativeCoordinate[] getRelativeInterfaceHatchCoords() {
        return new RelativeCoordinate[]{new RelativeCoordinate(-1, 1, 0)};
    }

    @Override
    public int[] getOutputSlots() {
        return new int[]{1};
    }

    @Override
    public int[] getInputSlots() {
        return new int[]{0};
    }

    @Override
    public boolean canExtractItem(int var1, ItemStack var2) {
        return var1==0;
    }

}
