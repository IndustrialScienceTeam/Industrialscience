package de.zsgn.industrialscience.factory.tileentity;

import org.apache.commons.lang3.ArrayUtils;

import de.zsgn.industrialscience.AbsoluteCoordinate;
import de.zsgn.industrialscience.RelativeCoordinate;
import de.zsgn.industrialscience.factory.block.IBlockMultiBlockController;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityHatch extends TileEntityMultiBlock implements
        ISidedInventory {
    protected boolean isoutput;
    protected boolean isinput;
    protected boolean isinterface;
    @Override
    public int getSizeInventory() {
        if(worldObj.getTileEntity(masterx, mastery, masterz) instanceof IHatchSupport){
            return ((IHatchSupport)worldObj.getTileEntity(masterx, mastery, masterz)).getSizeInventory();
        }
        return 0;
    }

    @Override
    public ItemStack getStackInSlot(int var1) {
        if(worldObj.getTileEntity(masterx, mastery, masterz) instanceof IHatchSupport){
            return ((IHatchSupport)worldObj.getTileEntity(masterx, mastery, masterz)).getStackInSlot(var1);
        }
        return null;
    }

    @Override
    public ItemStack decrStackSize(int var1, int var2) {
        if(worldObj.getTileEntity(masterx, mastery, masterz) instanceof IHatchSupport){
            return ((IHatchSupport)worldObj.getTileEntity(masterx, mastery, masterz)).decrStackSize(var1, var2);
        }
        return null;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int var1) {
        if(worldObj.getTileEntity(masterx, mastery, masterz) instanceof IHatchSupport){
            return ((IHatchSupport)worldObj.getTileEntity(masterx, mastery, masterz)).getStackInSlotOnClosing(var1);
        }
        return null;
    }

    @Override
    public void setInventorySlotContents(int var1, ItemStack var2) {
        if(worldObj.getTileEntity(masterx, mastery, masterz) instanceof IHatchSupport){
            ((IHatchSupport)worldObj.getTileEntity(masterx, mastery, masterz)).setInventorySlotContents(var1, var2);
        }
    }

    @Override
    public String getInventoryName() {
        if(worldObj.getTileEntity(masterx, mastery, masterz) instanceof IHatchSupport){
            return ((IHatchSupport)worldObj.getTileEntity(masterx, mastery, masterz)).getInventoryName();
        }
        return "";
    }

    @Override
    public boolean hasCustomInventoryName() {
        return false;
    }

    @Override
    public int getInventoryStackLimit() {
        if(worldObj.getTileEntity(masterx, mastery, masterz) instanceof IHatchSupport){
            return ((IHatchSupport)worldObj.getTileEntity(masterx, mastery, masterz)).getInventoryStackLimit();
        }
        return 0;
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
        if(worldObj.getTileEntity(masterx, mastery, masterz) instanceof IHatchSupport){
            return ((IHatchSupport)worldObj.getTileEntity(masterx, mastery, masterz)).isItemValidForSlot(var1, var2);
        }
        return false;
    }

    @Override
    public int[] getAccessibleSlotsFromSide(int var1) {
        if(worldObj.getTileEntity(masterx, mastery, masterz) instanceof IHatchSupport){
        IHatchSupport master =(IHatchSupport)worldObj.getTileEntity(masterx, mastery, masterz);
        int[] slots=new int[]{};
        if(isinput){
            slots=master.getInputSlots();
        }
        if(isoutput){
            slots=ArrayUtils.addAll(slots, master.getOutputSlots());
        }
        }
        return new int[]{};
    }

    @Override
    public boolean canInsertItem(int var1, ItemStack var2, int var3) {
        if(worldObj.getTileEntity(masterx, mastery, masterz) instanceof IHatchSupport){
        IHatchSupport master =(IHatchSupport)worldObj.getTileEntity(masterx, mastery, masterz);
        return master.isItemValidForSlot(var1, var2);
        }
        return false;
    }

    @Override
    public boolean canExtractItem(int var1, ItemStack var2, int var3) {
        if(worldObj.getTileEntity(masterx, mastery, masterz) instanceof IHatchSupport){
        IHatchSupport master =(IHatchSupport)worldObj.getTileEntity(masterx, mastery, masterz);
        return master.canExtractItem(var1, var2);
        }
        return false;
    }

    @Override
    public void setActivepart(boolean activepart) {
        super.setActivepart(activepart);
        if(worldObj.getTileEntity(masterx, mastery, masterz) instanceof IHatchSupport&&worldObj.getBlock(masterx, mastery, masterz)instanceof IBlockMultiBlockController){
            IHatchSupport master =(IHatchSupport)worldObj.getTileEntity(masterx, mastery, masterz);
            ForgeDirection right=ForgeDirection.getOrientation(worldObj.getBlockMetadata(masterx, mastery, masterz)).getRotation(ForgeDirection.DOWN);
            ForgeDirection depth=ForgeDirection.getOrientation(worldObj.getBlockMetadata(masterx, mastery, masterz)).getOpposite();
            for (RelativeCoordinate outputcoord : master.getRelativeOutputHatchCoords()) {
                if(outputcoord.convertToAbsolute(masterx, mastery, masterz, right, depth).equals(new AbsoluteCoordinate(xCoord, yCoord, zCoord))){
                   isoutput=true;
                   isinterface=true;
                   break;
                }
            }
            for (RelativeCoordinate inputcoord : master.getRelativeInputHatchCoords()) {
                if(inputcoord.convertToAbsolute(masterx, mastery, masterz, right, depth).equals(new AbsoluteCoordinate(xCoord, yCoord, zCoord))){
                   isinput=true;
                   isinterface=true;
                   break;
                }
            }
            if(isinput||isoutput){
                for (RelativeCoordinate interfacecoord : master.getRelativeInterfaceHatchCoords()) {
                    if(interfacecoord.convertToAbsolute(masterx, mastery, masterz, right, depth).equals(new AbsoluteCoordinate(xCoord, yCoord, zCoord))){
                       isinterface=true;
                       break;
                    }
                }
            }
        }
    }

}
