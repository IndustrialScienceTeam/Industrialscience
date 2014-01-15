package industrialscience.blocksystem;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public abstract class ModelISBlock extends ISBlock{
	protected Class<? extends TileEntitySpecialRenderer> renderer;	
	public ModelISBlock(Class<? extends TileEntity> tileEntity,
			String idName,Class<? extends TileEntitySpecialRenderer> renderer) {
		super(tileEntity,idName);
		this.renderer = renderer;
	}
	/**
	 * @return The renderer for this block.
	 */
	public Class<? extends TileEntitySpecialRenderer> getRenderer() {
		return renderer;
	}


}
