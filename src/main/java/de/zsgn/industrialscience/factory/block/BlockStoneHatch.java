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
                HatchStrategy.onHatchActivated(world, x, y, z, player, side,xOffset, yOffset, zOffset);
            }
            return true;
        }
        return false;
    }



}
