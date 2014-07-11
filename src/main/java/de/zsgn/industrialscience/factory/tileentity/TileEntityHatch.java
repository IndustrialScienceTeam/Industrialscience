package de.zsgn.industrialscience.factory.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;
import de.zsgn.industrialscience.factory.block.IBlockMultiBlockController;
import de.zsgn.industrialscience.factory.tileentity.controllers.IHatchSupport;
import de.zsgn.industrialscience.util.AbsoluteCoordinate;
import de.zsgn.industrialscience.util.RelativeCoordinate;

public class TileEntityHatch extends TileEntityMultiBlock implements
        ISidedInventory {
    protected boolean iteminterface = false;
    protected boolean blockinterface = false;
    protected boolean automated = true;

    public TileEntityHatch(boolean automated) {
        super();
        this.automated = automated;
    }

    public TileEntityHatch() {
        super();
    }

    @Override
    public int getSizeInventory() {
        if (worldObj.getTileEntity(masterx, mastery, masterz) instanceof IHatchSupport&&activepart) {
            return ((IHatchSupport) worldObj.getTileEntity(masterx, mastery,
                    masterz)).getSizeInventory();
        }
        return 0;
    }

    @Override
    public ItemStack getStackInSlot(int var1) {
        if (worldObj.getTileEntity(masterx, mastery, masterz) instanceof IHatchSupport&&activepart) {
            return ((IHatchSupport) worldObj.getTileEntity(masterx, mastery,
                    masterz)).getStackInSlot(var1);
        }
        return null;
    }

    @Override
    public ItemStack decrStackSize(int var1, int var2) {
        if (worldObj.getTileEntity(masterx, mastery, masterz) instanceof IHatchSupport&&activepart) {
            return ((IHatchSupport) worldObj.getTileEntity(masterx, mastery,
                    masterz)).decrStackSize(var1, var2);
        }
        return null;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int var1) {
        if (worldObj.getTileEntity(masterx, mastery, masterz) instanceof IHatchSupport&&activepart) {
            return ((IHatchSupport) worldObj.getTileEntity(masterx, mastery,
                    masterz)).getStackInSlotOnClosing(var1);
        }
        return null;
    }

    @Override
    public void setInventorySlotContents(int var1, ItemStack var2) {
        if (worldObj.getTileEntity(masterx, mastery, masterz) instanceof IHatchSupport&&activepart) {
            ((IHatchSupport) worldObj.getTileEntity(masterx, mastery, masterz))
                    .setInventorySlotContents(var1, var2);
        }
    }

    @Override
    public String getInventoryName() {
        if (worldObj.getTileEntity(masterx, mastery, masterz) instanceof IHatchSupport&&activepart) {
            return ((IHatchSupport) worldObj.getTileEntity(masterx, mastery,
                    masterz)).getInventoryName();
        }
        return "";
    }

    @Override
    public boolean hasCustomInventoryName() {
        return false;
    }

    @Override
    public int getInventoryStackLimit() {
        if (worldObj.getTileEntity(masterx, mastery, masterz) instanceof IHatchSupport&&activepart) {
            return ((IHatchSupport) worldObj.getTileEntity(masterx, mastery,
                    masterz)).getInventoryStackLimit();
        }
        return 0;
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
        if (worldObj.getTileEntity(masterx, mastery, masterz) instanceof IHatchSupport&&activepart) {
            return ((IHatchSupport) worldObj.getTileEntity(masterx, mastery,
                    masterz)).isItemValidForSlot(var1, var2);
        }
        return false;
    }

    @Override
    public int[] getAccessibleSlotsFromSide(int var1) {
        if (worldObj.getTileEntity(masterx, mastery, masterz) instanceof IHatchSupport
                && automated&&activepart) {
            IHatchSupport master = (IHatchSupport) worldObj.getTileEntity(
                    masterx, mastery, masterz);
            return master.getSlots();
        }
        return new int[] {};
    }

    @Override
    public boolean canInsertItem(int var1, ItemStack var2, int var3) {
        if (worldObj.getTileEntity(masterx, mastery, masterz) instanceof IHatchSupport
                && automated&&activepart) {
            IHatchSupport master = (IHatchSupport) worldObj.getTileEntity(
                    masterx, mastery, masterz);
            return this.canManuallyInsertItem(var1, var2);
        }
        return false;
    }

    @Override
    public boolean canExtractItem(int var1, ItemStack var2, int var3) {
        if (worldObj.getTileEntity(masterx, mastery, masterz) instanceof IHatchSupport
                && automated&&activepart) {
            return this.canManuallyExtractItem(var1, var2);
        }
        return false;
    }

    public boolean canManuallyInsertItem(int slot, ItemStack itemStack) {
        if (worldObj.getTileEntity(masterx, mastery, masterz) instanceof IHatchSupport&&activepart) {
            IHatchSupport master = (IHatchSupport) worldObj.getTileEntity(
                    masterx, mastery, masterz);
            return master.isItemValidForSlot(slot, itemStack);
        }
        return false;
    }

    public boolean canManuallyExtractItem(int var1, ItemStack var2) {
        if (worldObj.getTileEntity(masterx, mastery, masterz) instanceof IHatchSupport
                &&activepart) {
            IHatchSupport master = (IHatchSupport) worldObj.getTileEntity(
                    masterx, mastery, masterz);
            return master.canExtractItem(var1, var2);
        }
        return false;
    }

    @Override
    public void setActivepart(boolean activepart) {
        super.setActivepart(activepart);
        if (worldObj.getTileEntity(masterx, mastery, masterz) instanceof IHatchSupport
                && worldObj.getBlock(masterx, mastery, masterz) instanceof IBlockMultiBlockController) {
            IHatchSupport master = (IHatchSupport) worldObj.getTileEntity(
                    masterx, mastery, masterz);
            ForgeDirection right = IBlockMultiBlockController.getFacingDir(worldObj, masterx, mastery, masterz)
                    .getRotation(ForgeDirection.DOWN);
            ForgeDirection depth = IBlockMultiBlockController.getFacingDir(worldObj, masterx, mastery, masterz)
                    .getOpposite();
            AbsoluteCoordinate owncoords=new AbsoluteCoordinate(xCoord, yCoord, zCoord);
            for (RelativeCoordinate iteminterfacecoord : master
                    .getRelativeItemHatchCoords()) {
                if (iteminterfacecoord.convertToAbsolute(masterx, mastery,
                        masterz, right, depth).equals(owncoords)) {
                    iteminterface = true;
                    blockinterface = true;
                    break;
                }
            }
            if (!iteminterface) {
                for (RelativeCoordinate interfacecoord : master
                        .getRelativeInterfaceHatchCoords()) {
                    if (interfacecoord.convertToAbsolute(masterx, mastery,
                            masterz, right, depth).equals(
                            new AbsoluteCoordinate(xCoord, yCoord, zCoord))) {
                        blockinterface = true;
                        break;
                    }
                }
            }
        }
    }

    public int[] getAccessibleSlots() {
        if (worldObj.getTileEntity(masterx, mastery, masterz) instanceof IHatchSupport
                &&activepart) {
            IHatchSupport master = (IHatchSupport) worldObj.getTileEntity(
                    masterx, mastery, masterz);
            return master.getSlots();
        }
        return new int[] {};
    }

    public boolean isItemInterface() {
        return iteminterface;
    }

    public boolean isBlockinterface() {
        return blockinterface;
    }

    public boolean isAutomated() {
        return automated;
    }

    @Override
    public void readFromNBT(NBTTagCompound tagCompound) {
        super.readFromNBT(tagCompound);
        automated = tagCompound.getBoolean("automated");
        iteminterface = tagCompound.getBoolean("iteminterface");
        blockinterface = tagCompound.getBoolean("blockinterface");
    }

    @Override
    public void writeToNBT(NBTTagCompound tagCompound) {
        super.writeToNBT(tagCompound);
        tagCompound.setBoolean("automated", automated);
        tagCompound.setBoolean("iteminterface", iteminterface);
        tagCompound.setBoolean("blockinterface", blockinterface);
    }

    @Override
    public void markDirty() {
        if (worldObj.getTileEntity(masterx, mastery, masterz) instanceof IHatchSupport) {
            worldObj.getTileEntity(masterx, mastery, masterz).markDirty();;
        }
        super.markDirty();
    }

}
