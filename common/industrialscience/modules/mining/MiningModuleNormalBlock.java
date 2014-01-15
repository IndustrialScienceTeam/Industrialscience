package industrialscience.modules.mining;

import net.minecraft.block.material.Material;
import industrialscience.blocksystem.ModelISBlock;
import industrialscience.blocksystem.ISModuleNormalBlock;
import industrialscience.blocksystem.NormalISBlock;
import industrialscience.modules.MiningModule;
import industrialscience.modules.mining.blocks.MinerBasicMachine;

public class MiningModuleNormalBlock extends ISModuleNormalBlock {

	public final int MINERBASICMACHINE=0;
	public MiningModuleNormalBlock(int par1,
			String prefix) {
		super(par1, Material.iron, prefix);
		blocks=new NormalISBlock[1];
		blocks[0]=new MinerBasicMachine();
	}

}
