package industrialscience.blocksystem;

import java.util.List;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.registry.GameRegistry;

public abstract class ISModuleBlock extends BlockContainer {
	protected ISBlock[] blocks= {};
	protected String prefix;
	
	public ISModuleBlock(int par1, Material par2Material, String prefix) {
		super(par1, par2Material);
		this.prefix=prefix+".block";
	}
    @Override
	public void breakBlock(World world, int x, int y, int z, int i, int j) {
    	blocks[world.getBlockMetadata(x, y, z)].breakBlock(world, x, y, z, i, j);
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
    public int damageDropped (int metadata) {
    	return metadata;
    }
    public String getIDName(int itemDamage) {
		return prefix+"."+blocks[itemDamage].getIdName();
	}
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs,
            List par3List) {
        for (int i = 0; i < blocks.length; i++) {
            par3List.add(new ItemStack(par1, 1, i));
        }

    }

    @Override
    public boolean hasTileEntity(int metadata) {
        return metadata<blocks.length;
    }
    @Override
	public boolean onBlockActivated(World par1World, int par2, int par3,
            int par4, EntityPlayer par5EntityPlayer, int par6, float par7,
            float par8, float par9) {
        return blocks[par1World.getBlockMetadata(par2, par3, par4)].onBlockActivated(par1World, par2, par3, par4,
                par5EntityPlayer, par6, par7, par8, par9);
    }
	public void register() {
        GameRegistry.registerBlock(this, ISModuleBlockItem.class,
                prefix+"."+this.getClass().getName());
        for (int i=0;i<blocks.length;i++) {
        	ISBlock typ=blocks[i];
            GameRegistry.registerTileEntity(
                    typ.getTileEntity(), prefix + ".tileEntity."+typ.getIdName());
        }
    }

}
