package industrialscience.modules.mining.tileentities.drill;

import net.minecraft.block.material.Material;

public class IronDrillTileEntity extends DrillTileEntity {

	@Override
	protected boolean isDrillAbleToMineBlock(Material material) {
		return true;
	}

}
