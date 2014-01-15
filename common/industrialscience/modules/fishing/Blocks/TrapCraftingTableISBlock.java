package industrialscience.modules.fishing.Blocks;

import industrialscience.BlockUtils;
import industrialscience.IndustrialScience;
import industrialscience.blocksystem.ModelISBlock;
import industrialscience.modules.fishing.TileEntities.TrapCraftingTableTileEntity;
import industrialscience.modules.research.frontend.renderer.ResearchDeskRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.FMLNetworkHandler;

public class TrapCraftingTableISBlock extends ModelISBlock {
    private Icon side;
    private Icon bottom;
    private Icon top;

    public TrapCraftingTableISBlock() {
        super(TrapCraftingTableTileEntity.class,"trapcraftingtable",ResearchDeskRenderer.class);
    }

    @Override
    public void breakBlock(World world, int x, int y, int z, int i, int j) {
        BlockUtils.dropItems(world, x, y, z);
        super.breakBlock(world, x, y, z, i, j);
    }

	/* (non-Javadoc)
	 * @see industrialscience.blocksystem.ISBlock#onBlockActivated(net.minecraft.world.World, int, int, int, net.minecraft.entity.player.EntityPlayer, int, float, float, float)
	 */
	@Override
	public boolean onBlockActivated(World par1World, int par2, int par3,
			int par4, EntityPlayer par5EntityPlayer, int par6, float par7,
			float par8, float par9) {
		FMLNetworkHandler.openGui(par5EntityPlayer, IndustrialScience.instance, 0, par1World, par2, par3, par4);
		return true;
	}

}
