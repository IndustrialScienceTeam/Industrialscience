package de.zsgn.industrialscience.factory.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import de.zsgn.industrialscience.AbsoluteCoordinate;
import de.zsgn.industrialscience.IndustrialScience;
import de.zsgn.industrialscience.factory.tileentity.TileEntityChimney;

public class BlockChimney extends BlockContainer {

    public BlockChimney() {
        super(Material.iron);
        this.setCreativeTab(IndustrialScience.getInstance().getCreativetab());
        this.setBlockName("chimney");
        this.setHardness(2.0F);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int blockMetadata) {
        return new TileEntityChimney();
    }

    @Override
    public void breakBlock(World world, int x, int y, int z, Block block,
            int meta) {
        if (!world.isRemote
                && world.getTileEntity(x, y, z) instanceof TileEntityChimney) {

        }
        super.breakBlock(world, x, y, z, block, meta);
    }

    protected AbsoluteCoordinate getMultiBlockInterface(World world, int x,
            int y, int z) {

        return new AbsoluteCoordinate(x, y - 1, z);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void randomDisplayTick(World world, int x, int y, int z,
            Random random) {
        world.spawnParticle("smoke", x, y, z, 0, 0, 0);
    }

    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }

    @Override
    public int getRenderType() {
        return -1;
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }
}
