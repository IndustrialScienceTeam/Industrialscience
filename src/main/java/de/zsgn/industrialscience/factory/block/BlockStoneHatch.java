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
        return new TileEntityHatch(true);
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
                if (player.inventory.getCurrentItem() != null&&player.inventory.getCurrentItem().getItem()==IndustrialScience.getInstance().getFactoryModule().getItemTong()) {
                   extractItem(world,hatch,player);
                    return true;
                    }
                }
            }else {
                return true;
            }
        }
        return false;
    }

    public static void extractItem(World world,TileEntityHatch hatch, EntityPlayer player) {
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

}
