package industrialscience.modules.mining.blocks;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.util.ResourceLocation;
import industrialscience.blocksystem.ModelISBlock;
import industrialscience.blocksystem.NormalISBlock;
import industrialscience.modules.mining.tileentities.BasicMinerMachineTileEntity;

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
