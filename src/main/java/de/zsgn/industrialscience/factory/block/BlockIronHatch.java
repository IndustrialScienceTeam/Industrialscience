package de.zsgn.industrialscience.factory.block;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import de.zsgn.industrialscience.IndustrialScience;
import de.zsgn.industrialscience.factory.tileentity.TileEntityHatch;

public class BlockIronHatch extends BlockIronHull {

    public BlockIronHatch() {
        super();
        this.setBlockName("ironhatch");
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
                HatchStrategy.onHatchActivated(world, x, y, z, player, side);
            }
            return true;
        }
        return false;
    }

}
