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
    public RelativeCoordinate[] getRelativeInterfaceHatchCoords() {
        return new RelativeCoordinate[]{};
    }


    @Override
    public boolean canExtractItem(int var1, ItemStack var2) {
        return var1==1;
    }

    @Override
    public RelativeCoordinate[] getRelativeItemHatchCoords() {
        return new RelativeCoordinate[]{new RelativeCoordinate(1, 0, 1),new RelativeCoordinate(-1, 0, 1),new RelativeCoordinate(0, 0, 2),new RelativeCoordinate(0, 1, 1),new RelativeCoordinate(0, -1, 1)};
    }

    @Override
    public int[] getSlots() {
       return new int[]{0,1};
    }

    @Override
    public void readFromNBT(NBTTagCompound tagCompound) {
        super.readFromNBT(tagCompound);
        NBTTagList nbttaglist = tagCompound.getTagList("Items", 10);
        this.testslots = new ItemStack[this.getSizeInventory()];

        for (int i = 0; i < nbttaglist.tagCount(); ++i)
        {
            NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
            byte b0 = nbttagcompound1.getByte("Slot");

            if (b0 >= 0 && b0 < this.testslots.length)
            {
                this.testslots[b0] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
            }
        }
        waitingticks=tagCompound.getInteger("waitingticks");
    }

    @Override
    public void writeToNBT(NBTTagCompound tagCompound) {
        super.writeToNBT(tagCompound);
        tagCompound.setInteger("waitingticks", (short)this.waitingticks);
        NBTTagList nbttaglist = new NBTTagList();

        for (int i = 0; i < this.testslots.length; ++i)
        {
            if (this.testslots[i] != null)
            {
                NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                nbttagcompound1.setByte("Slot", (byte)i);
                this.testslots[i].writeToNBT(nbttagcompound1);
                nbttaglist.appendTag(nbttagcompound1);
            }
        }

        tagCompound.setTag("Items", nbttaglist);
    }

}
