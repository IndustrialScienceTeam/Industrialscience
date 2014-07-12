package de.zsgn.industrialscience.factory.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import de.zsgn.industrialscience.IndustrialScience;
import de.zsgn.industrialscience.factory.tileentity.TileEntityMultiBlock;
import de.zsgn.industrialscience.factory.tileentity.controllers.ITileEntityMultiBlockController;
import de.zsgn.industrialscience.util.AbsoluteCoordinate;
import de.zsgn.industrialscience.util.MultiBlockStructure;

public abstract class IBlockMultiBlockController extends BlockContainer {
    protected String sidetexturestring;
    @SideOnly(Side.CLIENT)
    protected IIcon front;
    @SideOnly(Side.CLIENT)
    protected IIcon frontActive;
    @SideOnly(Side.CLIENT)
    protected IIcon sides;

    protected IBlockMultiBlockController(Material material,String sidetexturestring) {
        super(material);
        this.sidetexturestring = sidetexturestring;
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z,
            EntityPlayer player, int side, float xOffset, float yOffset,
            float zOffset) {
        if (!world.isRemote) {
            if(world.getTileEntity(x, y, z)instanceof ITileEntityMultiBlockController){
                if (!((ITileEntityMultiBlockController)world.getTileEntity(x, y, z)).isActivePart()) {
            return this.testAndSetStructure(world, x, y, z);
            }else if(world.getTileEntity(x, y, z) instanceof IInventory){
                IInventory inventory=(IInventory)world.getTileEntity(x, y, z);
                boolean notempty = false;
                String[] items=new String[inventory.getSizeInventory()];
                for (int i = 0; i < inventory.getSizeInventory(); i++) {
                    if(inventory.getStackInSlot(i)!=null){
                        items[i]=inventory.getStackInSlot(i).getDisplayName();
                        notempty=true;
                    }
                }
                player.addChatMessage(new ChatComponentText(notempty?StatCollector.translateToLocal("i_can_see"):StatCollector.translateToLocal("appears_empty")));
                for (String item : items) {
                    if(item!=null){
                        player.addChatMessage(new ChatComponentText(item));
                    }
                }
            }
            }
        }
        return false;
    }
    private boolean testAndSetStructure(World world, int x, int y, int z) {
        if (world.getTileEntity(x, y, z) instanceof ITileEntityMultiBlockController) {
            ITileEntityMultiBlockController masterTileEntity = (ITileEntityMultiBlockController) world
                    .getTileEntity(x, y, z);
            if (!masterTileEntity.isActivePart()) {
                AbsoluteCoordinate[] blocks = getMultiBlockStructure().structureTest(world, x,
                        y, z, getFacingDir(world, x, y, z));
                if (blocks == null) {
                    return false;
                }
                for (AbsoluteCoordinate blockcord : blocks) {
                    if (world.getTileEntity(blockcord.xCoord, blockcord.yCoord,
                            blockcord.zCoord) instanceof TileEntityMultiBlock) {
                        TileEntityMultiBlock tileentity = (TileEntityMultiBlock) world
                                .getTileEntity(blockcord.xCoord,
                                        blockcord.yCoord, blockcord.zCoord);
                        tileentity.setController(x, y, z);
                        tileentity.setActivepart(true);
                        world.markBlockForUpdate(blockcord.xCoord,
                        blockcord.yCoord, blockcord.zCoord);
                    }
                }
                masterTileEntity.setStructure(blocks);
                return true;
            }
        }
        return false;

    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(IBlockAccess blockAccess, int x, int y, int z, int side) {
        if (side == getFacingDir(blockAccess, x, y, z).ordinal()) {
            if (isActive(blockAccess, x, y, z)) {
                return frontActive;
            } else {
                return front;
            }
        } else {
            return sides;
        }
    }

    // For the Item in the Inventory:
    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta) {
        if (side == 4) {
            return front;
        } else {
            return sides;
        }

    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister) {
        front = iconRegister.registerIcon(IndustrialScience.MODID + ":"
                + this.getUnlocalizedName().substring(5));
        frontActive = iconRegister.registerIcon(IndustrialScience.MODID + ":"
                + this.getUnlocalizedName().substring(5) + "_active");
        sides = iconRegister.registerIcon(sidetexturestring);
    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z,
            EntityLivingBase entityLivingBase, ItemStack itemStack) {
        if (!world.isRemote) {
            int l = MathHelper
                    .floor_double(entityLivingBase.rotationYaw * 4.0F / 360.0F + 2.5D) & 3;
            switch (l) {
            case 0:
                world.setBlockMetadataWithNotify(x, y, z, ForgeDirection
                        .getOrientation(2).getOpposite().ordinal() << 1, 2);
                break;
            case 1:
                world.setBlockMetadataWithNotify(x, y, z, ForgeDirection
                        .getOrientation(5).getOpposite().ordinal() << 1, 2);
                break;
            case 2:
                world.setBlockMetadataWithNotify(x, y, z, ForgeDirection
                        .getOrientation(3).getOpposite().ordinal() << 1, 2);
                break;
            case 3:
                world.setBlockMetadataWithNotify(x, y, z, ForgeDirection
                        .getOrientation(4).getOpposite().ordinal() << 1, 2);
                break;
            default:
                break;
            }
        }
    }

    @Override
    public void breakBlock(World world, int x, int y, int z, Block block,
            int meta) {
        if (!world.isRemote
                && world.getTileEntity(x, y, z) instanceof TileEntityMultiBlock
                && ((TileEntityMultiBlock) world.getTileEntity(x, y, z))
                        .isActivePart()) {
            ((TileEntityMultiBlock) world.getTileEntity(x, y, z))
                    .destroyStructure();
        }
        super.breakBlock(world, x, y, z, block, meta);
    }
    public static boolean isActive(World world, int x, int y, int z){
        return (world.getBlockMetadata(x, y, z) & 1) == 1;
    }
    public static boolean isActive(IBlockAccess world, int x, int y, int z){
        return (world.getBlockMetadata(x, y, z) & 1) == 1;
    }
    public static ForgeDirection getFacingDir(IBlockAccess world, int x, int y, int z){
        return ForgeDirection.getOrientation(world.getBlockMetadata(x, y, z) >> 1);
    }
    public static ForgeDirection getFacingDir(World world, int x, int y, int z){
        return ForgeDirection.getOrientation(world.getBlockMetadata(x, y, z) >> 1);
    }
    protected abstract MultiBlockStructure getMultiBlockStructure();

}
