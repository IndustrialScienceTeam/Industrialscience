package industrialscience.blocksystem;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;

public abstract class NormalISBlock extends ISBlock{

	protected NormalISBlock(Class<? extends TileEntity> tileEntity,
			String idName) {
		super(tileEntity, idName);
	}

	public abstract Icon getIcon(int side);

	public abstract void registerIcons(IconRegister par1IconRegister);

}
