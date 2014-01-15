package industrialscience.blocksystem;

import java.util.List;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public class ISModuleModelBlock extends ISModuleBlock {
	 public ISModuleModelBlock(int par1, Material par2Material, String prefix) {
		super(par1, par2Material, prefix);
	}
	@Override
	    public boolean isOpaqueCube() {
	        return false;
	    }
	    public void registerRenderers() {
	        try {

		        for (int i=0;i<blocks.length;i++) {
		        	ISBlock typ=blocks[i];
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

	    public boolean onBlockActivated(World par1World, int par2, int par3,
	            int par4, EntityPlayer par5EntityPlayer, int par6, float par7,
	            float par8, float par9) {
	        return blocks[par1World.getBlockMetadata(par2, par3, par4)].onBlockActivated(par1World, par2, par3, par4,
	                par5EntityPlayer, par6, par7, par8, par9);
	    }

	    public void breakBlock(World world, int x, int y, int z, int i, int j) {
	    	blocks[world.getBlockMetadata(x, y, z)].breakBlock(world, x, y, z, i, j);
	    }


	    @Override
		public int getRenderType() {
	        return -1;
	    }

	    @Override
		public boolean renderAsNormalBlock() {
	        return false;
	    }
	    @Override
	    public int damageDropped (int metadata) {
	    	return metadata;
	    }
		public String getIDName(int itemDamage) {
			return prefix+"."+blocks[itemDamage].getIdName();
		}
	    
}
