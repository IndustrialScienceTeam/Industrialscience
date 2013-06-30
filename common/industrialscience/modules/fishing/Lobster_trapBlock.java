package industrialscience.modules.fishing;

import industrialscience.modules.ISAbstractModule;

import java.util.Random;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class Lobster_trapBlock extends BlockContainer {
    protected Icon texture;
    protected final Random random = new Random();
    protected int fishamout = 5;
    protected int neededwater = 1;
    protected int waterforextrafish = 1;
    protected int range = 1;
    protected static Material material = Material.wood;

    public Lobster_trapBlock(int id) {
        super(id, material);
        setUnlocalizedName("Lobster trap");
        LanguageRegistry.addName(this, "Lobster trap");
        setTickRandomly(true);
        setCreativeTab(ISAbstractModule.getCreativeTab());
    }

    @Override
    public TileEntity createNewTileEntity(World world) {
        return new Lobster_traptile();
    }

    @Override
    public boolean hasTileEntity(int metadata) {
        return true;
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public void registerIcons(IconRegister par1IconRegister) {
        texture = par1IconRegister
                .registerIcon("industrialscience:lobstertrap");
    }
    @Override
    public Icon getIcon(int i, int j) {
        return texture;
    }

    @Override
    public void updateTick(World world, int x, int y, int z, Random random) {
        TileEntity tile = world.getBlockTileEntity(x, y, z);
        if (!(tile instanceof Lobster_traptile))
            return;
        Lobster_traptile lobstertile = (Lobster_traptile) tile;
        lobstertile.addFish(fishamout, neededwater, range, waterforextrafish);

    }

    @Override
    public void breakBlock(World world, int x, int y, int z, int i, int j) {
        dropItems(world, x, y, z);
        super.breakBlock(world, x, y, z, i, j);
    }

    protected void dropItems(World par1World, int x, int y, int z) {
        TileEntity tileentity = par1World.getBlockTileEntity(x, y, z);
        if (tileentity instanceof IInventory) {
            IInventory INV = (IInventory) tileentity;
            for (int j1 = 0; j1 < INV.getSizeInventory(); ++j1) {
                ItemStack itemstack = INV.getStackInSlot(j1);

                if (itemstack != null) {
                    float f = random.nextFloat() * 0.8F + 0.1F;
                    float f1 = random.nextFloat() * 0.8F + 0.1F;
                    EntityItem entityitem;

                    for (float f2 = random.nextFloat() * 0.8F + 0.1F; itemstack.stackSize > 0; par1World
                            .spawnEntityInWorld(entityitem)) {
                        int k1 = random.nextInt(21) + 10;

                        if (k1 > itemstack.stackSize) {
                            k1 = itemstack.stackSize;
                        }

                        itemstack.stackSize -= k1;
                        entityitem = new EntityItem(par1World, x + f, y + f1, z
                                + f2, new ItemStack(itemstack.itemID, k1,
                                itemstack.getItemDamage()));
                        float f3 = 0.05F;
                        entityitem.motionX = (float) random.nextGaussian() * f3;
                        entityitem.motionY = (float) random.nextGaussian() * f3
                                + 0.5F;
                        entityitem.motionZ = (float) random.nextGaussian() * f3;

                        if (itemstack.hasTagCompound()) {
                            entityitem.getEntityItem().setTagCompound(
                                    (NBTTagCompound) itemstack.getTagCompound()
                                            .copy());
                        }
                    }
                }
            }

        }
    }
}