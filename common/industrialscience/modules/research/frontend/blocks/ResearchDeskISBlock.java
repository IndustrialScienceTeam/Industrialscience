package industrialscience.modules.research.frontend.blocks;

import industrialscience.BlockUtils;
import industrialscience.IndustrialScience;
import industrialscience.blocksystem.ModelISBlock;
import industrialscience.modules.research.frontend.TileEntities.ResearchDeskTile;
import industrialscience.modules.research.frontend.renderer.ResearchDeskRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.FMLNetworkHandler;

public class ResearchDeskISBlock extends ModelISBlock {

	public ResearchDeskISBlock() {
		super(ResearchDeskTile.class, "ResearchDesk");
		if(FMLCommonHandler.instance().getSide().isClient()){
			this.setRenderer(ResearchDeskRenderer.class);
		}
	}
	public boolean onBlockActivated(World par1World, int par2, int par3,
			int par4, EntityPlayer par5EntityPlayer, int par6, float par7,
			float par8, float par9) {
		 FMLNetworkHandler.openGui(par5EntityPlayer,
                 IndustrialScience.instance, 0, par1World, par2, par3, par4);
         return true;
	}
	@Override
	public void breakBlock(World world, int x, int y, int z, int i, int j) {
        BlockUtils.dropItems(world, x, y, z);
	}
	

}
