package industrialscience.modules.mining.blocks;

import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import industrialscience.blocksystem.ModelISBlock;
import industrialscience.modules.mining.tileentities.DrillingPipeTileEntity;
import industrialscience.modules.research.frontend.renderer.ResearchDeskRenderer;

public class DrillingPipeBlock extends ModelISBlock {

	public DrillingPipeBlock() {
		super(DrillingPipeTileEntity.class, "DrillingPipe");
		if(FMLCommonHandler.instance().getSide().isClient()){
			this.setRenderer(ResearchDeskRenderer.class);
		}
	}

}
