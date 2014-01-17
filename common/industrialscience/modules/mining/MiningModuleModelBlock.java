package industrialscience.modules.mining;

import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraft.block.material.Material;
import industrialscience.blocksystem.ISModuleModelBlock;
import industrialscience.blocksystem.ModelISBlock;
import industrialscience.modules.mining.blocks.Drill;
import industrialscience.modules.mining.blocks.DrillingPipeBlock;
import industrialscience.modules.mining.tileentities.drill.IronDrillTileEntity;
import industrialscience.modules.research.frontend.renderer.ResearchDeskRenderer;

public class MiningModuleModelBlock extends ISModuleModelBlock {
public static final int DRILLINGPIPEMETAID=0;
public static final int IRONDRILLEMETAID=1;
	public MiningModuleModelBlock(int par1, String prefix) {
		super(par1, Material.iron, prefix);
		blocks = new ModelISBlock[2];
		blocks[DRILLINGPIPEMETAID]=new DrillingPipeBlock();
		blocks[IRONDRILLEMETAID]=new Drill(IronDrillTileEntity.class, "IronDrill");
		if(FMLCommonHandler.instance().getSide().isClient())
		((ModelISBlock)blocks[IRONDRILLEMETAID]).setRenderer(ResearchDeskRenderer.class);
	}

}
