package de.zsgn.industrialscience.factory.tileentity.controllers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import de.zsgn.industrialscience.util.AbsoluteCoordinate;
import de.zsgn.industrialscience.util.RelativeCoordinate;

public abstract class ITileEntityMultiBlockMachine extends
        ITileEntityMultiBlockController implements IHatchSupport {
    protected ItemStack[] inventory = new ItemStack[3];
    protected RelativeCoordinate[] itemhatchcoords;
    protected RelativeCoordinate[] interfacehatchcoords;
    private int operatedticks = 0;
    
    public ITileEntityMultiBlockMachine(RelativeCoordinate[] itemhatchcoords,
            RelativeCoordinate[] interfacehatchcoords) {
        this.itemhatchcoords=itemhatchcoords;
        this.interfacehatchcoords=interfacehatchcoords;
    }
    
    public ITileEntityMultiBlockMachine() {
    }
    @Override
    public void updateEntity() {
        super.updateEntity();
        if(!worldObj.isRemote){
        if (this.canDoOperation()) {
            operatedticks++;
            if (operatedticks == this.getOperationTime()) {
                this.doOperation();
                operatedticks = 0;
            }
        } else if (operatedticks > 0) {
            operatedticks = 0;
        }
        }

    }

    protected abstract int getOperationTime();
    protected abstract boolean canDoOperation();
    protected abstract void doOperation();

    @Override
    public void closeInventory() {
    }

    @Override
    public ItemStack decrStackSize(int var1, int var2) {
        if (inventory[var1] != null) {
            ItemStack result;
            if (inventory[var1].stackSize <= var2) {
                result = inventory[var1];
                inventory[var1] = null;
                return result;
            } else {
                result = inventory[var1].splitStack(var2);
                if (inventory[var1].stackSize == 0) {
                    inventory[var1] = null;
                }
                return result;
            }
        } else {
            return null;
        }
    }

    @Override
    public String getInventoryName() {
        return "";
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public RelativeCoordinate[] getRelativeInterfaceHatchCoords() {
        return interfacehatchcoords;
    }

    @Override
    public RelativeCoordinate[] getRelativeItemHatchCoords() {
        return itemhatchcoords;
    }

    @Override
    public int getSizeInventory() {
        return inventory.length;
    }

    @Override
    public ItemStack getStackInSlot(int var1) {
        return inventory[var1];
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int var1) {
        if (inventory[var1] != null) {
            ItemStack result = inventory[var1];
            inventory[var1] = null;
            return result;
        }
        return null;
    }

    @Override
    public boolean hasCustomInventoryName() {
        return false;
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
    public void readFromNBT(NBTTagCompound tagCompound) {
        super.readFromNBT(tagCompound);
        NBTTagList nbttaglist = tagCompound.getTagList("Items", 10);
        inventory = new ItemStack[this.getSizeInventory()];

        for (int i = 0; i < nbttaglist.tagCount(); ++i) {
            NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
            byte b0 = nbttagcompound1.getByte("Slot");

            if (b0 >= 0 && b0 < inventory.length) {
                inventory[b0] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
            }
        }
        itemhatchcoords = RelativeCoordinate.getArrayFromNBTTagList(tagCompound
                .getTagList("itemhatchcoords", new NBTTagCompound().getId()));
        interfacehatchcoords = RelativeCoordinate
                .getArrayFromNBTTagList(tagCompound.getTagList(
                        "interfacehatchcoords", new NBTTagCompound().getId()));
        operatedticks = tagCompound.getInteger("operatedticks");
    }

    @Override
    public void writeToNBT(NBTTagCompound tagCompound) {
        super.writeToNBT(tagCompound);
        NBTTagList nbttaglist = new NBTTagList();

        for (int i = 0; i < inventory.length; ++i) {
            if (inventory[i] != null) {
                NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                nbttagcompound1.setByte("Slot", (byte) i);
                inventory[i].writeToNBT(nbttagcompound1);
                nbttaglist.appendTag(nbttagcompound1);
            }
        }

        tagCompound.setTag("Items", nbttaglist);
        tagCompound.setInteger("operatedticks", operatedticks);
        tagCompound.setTag("itemhatchcoords",
                RelativeCoordinate.getArrayAsNBTTagList(itemhatchcoords));
        tagCompound.setTag("interfacehatchcoords",
                RelativeCoordinate.getArrayAsNBTTagList(interfacehatchcoords));
    }

}
