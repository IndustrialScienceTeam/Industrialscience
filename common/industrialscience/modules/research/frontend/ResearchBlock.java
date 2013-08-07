package industrialscience.modules.research.frontend;

import industrialscience.IndustrialScience;

import java.util.List;
import java.util.Random;

import cpw.mods.fml.common.network.FMLNetworkHandler;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

// Thx to iron chest(cpw) for the idea of this system. You rock.
public class ResearchBlock extends BlockContainer {
    public ResearchBlock(int id) {
        super(id, Material.wood);
        setUnlocalizedName("ResearchBlock");
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public void registerIcons(IconRegister par1IconRegister) {
        ResearchBlockType.registerIcons(par1IconRegister);
    }

    @Override
    public TileEntity createNewTileEntity(World world) {
        return null;
    }

    @Override
    public TileEntity createTileEntity(World world, int metadata) {
        return ResearchBlockType.getEntity(metadata);
    }

    @Override
    public boolean hasTileEntity(int metadata) {
        return true;
    }

    @Override
    public Icon getIcon(int i, int j) {
        return ResearchBlockType.getIcon(i, j);
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs,
            List par3List) {
        for (int i = 0; i < ResearchBlockType.values().length; i++) {
            par3List.add(new ItemStack(par1, 1, i));
        }

    }
    @Override
    public void breakBlock(World world, int x, int y, int z, int i, int j) {
    	if(!world.isRemote){
     	   ResearchBlockType.values()[world.getBlockMetadata(x, y, z)].breakBlock(world, x, y, z, i, j);
        }
    }
    @Override
    public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
    {
       if(!par1World.isRemote){
    	   return ResearchBlockType.values()[par1World.getBlockMetadata(par2, par3, par4)].onBlockActivated(par1World, par2, par3, par4, par5EntityPlayer, par6, par7, par8, par9);
       }
       return true;
    }
    public int getRenderType(){
    	  return -1;
    	        }
    public boolean renderAsNormalBlock()
    {
            return false;
    }
    

}
