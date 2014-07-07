package de.zsgn.industrialscience.factory.block;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import de.zsgn.industrialscience.IndustrialScience;
import de.zsgn.industrialscience.factory.tileentity.TileEntityHatch;

public class BlockStoneHatch extends BlockStoneHull {

    public BlockStoneHatch() {
        super();
        this.setBlockName("cobblestonehatch");
        textureName = IndustrialScience.MODID + ":"
                + this.getUnlocalizedName().substring(5);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int blockMetadata) {
        return new TileEntityHatch(false);
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z,
            EntityPlayer player, int side, float xOffset, float yOffset,
            float zOffset) {
        if (world.getTileEntity(x, y, z) instanceof TileEntityHatch) {
            if(!world.isRemote){
            TileEntityHatch hatch = (TileEntityHatch) world.getTileEntity(x, y,
                    z);
            if (hatch.isItemInterface()) {
                if (player.inventory.getCurrentItem() != null) {
                    if(player.inventory.getCurrentItem().getItem()==IndustrialScience.getInstance().getFactoryModule().getItemTong()){
                   extractItem(world,hatch,player);
                    }else {
                        insertItem(world, hatch, player);
                    }
                    return true;
                    }
                }
            }else {
                return true;
            }
        }
        return false;
    }

    public static void extractItem(World world, TileEntityHatch hatch,
            EntityPlayer player) {
        for (int slot : hatch.getAccessibleSlots()) {
            ItemStack stackinslot =hatch.getStackInSlot(slot);
            if(stackinslot!=null&&hatch.canManuallyExtractItem(slot, stackinslot)){
                ItemStack result = hatch.decrStackSize(slot, 1);
                if(result!=null){
                    EntityItem stack=new EntityItem(world, player.posX, player.posY, player.posZ, result);
                    world.spawnEntityInWorld(stack);
                }
            }
        } 
        
    }

    public static void insertItem(World world,TileEntityHatch hatch, EntityPlayer player) {
        for (int slot : hatch.getAccessibleSlots()) {
            ItemStack originalSlot=hatch.getStackInSlot(slot);
            if (hatch.canManuallyInsertItem(slot, player.getHeldItem())) {
                boolean isdirty=false;
                if (originalSlot == null)
                {
                   hatch.setInventorySlotContents(slot, player.inventory.mainInventory[player.inventory.currentItem].splitStack(1));
                   isdirty=true;
                    
               } else if (areStacksCompatable(originalSlot,player.getHeldItem()))
                {
                    int max = Math.min(player.getHeldItem().getMaxStackSize(), hatch.getInventoryStackLimit());
                    if (max > originalSlot.stackSize+1)
                    {
                        player.inventory.mainInventory[player.inventory.currentItem].stackSize--;
                        originalSlot.stackSize++;
                        isdirty=true;
                    }
                }
            if (isdirty) {
                hatch.markDirty();
            }
            }
        } 
    }
    public static boolean areStacksCompatable(ItemStack stack1, ItemStack stack2)
    {
        return stack1.getItem() != stack2.getItem() ? false : (stack1.getItemDamage() != stack2.getItemDamage() ? false : (stack1.stackSize > stack1.getMaxStackSize() ? false : ItemStack.areItemStackTagsEqual(stack1, stack2)));
    }

}
