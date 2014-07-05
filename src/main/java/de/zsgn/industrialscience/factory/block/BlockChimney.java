package de.zsgn.industrialscience.factory.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import de.zsgn.industrialscience.IndustrialScience;
import de.zsgn.industrialscience.factory.tileentity.IChimneySupport;
import de.zsgn.industrialscience.factory.tileentity.TileEntityChimney;
import de.zsgn.industrialscience.factory.tileentity.TileEntityMultiBlock;
import de.zsgn.industrialscience.util.AbsoluteCoordinate;

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

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z,
            EntityLivingBase entityLivingBase, ItemStack itemStack) {
        if(!world.isRemote&&world.getTileEntity(x, y-1, z) instanceof TileEntityMultiBlock){
            TileEntityMultiBlock connector=(TileEntityMultiBlock)world.getTileEntity(x, y-1, z);
            if(world.getTileEntity(connector.getMasterx(),connector.getMastery(),connector.getMasterz())instanceof IChimneySupport){
                IChimneySupport master= (IChimneySupport)world.getTileEntity(connector.getMasterx(),connector.getMastery(),connector.getMasterz());
                master.addChimney(new AbsoluteCoordinate(x, y, z));
            }
        }
    }
    @SideOnly(Side.CLIENT)
    @Override
    public void randomDisplayTick(World world, int x, int y, int z,
            Random random) {
        System.err.println(world.getBlockMetadata(x, y, z));
        //if(world.getBlockMetadata(x, y, z)==1){do particle krams}
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
