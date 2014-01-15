package industrialscience.blocksystem;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public abstract class ISBlock {
	protected Class<? extends TileEntity> tileEntity;
	protected String idName;
	
	protected ISBlock(Class<? extends TileEntity> tileEntity, String idName) {
		super();
		this.tileEntity = tileEntity;
		this.idName = idName;
	}
	
	
	public boolean onBlockActivated(World par1World, int par2, int par3,
			int par4, EntityPlayer par5EntityPlayer, int par6, float par7,
			float par8, float par9) {
		return false;
	}
	public void breakBlock(World world, int x, int y, int z, int i, int j) {
	}
	/**
	 * @return The TileEntity for this block.
	 */
	public Class<? extends TileEntity> getTileEntity() {
		return tileEntity;
	}
	/**
	 * @return The unlocalized name-part for this block.
	 */
	public String getIdName() {
		return idName;
	}


}
