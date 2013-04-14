package mod.industrialscience.modules.fishing;

import java.util.Random;

import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public class FishtrapBlock extends BlockContainer {
	private Icon texture;
	private static Material material=Material.wood;
	public FishtrapBlock(int id) {
		super(id, material);
		setUnlocalizedName("Basic Fishtrap");
		LanguageRegistry.addName(this, "Basic Fishtrap");
		setTickRandomly(true);
	}
	@Override
	public TileEntity createNewTileEntity(World world) {
		return new Fishtraptile();
	}
	public boolean hasTileEntity(int metadata)
	{
	    return true;
	}
    public boolean isOpaqueCube()
    {
        return false;
    }
	public void func_94332_a(IconRegister par1IconRegister)
	{
	this.texture= par1IconRegister.func_94245_a("trapdoor");
	}
	public Icon getBlockTextureFromSideAndMetadata(int i, int j){
		return texture;
		}
	public void updateTick(World world, int x, int y, int z, Random random){
		
	}
    public void breakBlock(World world, int x, int y, int z, int i, int j){
        dropItems(world, x, y, z);
        super.breakBlock(world, x, y, z, i, j);
        }
    private void dropItems(World world, int x, int y, int z){
        Random rand = new Random();
       
        TileEntity tile_entity = world.getBlockTileEntity(x, y, z);
       
        if(!(tile_entity instanceof IInventory)){
                return;
        }

        IInventory inventory = (IInventory) tile_entity;

        for(int i = 0; i < inventory.getSizeInventory(); i++){
                ItemStack item = inventory.getStackInSlot(i);
               
                if(item != null && item.stackSize > 0){
                float rz = rand.nextFloat() * 0.3F + 0.1F;
               
                EntityItem entity_item = new EntityItem(world, x, y , z + rz, new ItemStack(item.itemID, item.stackSize, item.getItemDamage()));
               
                if(item.hasTagCompound()){
                        entity_item.writeToNBT(((NBTTagCompound) item.getTagCompound().copy()));
                }

                float factor = 0.5F;
               
                entity_item.motionX = rand.nextGaussian() * factor;
                entity_item.motionY = rand.nextGaussian() * factor + 0.2F;
                entity_item.motionZ = rand.nextGaussian() * factor;
                world.spawnEntityInWorld(entity_item);
                item.stackSize = 0;
                }
                }
        }

}
