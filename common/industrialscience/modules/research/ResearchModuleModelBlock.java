package industrialscience.modules.research;

import industrialscience.blocksystem.ISBlock;
import industrialscience.blocksystem.ISModuleModelBlock;
import industrialscience.modules.research.frontend.blocks.CopierISBlock;
import industrialscience.modules.research.frontend.blocks.ResearchDeskISBlock;
import net.minecraft.block.material.Material;

public class ResearchModuleModelBlock extends ISModuleModelBlock {
	public static final int RESEARCHDESKMETAID=0;
	public static final int COPIERMETAID = 1;
	
	public ResearchModuleModelBlock(int par1, String prefix) {
		super(par1, Material.wood, prefix);
		blocks=new ISBlock[2];
		blocks[RESEARCHDESKMETAID]=new ResearchDeskISBlock();
		blocks[COPIERMETAID]=new CopierISBlock();
	}

}
