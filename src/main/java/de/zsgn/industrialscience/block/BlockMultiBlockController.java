package de.zsgn.industrialscience.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import de.zsgn.industrialscience.AbsoluteCoordinate;
import de.zsgn.industrialscience.MultiBlockStructure;
import de.zsgn.industrialscience.tileentity.multiblock.TileEntityMultiBlock;
import de.zsgn.industrialscience.tileentity.multiblock.TileEntityMultiBlockController;

public abstract class BlockMultiBlockController extends BlockContainer {
    protected MultiBlockStructure structure;
    protected Block[] ValidBlocks={};
    protected BlockMultiBlockController(Material p_i45386_1_, MultiBlockStructure structure) {
        super(p_i45386_1_);
        this.structure=structure;
    } 
    @Override
    public boolean onBlockActivated(World world, int x,
            int y, int z, EntityPlayer player,
            int side, float xOffset, float yOffset,
            float zOffset) {
        if(!world.isRemote && world.getTileEntity(x, y, z) instanceof TileEntityMultiBlockController){
            TileEntityMultiBlockController masterTileEntity = (TileEntityMultiBlockController) world.getTileEntity(x, y, z);
            AbsoluteCoordinate[] blocks = structure.structureTest(world, x, y, z, ForgeDirection.getOrientation(side), ValidBlocks);
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
            return true;
        }
        return false;
    }
    public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLiving par5EntityLiving, ItemStack par6ItemStack)
    {
        int l = MathHelper.floor_double(par5EntityLiving.rotationYaw * 4.0F / 360.0F + 2.5D) & 3;
        par1World.setBlockMetadataWithNotify(par2, par3, par4, l, 2);
    }



}
