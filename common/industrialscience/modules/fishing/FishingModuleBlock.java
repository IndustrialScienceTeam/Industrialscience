package industrialscience.modules.fishing;

import industrialscience.IndustrialScience;
import industrialscience.blocksystem.ISBlock;
import industrialscience.blocksystem.ISModuleBlock;
import industrialscience.modules.fishing.Blocks.TrapCraftingTableISBlock;
import industrialscience.modules.fishing.TileEntities.AbstractFishTrapTileEntity;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.FMLNetworkHandler;

public class FishingModuleBlock extends ISModuleBlock {
public static final int TRAPCRAFTINGBLOCKMETAID=0;
    

    public FishingModuleBlock(int par1, String prefix) {
		super(par1, Material.iron, prefix);
		blocks=new ISBlock[1];
		blocks[TRAPCRAFTINGBLOCKMETAID]=new TrapCraftingTableISBlock();
		
	}

	@Override
    public void updateTick(World world, int x, int y, int z, Random random) {
        TileEntity tile = world.getBlockTileEntity(x, y, z);
        if (!(tile instanceof AbstractFishTrapTileEntity))
            return;
        AbstractFishTrapTileEntity fishtile = (AbstractFishTrapTileEntity) tile;
        fishtile.doUpdateTick(world, x, y, z, random);

    }
	public boolean onBlockActivated(World par1World, int par2, int par3,
			int par4, EntityPlayer par5EntityPlayer, int par6, float par7,
			float par8, float par9) {
		FMLNetworkHandler.openGui(par5EntityPlayer, IndustrialScience.instance, 0, par1World, par2, par3, par4);
		return true;
	}
	public void breakBlock(World world, int x, int y, int z, int i, int j) {
	}

}
