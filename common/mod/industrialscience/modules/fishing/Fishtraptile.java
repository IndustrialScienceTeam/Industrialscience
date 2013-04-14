package mod.industrialscience.modules.fishing;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

public class Fishtraptile extends TileEntity implements IInventory {
	private ItemStack[] Inventory;
	private String name="Basic Fishtrap";
	public Fishtraptile() {
		Inventory = new ItemStack[2];
	}
	@Override
	public void updateEntity(){
	}

	@Override
	public int getSizeInventory() {
		return Inventory.length;
	}

	@Override
	public ItemStack getStackInSlot(int i) {
		return Inventory[i];
	}

	@Override
	public ItemStack decrStackSize(int i, int j) {
ItemStack stack = getStackInSlot(i);
        
        if(stack != null){
       
                if(stack.stackSize <= j){
                        setInventorySlotContents(i, null);
                }
                else{
                        stack = stack.splitStack(j);
                        if(stack.stackSize == 0){
                                setInventorySlotContents(i, null);
                        }
                }
        }


        return stack;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int i) {
		ItemStack stack = getStackInSlot(i);
        
        if(stack != null){
                setInventorySlotContents(i, null);
        }
       
       
        return stack;
	}

	@Override
	public void setInventorySlotContents(int i, ItemStack itemstack) {
		Inventory[i]=itemstack;
        if(itemstack != null && itemstack.stackSize > getInventoryStackLimit()){
            itemstack.stackSize = getInventoryStackLimit();
            }

	}

	@Override
	public String getInvName() {
		return name;
	}

	@Override
	public boolean func_94042_c() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer entityplayer) {
		return worldObj.getBlockTileEntity(xCoord, yCoord, zCoord) == this && entityplayer.getDistanceSq(xCoord + 0.5, yCoord + 0.5, zCoord + 0.5) < 64;
	}

	@Override
	public void openChest() {
		// TODO Auto-generated method stub

	}

	@Override
	public void closeChest() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean func_94041_b(int i, ItemStack itemstack) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean canUpdate(){
		return true;
	}
	

}
