package industrialscience.blocksystem;

import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import cpw.mods.fml.client.registry.ClientRegistry;

public class ISModuleModelBlock extends ISModuleBlock {
	 public ISModuleModelBlock(int par1, Material par2Material, String prefix) {
		super(par1, par2Material, prefix);
	}
	@Override
	public void breakBlock(World world, int x, int y, int z, int i, int j) {
		blocks[world.getBlockMetadata(x, y, z)].breakBlock(world, x, y, z, i, j);
	}
	    @Override
	    public int damageDropped (int metadata) {
	    	return metadata;
	    }

	    @Override
		public String getIDName(int itemDamage) {
			return prefix+"."+blocks[itemDamage].getIdName();
		}

	    @Override
		public int getRenderType() {
	        return -1;
	    }


	    @Override
		    public boolean isOpaqueCube() {
		        return false;
		    }

	    @Override
		public boolean onBlockActivated(World par1World, int par2, int par3,
	            int par4, EntityPlayer par5EntityPlayer, int par6, float par7,
	            float par8, float par9) {
	        return blocks[par1World.getBlockMetadata(par2, par3, par4)].onBlockActivated(par1World, par2, par3, par4,
	                par5EntityPlayer, par6, par7, par8, par9);
	    }
	    public void registerRenderers() {
	        try {

		        for (int i=0;i<blocks.length;i++) {
		        	ModelISBlock typ=(ModelISBlock)blocks[i];
	                ClientRegistry
	                        .bindTileEntitySpecialRenderer(typ.getTileEntity(),
	                                typ.getRenderer().newInstance());
	            }
	        } catch (InstantiationException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        } catch (IllegalAccessException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	    }
		@Override
		public boolean renderAsNormalBlock() {
	        return false;
	    }
	    
}
