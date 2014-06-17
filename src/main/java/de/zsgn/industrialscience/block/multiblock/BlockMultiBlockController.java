package de.zsgn.industrialscience.block.multiblock;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import de.zsgn.industrialscience.AbsoluteCoordinate;
import de.zsgn.industrialscience.IndustrialScience;
import de.zsgn.industrialscience.MultiBlockStructure;
import de.zsgn.industrialscience.tileentity.multiblock.TileEntityMultiBlock;
import de.zsgn.industrialscience.tileentity.multiblock.TileEntityMultiBlockController;

public abstract class BlockMultiBlockController extends BlockContainer {
    protected MultiBlockStructure structure;
    protected String sidetexturestring;
    protected Block[] ValidBlocks={};
    @SideOnly(Side.CLIENT)
    protected IIcon front;
    @SideOnly(Side.CLIENT)
    protected IIcon frontActive;
    @SideOnly(Side.CLIENT)
    protected IIcon sides;
    protected BlockMultiBlockController(Material p_i45386_1_, MultiBlockStructure structure, String sidetexturestring) {
        super(p_i45386_1_);
        this.structure=structure;
        this.sidetexturestring=sidetexturestring;
    } 
    @Override
    public boolean onBlockActivated(World world, int x,
            int y, int z, EntityPlayer player,
            int side, float xOffset, float yOffset,
            float zOffset) {
        if(!world.isRemote){
           return testStructure(world,x,y,z,player);
        }
        return false;
    }
    
    private boolean testStructure(World world, int x, int y, int z,
            EntityPlayer player) {
        if(world.getTileEntity(x, y, z) instanceof TileEntityMultiBlockController){
        TileEntityMultiBlockController masterTileEntity = (TileEntityMultiBlockController) world.getTileEntity(x, y, z);
        if(!masterTileEntity.isActivePart()){
        AbsoluteCoordinate[] blocks = structure.structureTest(world, x, y, z, ForgeDirection.getOrientation(world.getBlockMetadata(x, y, z)), ValidBlocks);
        if(blocks == null){
            return false;
        }
        for (int i = 0; i < blocks.length; i++) {
            AbsoluteCoordinate blockcord = blocks[i];
            Block block = world.getBlock(blockcord.xCoord,blockcord.yCoord, blockcord.zCoord);
            if(world.getTileEntity(blockcord.xCoord, blockcord.yCoord, blockcord.zCoord) instanceof TileEntityMultiBlock){
                TileEntityMultiBlock tileentity = (TileEntityMultiBlock) world.getTileEntity(blockcord.xCoord, blockcord.yCoord, blockcord.zCoord);
                tileentity.setController(x,y,z);
                tileentity.setActivepart(true);
            }
        }
        masterTileEntity.setStructure(blocks);
        player.addChatMessage(new ChatComponentText("Active"));
        return true;
        }
        }
        return false;
        
    }
    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(IBlockAccess blockAccess, int x, int y, int z, int side)
    {
        if(side==blockAccess.getBlockMetadata(x, y, z)){
            if(blockAccess.getTileEntity(x, y, z) instanceof TileEntityMultiBlockController &&((TileEntityMultiBlockController) blockAccess.getTileEntity(x, y, z)).isProcessing()){
                return frontActive;
            }else{
                return front;
            }
        }else{
            return sides;
        }
    }
    //For the Item in the Inventory:
    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta)
    {
        if(side==4){
            return front;
        }
        else{
            return sides;
        }
        
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister) {
        front=iconRegister.registerIcon(IndustrialScience.MODID + ":" + this.getUnlocalizedName().substring(5));
        frontActive=iconRegister.registerIcon(IndustrialScience.MODID + ":" + this.getUnlocalizedName().substring(5)+"_active");
        sides=iconRegister.registerIcon(sidetexturestring);
    }
    @Override
    public void onBlockPlacedBy(World world, int x,
            int y, int z, EntityLivingBase entityLivingBase,
            ItemStack itemStack) {
        if(!world.isRemote){
        int l = MathHelper.floor_double(entityLivingBase.rotationYaw * 4.0F / 360.0F + 2.5D) & 3;
        //The last number is the update flag(1 Blockupdate, 2 CLientupdate)
        switch (l) {
        case 0:
            world.setBlockMetadataWithNotify(x, y, z, ForgeDirection.getOrientation(2).getOpposite().ordinal(), 2);
            break;
        case 1:
            world.setBlockMetadataWithNotify(x, y, z, ForgeDirection.getOrientation(5).getOpposite().ordinal(), 2);
            break;
        case 2:
            world.setBlockMetadataWithNotify(x, y, z, ForgeDirection.getOrientation(3).getOpposite().ordinal(), 2);
            break;
        case 3:
            world.setBlockMetadataWithNotify(x, y, z, ForgeDirection.getOrientation(4).getOpposite().ordinal(), 2);
            break;
        default:
            break;
        }
        }
    }
    @Override
    public void breakBlock(World world, int x, int y,
            int z, Block block, int meta) {
        if(!world.isRemote&&world.getTileEntity(x, y, z)instanceof TileEntityMultiBlock && (((TileEntityMultiBlock)world.getTileEntity(x, y, z)).isActivePart())){
            ((TileEntityMultiBlock)world.getTileEntity(x, y, z)).destroyStructure();
        }
    }



}
