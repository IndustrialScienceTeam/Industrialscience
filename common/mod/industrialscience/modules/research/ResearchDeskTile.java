package mod.industrialscience.modules.research;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

public class ResearchDeskTile extends TileEntity implements IInventory {
	public final static int PAGESLOT=0;
	public final static int ITEMSLOT=1;
	public final static int BOOKSLOT=2;
	private ItemStack[] Inventory;
	public ResearchDeskTile(){
		this.Inventory= new ItemStack[2];
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
		return "Research Desk";
	}

	@Override
	public boolean func_94042_c() {
		return false;
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		 return worldObj.getBlockTileEntity(xCoord, yCoord, zCoord) == this && player.getDistanceSq(xCoord + 0.5, yCoord + 0.5, zCoord + 0.5) < 64;
	}

	@Override
	public void openChest() {
		
	}

	@Override
	public void closeChest() {
		
	}

	@Override
	public boolean func_94041_b(int i, ItemStack itemstack) {
		// TODO Auto-generated method stub
		//WHAT IS THAT?
		return false;
	}
	@Override
	public void readFromNBT(NBTTagCompound tagCompound){
		super.readFromNBT(tagCompound);
		
		NBTTagList tagList = tagCompound.getTagList("Inventory");
		
		for(int i = 0; i < tagList.tagCount(); i++){
			NBTTagCompound tag = (NBTTagCompound) tagList.tagAt(i);
			
			byte slot = tag.getByte("Slot");
			
			if(slot >= 0 && slot < Inventory.length){
				Inventory[slot] = ItemStack.loadItemStackFromNBT(tag);
			}
		}
	}
	
	@Override
	public void writeToNBT(NBTTagCompound tagCompound){
		super.writeToNBT(tagCompound);
		
		NBTTagList itemList = new NBTTagList();
		
		for(int i = 0; i < Inventory.length; i++){
			ItemStack stack = Inventory[i];
			
			if(stack != null){
				NBTTagCompound tag = new NBTTagCompound();
				
				tag.setByte("Slot", (byte) i);
				stack.writeToNBT(tag);
				itemList.appendTag(tag);
			}
		}
		
		tagCompound.setTag("Inventory", itemList);
	}

}
