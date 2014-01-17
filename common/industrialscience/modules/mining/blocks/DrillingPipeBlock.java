package industrialscience.modules.mining.blocks;

import industrialscience.blocksystem.ModelISBlock;
import industrialscience.modules.mining.tileentities.DrillingPipeTileEntity;
import industrialscience.modules.research.frontend.renderer.ResearchDeskRenderer;
import cpw.mods.fml.common.FMLCommonHandler;

public class DrillingPipeBlock extends ModelISBlock {

	public DrillingPipeBlock() {
		super(DrillingPipeTileEntity.class, "DrillingPipe");
		if(FMLCommonHandler.instance().getSide().isClient()){
			this.setRenderer(ResearchDeskRenderer.class);
		}
	}

}
