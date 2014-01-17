package industrialscience.blocksystem;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public abstract class ModelISBlock extends ISBlock{
	protected ModelISBlock(Class<? extends TileEntity> tileEntity, String idName) {
		super(tileEntity, idName);
	}
	@SideOnly(Side.CLIENT)
	protected Class<? extends TileEntitySpecialRenderer> renderer;	
	public void setRenderer(Class<? extends TileEntitySpecialRenderer> renderer) {
		this.renderer = renderer;
	}
	/**
	 * @return The renderer for this block.
	 */
	public Class<? extends TileEntitySpecialRenderer> getRenderer() {
		return renderer;
	}


}
