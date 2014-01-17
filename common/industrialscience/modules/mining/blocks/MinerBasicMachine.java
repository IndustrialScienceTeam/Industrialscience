package industrialscience.modules.mining.blocks;

import industrialscience.blocksystem.NormalISBlock;
import industrialscience.modules.mining.tileentities.BasicMinerMachineTileEntity;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;

public class MinerBasicMachine extends NormalISBlock {
	private Icon testingicon;
	public MinerBasicMachine() {
		super(BasicMinerMachineTileEntity.class, "BasicMinerMachine");
	}

	@Override
	public Icon getIcon(int side) {
		return testingicon;
	}

	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		testingicon=par1IconRegister.registerIcon("iron_block");
	}

}
