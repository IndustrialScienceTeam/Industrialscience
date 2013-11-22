package industrialscience.modules.research;

import net.minecraft.block.material.Material;
import industrialscience.blocksystem.ISBlock;
import industrialscience.blocksystem.ISModuleBlock;
import industrialscience.modules.research.frontend.blocks.CopierISBlock;
import industrialscience.modules.research.frontend.blocks.ResearchDeskISBlock;

public class ResearchModuleBlock extends ISModuleBlock {
	public static final int RESEARCHDESKMETAID=0;
	public static final int COPIERMETAID = 1;
	
	public ResearchModuleBlock(int par1, String prefix) {
		super(par1, Material.wood, prefix);
		blocks=new ISBlock[2];
		blocks[RESEARCHDESKMETAID]=new ResearchDeskISBlock();
		blocks[COPIERMETAID]=new CopierISBlock();
	}

}
