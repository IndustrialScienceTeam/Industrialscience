package de.zsgn.industrialscience.block;

import de.zsgn.industrialscience.MultiBlockStructure;
import de.zsgn.industrialscience.block.multiblock.IMultiBlockHull;
import de.zsgn.industrialscience.tileentity.multiblock.TileEntityMultiBlock;
import de.zsgn.industrialscience.tileentity.multiblock.TileEntityMultiBlockController;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

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
            Vec3[] blocks = structure.structureTest(world, x, y, z, ForgeDirection.getOrientation(side), ValidBlocks);
            if(blocks == null){
                return false;
            }
            for (int i = 0; i < blocks.length; i++) {
                Vec3 blockcord = blocks[i];
                Block block = world.getBlock((int)blockcord.xCoord, (int)blockcord.yCoord, (int)blockcord.zCoord);
                if(block instanceof IMultiBlockHull){
                    IMultiBlockHull tileEntityProvider = (IMultiBlockHull) block;
                    TileEntityMultiBlock tileentity = tileEntityProvider.createNewTileEntity(world, world.getBlockMetadata((int)blockcord.xCoord, (int)blockcord.yCoord, (int)blockcord.zCoord));
                    tileentity.setController(x,y,z);
                    tileentity.setActivepart(true);
                    world.setTileEntity((int)blockcord.xCoord, (int)blockcord.yCoord, (int)blockcord.zCoord, tileentity);
                }
            }
            masterTileEntity.setStructure(blocks);
            return true;
        }
        return false;
    }
    public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLiving par5EntityLiving, ItemStack par6ItemStack)
    {
            int l = MathHelper.floor_double((double)(par5EntityLiving.rotationYaw * 4.0F / 360.0F) + 2.5D) & 3;
            par1World.setBlockMetadataWithNotify(par2, par3, par4, l, 2);
    }
    


}
