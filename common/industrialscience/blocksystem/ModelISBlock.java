package industrialscience.blocksystem;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public abstract class ModelISBlock extends ISBlock{
	@SideOnly(Side.CLIENT)
	protected Class<? extends TileEntitySpecialRenderer> renderer;
	protected ModelISBlock(Class<? extends TileEntity> tileEntity, String idName) {
		super(tileEntity, idName);
	}	
	/**
	 * @return The renderer for this block.
	 */
	public Class<? extends TileEntitySpecialRenderer> getRenderer() {
		return renderer;
	}
	public void setRenderer(Class<? extends TileEntitySpecialRenderer> renderer) {
		this.renderer = renderer;
	}


}
