package industrialscience.modules;

import net.minecraft.block.material.Material;
import industrialscience.blocksystem.ISModuleModelBlock;
import industrialscience.blocksystem.ModelISBlock;
import industrialscience.modules.mining.blocks.DrillingPipeBlock;

public class MiningModuleModelBlock extends ISModuleModelBlock {

	public MiningModuleModelBlock(int par1, String prefix) {
		super(par1, Material.iron, prefix);
		blocks = new ModelISBlock[1];
		blocks[0]=new DrillingPipeBlock();
	}

}
