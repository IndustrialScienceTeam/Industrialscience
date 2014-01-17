package industrialscience.modules.mining.blocks;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import industrialscience.blocksystem.ModelISBlock;

public class Drill extends ModelISBlock {

	public Drill(Class<? extends TileEntity> tileEntity, String idName,
			Class<? extends TileEntitySpecialRenderer> renderer) {
		super(tileEntity, idName, renderer);
	}

}
