package industrialscience.modules.mining;

import industrialscience.blocksystem.ISModuleNormalBlock;
import industrialscience.blocksystem.NormalISBlock;
import industrialscience.modules.mining.blocks.MinerBasicMachine;
import net.minecraft.block.material.Material;

public class MiningModuleNormalBlock extends ISModuleNormalBlock {

	public final int MINERBASICMACHINE=0;
	public MiningModuleNormalBlock(int par1,
			String prefix) {
		super(par1, Material.iron, prefix);
		blocks=new NormalISBlock[1];
		blocks[0]=new MinerBasicMachine();
	}

}
