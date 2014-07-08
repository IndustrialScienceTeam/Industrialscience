package de.zsgn.industrialscience.factory.block;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import de.zsgn.industrialscience.IndustrialScience;
import de.zsgn.industrialscience.factory.tileentity.TileEntityHatch;

public class HatchStrategy {
    public static final int itemmovement=1;
    public static void onHatchActivated(World world, int x, int y, int z,
            EntityPlayer player, int side, float offsetx, float offsety,  float offsetz){
        System.out.println(ForgeDirection.getOrientation(side));
        if(world.getTileEntity(x, y, z)instanceof TileEntityHatch){
            TileEntityHatch hatch=(TileEntityHatch)world.getTileEntity(x, y, z);
            if (hatch.isItemInterface()&&player.inventory.getCurrentItem() != null) {
                    if(player.inventory.getCurrentItem().getItem()==IndustrialScience.getInstance().getFactoryModule().getItemTong()){
                        extractItem(world,hatch,player, side, offsetx, offsety, offsetz);
                    }else {
                        insertItem(world, hatch, player);
                    }
            }
        }
    }
    public static void extractItem(World world, TileEntityHatch hatch,
            EntityPlayer player, int side, float offsetx, float offsety,  float offsetz) {
        for (int slot : hatch.getAccessibleSlots()) {
            ItemStack stackinslot =hatch.getStackInSlot(slot);
            if(stackinslot!=null&&hatch.canManuallyExtractItem(slot, stackinslot)){
                ItemStack result = hatch.decrStackSize(slot, 1);
                if(result!=null){
                    ForgeDirection clickedside=ForgeDirection.getOrientation(side);
                    ForgeDirection right=clickedside.getRotation(ForgeDirection.DOWN);
                    ForgeDirection up=clickedside.getRotation(right);
                    EntityItem stack=new EntityItem(world, hatch.xCoord+offsetx, hatch.yCoord+offsety, hatch.zCoord+offsetz, result);
                    stack.motionX=0.5*clickedside.offsetX;
                    stack.motionY=0.5*clickedside.offsetY;
                    stack.motionZ=0.5*clickedside.offsetZ;
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
