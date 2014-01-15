package industrialscience.modules.research;

import net.minecraft.block.material.Material;
import industrialscience.blocksystem.ModelISBlock;
import industrialscience.blocksystem.ISModuleModelBlock;
import industrialscience.blocksystem.ISModuleNormalBlock;
import industrialscience.modules.research.frontend.blocks.CopierISBlock;
import industrialscience.modules.research.frontend.blocks.ResearchDeskISBlock;

public class ResearchModuleNormalBlock extends ISModuleNormalBlock {
	
	public ResearchModuleNormalBlock(int par1, String prefix) {
		super(par1, Material.wood, prefix);
	}

}
