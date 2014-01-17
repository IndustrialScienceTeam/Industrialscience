package tconstruct.library.blocks;

import java.util.Random;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import tconstruct.library.util.IActiveLogic;
import tconstruct.library.util.IFacingLogic;

public abstract class InventoryBlock extends BlockContainer
{
    public static boolean isActive (IBlockAccess world, int x, int y, int z)
    {
        TileEntity logic = world.getBlockTileEntity(x, y, z);
        if (logic instanceof IActiveLogic)
        {
            return ((IActiveLogic) logic).getActive();
        }
        return false;
    }

    /* Textures */
    public Icon[] icons;

    protected Random rand = new Random();

    int side = -1;

    protected InventoryBlock(int id, Material material)
    {
        super(id, material);
    }

    @Override
    public void breakBlock (World par1World, int x, int y, int z, int par5, int meta)
    {
        TileEntity te = par1World.getBlockTileEntity(x, y, z);

        if (te != null && te instanceof InventoryLogic)
        {
            InventoryLogic logic = (InventoryLogic) te;
            for (int iter = 0; iter < logic.getSizeInventory(); ++iter)
            {
                ItemStack stack = logic.getStackInSlot(iter);

                if (stack != null && logic.canDropInventorySlot(iter))
                {
                    float jumpX = rand.nextFloat() * 0.8F + 0.1F;
                    float jumpY = rand.nextFloat() * 0.8F + 0.1F;
                    float jumpZ = rand.nextFloat() * 0.8F + 0.1F;

                    while (stack.stackSize > 0)
                    {
                        int itemSize = rand.nextInt(21) + 10;

                        if (itemSize > stack.stackSize)
                        {
                            itemSize = stack.stackSize;
                        }

                        stack.stackSize -= itemSize;
                        EntityItem entityitem = new EntityItem(par1World, x + jumpX, y + jumpY, z + jumpZ, new ItemStack(stack.itemID,
                                itemSize, stack.getItemDamage()));

                        if (stack.hasTagCompound())
                        {
                            entityitem.getEntityItem().setTagCompound((NBTTagCompound) stack.getTagCompound().copy());
                        }

                        float offset = 0.05F;
                        entityitem.motionX = (float) rand.nextGaussian() * offset;
                        entityitem.motionY = (float) rand.nextGaussian() * offset + 0.2F;
                        entityitem.motionZ = (float) rand.nextGaussian() * offset;
                        par1World.spawnEntityInWorld(entityitem);
                    }
                }
            }
        }

        super.breakBlock(par1World, x, y, z, par5, meta);
    }

    /* Logic backend */
    @Override
	public TileEntity createNewTileEntity (World var1)
    {
        return null;
    }

    /* Inventory */

    @Override
	public abstract TileEntity createTileEntity (World world, int metadata);

    /* Placement */

    @Override
	public int damageDropped (int meta)
    {
        return meta;
    }

    public abstract Integer getGui (World world, int x, int y, int z, EntityPlayer entityplayer);

    public abstract Object getModInstance ();

    public abstract String[] getTextureNames ();

    @Override
    public boolean onBlockActivated (World world, int x, int y, int z, EntityPlayer player, int side, float clickX, float clickY, float clickZ)
    {
        if (player.isSneaking())
            return false;

        Integer integer = getGui(world, x, y, z, player);
        if (integer == null || integer == -1)
        {
            return false;
        }
        else
        {
            if (!world.isRemote)
                player.openGui(getModInstance(), integer, world, x, y, z);
            return true;
        }
    }

    //This class does not have an actual block placed in the world
    @Override
    public int onBlockPlaced (World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int meta)
    {
        this.side = side;
        return meta;
    }

    @Override
    public void onBlockPlacedBy (World world, int x, int y, int z, EntityLivingBase entityliving, ItemStack stack)
    {
        TileEntity logic = world.getBlockTileEntity(x, y, z);
        if (logic instanceof IFacingLogic)
        {
            IFacingLogic direction = (IFacingLogic) logic;
            if (side != -1)
            {
                direction.setDirection(side);
                side = -1;
            }
            if (entityliving == null)
            {
                direction.setDirection(0F, 0F, null);
            }
            else
            {
                direction.setDirection(entityliving.rotationYaw * 4F, entityliving.rotationPitch, entityliving);
            }
        }

        if (stack.hasDisplayName())
        {
            ((InventoryLogic) world.getBlockTileEntity(x, y, z)).setInvName(stack.getDisplayName());
        }
    }

    @Override
    public void registerIcons (IconRegister iconRegister)
    {
        String[] textureNames = getTextureNames();
        this.icons = new Icon[textureNames.length];

        for (int i = 0; i < this.icons.length; ++i)
        {
            this.icons[i] = iconRegister.registerIcon("tinker:" + textureNames[i]);
        }
    }
}
