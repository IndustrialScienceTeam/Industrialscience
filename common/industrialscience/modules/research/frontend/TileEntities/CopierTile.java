package industrialscience.modules.research.frontend.TileEntities;

import industrialscience.modules.ResearchModule;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

public class CopierTile extends TileEntity implements IInventory {
    public final static int PAGEINPUT = 0;
    public final static int PAGEOUTPUT = 1;
    public final static int UPGRADESLOT = 2;
    private ItemStack[] Inventory;

    public CopierTile() {
        Inventory = new ItemStack[3];
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
        return "Research Copier";
    }

    @Override
    public int getInventoryStackLimit() {
        return 1;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer entityplayer) {
        return worldObj.getBlockTileEntity(xCoord, yCoord, zCoord) == this
                && entityplayer.getDistanceSq(xCoord + 0.5, yCoord + 0.5,
                        zCoord + 0.5) < 64;
    }

    @Override
    public void openChest() {

    }

    @Override
    public void closeChest() {

    }

    @Override
    public boolean isInvNameLocalized() {
        return false;
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

    @Override
    public boolean isItemValidForSlot(int i, ItemStack itemstack) {
        if ((i == PAGEINPUT)
                && itemstack.itemID == ResearchModule.researchNoteID)
            return true;
        if (itemstack.itemID == ResearchModule.researchbookID)
            return true;
        return false;
    }

}
