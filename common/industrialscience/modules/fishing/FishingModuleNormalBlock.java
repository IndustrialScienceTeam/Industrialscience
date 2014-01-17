package industrialscience.modules.fishing;
import industrialscience.blocksystem.ISModuleNormalBlock;
import industrialscience.modules.fishing.TileEntities.AbstractFishTrapTileEntity;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class FishingModuleNormalBlock extends ISModuleNormalBlock {
	public FishingModuleNormalBlock(int par1, String prefix) {
		super(par1, Material.iron, prefix);
		
	}

	@Override
    public void updateTick(World world, int x, int y, int z, Random random) {
        TileEntity tile = world.getBlockTileEntity(x, y, z);
        if (!(tile instanceof AbstractFishTrapTileEntity))
            return;
        AbstractFishTrapTileEntity fishtile = (AbstractFishTrapTileEntity) tile;
        fishtile.doUpdateTick(world, x, y, z, random);

    }

}
