package de.zsgn.industrialscience.factory.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import de.zsgn.industrialscience.IndustrialScience;
import de.zsgn.industrialscience.factory.tileentity.TileEntityMultiBlock;

public abstract class IBlockMultiblockHull extends BlockContainer {

    public IBlockMultiblockHull(Material material) {
        super(material);
        this.setCreativeTab(IndustrialScience.getInstance().getCreativetab());
    }

    @Override
    public TileEntity createNewTileEntity(World world, int blockMetadata) {
        return new TileEntityMultiBlock();
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

}
