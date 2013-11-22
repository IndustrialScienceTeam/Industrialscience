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

public abstract class ISModuleBlock extends BlockContainer {
	protected String prefix;
	protected ISBlock[] blocks= {};
	
	public ISModuleBlock(int par1, Material par2Material, String prefix) {
		super(par1, par2Material);
		this.prefix=prefix+".block";
		this.setUnlocalizedName(this.prefix);
	}
	 @Override
	    public boolean isOpaqueCube() {
	        return false;
	    }

	    @Override
	    public TileEntity createNewTileEntity(World world) {
	        return null;
	    }

	    @Override
	    public TileEntity createTileEntity(World world, int metadata) {
	    	 try {
		            TileEntity te = blocks[metadata].getTileEntity().newInstance();
		            return te;
		        } catch (InstantiationException e) {
		            e.printStackTrace();
		        } catch (IllegalAccessException e) {
		            e.printStackTrace();
		        }
		        return null;
	    }

	    @Override
	    public boolean hasTileEntity(int metadata) {
	        return true;
	    }
	    @SuppressWarnings({ "rawtypes", "unchecked" })
	    @Override
	    public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs,
	            List par3List) {
	        for (int i = 0; i < blocks.length; i++) {
	            par3List.add(new ItemStack(par1, 1, i));
	        }

	    }
	    public void register() {
	        GameRegistry.registerBlock(this, ISModuleBlockItem.class,
	                prefix);
	        for (int i=0;i<blocks.length;i++) {
	        	ISBlock typ=blocks[i];
	            GameRegistry.registerTileEntity(
	                    typ.getTileEntity(), prefix + typ.getIdName());
	            LanguageRegistry.addName(
	                    new ItemStack(this, 1, i),
	                    typ.getHumanName());
	        }

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

}
