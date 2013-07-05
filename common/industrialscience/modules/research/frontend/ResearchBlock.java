package industrialscience.modules.research.frontend;

import industrialscience.modules.ISAbstractModule;

import java.util.List;
import java.util.Random;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
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
        setCreativeTab(ISAbstractModule.getCreativeTab());
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

    private void dropItems(World world, int x, int y, int z) {
        Random rand = new Random();

        TileEntity tile_entity = world.getBlockTileEntity(x, y, z);

        if (!(tile_entity instanceof IInventory))
            return;

        IInventory inventory = (IInventory) tile_entity;

        for (int i = 0; i < inventory.getSizeInventory(); i++) {
            ItemStack item = inventory.getStackInSlot(i);

            if (item != null && item.stackSize > 0) {
                float rx = rand.nextFloat() * 0.3F + 0.1F;
                float ry = rand.nextFloat() * 0.3F + 0.1F;
                float rz = rand.nextFloat() * 0.3F + 0.1F;

                EntityItem entity_item = new EntityItem(world, x + rx, y + ry,
                        z + rz, new ItemStack(item.itemID, item.stackSize,
                                item.getItemDamage()));

                if (item.hasTagCompound()) {
                    entity_item.writeToNBT((NBTTagCompound) item
                            .getTagCompound().copy());
                }

                float factor = 0.5F;

                entity_item.motionX = rand.nextGaussian() * factor;
                entity_item.motionY = rand.nextGaussian() * factor;
                entity_item.motionZ = rand.nextGaussian() * factor;
                world.spawnEntityInWorld(entity_item);
                item.stackSize = 0;
            }
        }

    }

    @Override
    public void breakBlock(World world, int x, int y, int z, int i, int j) {
        dropItems(world, x, y, z);
        super.breakBlock(world, x, y, z, i, j);
    }

}
