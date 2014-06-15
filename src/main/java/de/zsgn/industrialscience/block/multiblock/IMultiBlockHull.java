package de.zsgn.industrialscience.block.multiblock;

import de.zsgn.industrialscience.tileentity.TileEntityMultiBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public abstract class IMultiBlockHull extends Block{

    protected IMultiBlockHull(Material p_i45386_1_)
    {
        super(p_i45386_1_);
    }

    /**
     * Called whenever the block is added into the world. Args: world, x, y, z
     */
    public void onBlockAdded(World p_149726_1_, int p_149726_2_, int p_149726_3_, int p_149726_4_)
    {
        super.onBlockAdded(p_149726_1_, p_149726_2_, p_149726_3_, p_149726_4_);
    }

    public void breakBlock(World world, int x, int y, int z, Block p_149749_5_, int p_149749_6_)
    {
        if(world.getTileEntity(x, y, z) instanceof TileEntityMultiBlock){
            ((TileEntityMultiBlock)world.getTileEntity(x, y, z)).destroyStructure();
        }
        super.breakBlock(world, x, y, z, p_149749_5_, p_149749_6_);
    }

    public boolean onBlockEventReceived(World p_149696_1_, int p_149696_2_, int p_149696_3_, int p_149696_4_, int p_149696_5_, int p_149696_6_)
    {
        super.onBlockEventReceived(p_149696_1_, p_149696_2_, p_149696_3_, p_149696_4_, p_149696_5_, p_149696_6_);
        TileEntity tileentity = p_149696_1_.getTileEntity(p_149696_2_, p_149696_3_, p_149696_4_);
        return tileentity != null ? tileentity.receiveClientEvent(p_149696_5_, p_149696_6_) : false;
    }

    public abstract TileEntityMultiBlock createNewTileEntity(World world,
            int blockMetadata) ;

}
