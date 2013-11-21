package industrialscience;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

public abstract class ISIInventory extends TileEntity implements IInventory {
	protected ItemStack[] Inventory;
	protected int stacklimit;
	protected String InvName;
	protected ISIInventory(int size, int stacklimit, String InvName){
		if(size>-1){
			Inventory= new ItemStack[size];
		}
		else{
			Inventory=new ItemStack[1];
		}
		if(stacklimit>1){
		this.stacklimit=stacklimit;}
		else{
			this.stacklimit=64;
		}
		this.InvName=InvName;
		
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

        if (stack != null) {

            if (stack.stackSize <= j) {
                setInventorySlotContents(i, null);
            } else {
                stack = stack.splitStack(j);
                if (stack.stackSize == 0) {
                    setInventorySlotContents(i, null);
                }
            }
        }

        return stack;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int i) {
	     ItemStack stack = getStackInSlot(i);

	        if (stack != null) {
	            setInventorySlotContents(i, null);
	        }

	        return stack;
	}

	@Override
	public void setInventorySlotContents(int i, ItemStack itemstack) {
		Inventory[i] = itemstack;
        if (itemstack != null && itemstack.stackSize > getInventoryStackLimit()) {
            itemstack.stackSize = getInventoryStackLimit();
        }

	}

	@Override
	public String getInvName() {
		return InvName;
	}

	@Override
	public boolean isInvNameLocalized() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getInventoryStackLimit() {
		return stacklimit;
	}
	@Override
	public void openChest() {

	}

	@Override
	public void closeChest() {

	}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack) {
		return true;
	}
    @Override
    public void readFromNBT(NBTTagCompound tagCompound) {
        super.readFromNBT(tagCompound);

        NBTTagList tagList = tagCompound.getTagList("Inventory");

        for (int i = 0; i < tagList.tagCount(); i++) {
            NBTTagCompound tag = (NBTTagCompound) tagList.tagAt(i);

            byte slot = tag.getByte("Slot");

            if (slot >= 0 && slot < Inventory.length) {
                Inventory[slot] = ItemStack.loadItemStackFromNBT(tag);
            }
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound tagCompound) {
        super.writeToNBT(tagCompound);

        NBTTagList itemList = new NBTTagList();

        for (int i = 0; i < Inventory.length; i++) {
            ItemStack stack = Inventory[i];

            if (stack != null) {
                NBTTagCompound tag = new NBTTagCompound();

                tag.setByte("Slot", (byte) i);
                stack.writeToNBT(tag);
                itemList.appendTag(tag);
            }
        }

        tagCompound.setTag("Inventory", itemList);
    }

}
