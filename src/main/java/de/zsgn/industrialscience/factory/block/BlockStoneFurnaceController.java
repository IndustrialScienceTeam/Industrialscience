package de.zsgn.industrialscience.factory.block;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import de.zsgn.industrialscience.IndustrialScience;
import de.zsgn.industrialscience.factory.tileentity.TileEntityMultiBlockFurnace;
import de.zsgn.industrialscience.util.MultiBlockStructure;
import de.zsgn.industrialscience.util.RelativeCoordinate;

public class BlockStoneFurnaceController extends IBlockMultiBlockController {
    public BlockStoneFurnaceController() {
        super(Material.rock, MultiBlockStructure.FURNACE_TIER1,
                IndustrialScience.MODID
                        + ":"
                        + IndustrialScience.getInstance().getFactoryModule()
                                .getBlockStonehull().getUnlocalizedName()
                                .substring(5));
        validBlocks = new Block[] {
                this,
                IndustrialScience.getInstance().getFactoryModule()
                        .getBlockStonehull() };
        this.setCreativeTab(IndustrialScience.getInstance().getCreativetab());
        this.setBlockName("tier1stonefurnace");
        this.setHardness(3.0F);
        this.setTickRandomly(true);
    }

    @Override
    public TileEntity createNewTileEntity(World var1, int var2) {
        return new TileEntityMultiBlockFurnace(20, new RelativeCoordinate[] {
                new RelativeCoordinate(1, 0, 1),
                new RelativeCoordinate(-1, 0, 1),
                new RelativeCoordinate(0, 0, 2),
                new RelativeCoordinate(0, 1, 1),
                new RelativeCoordinate(0, -1, 1) }, new RelativeCoordinate[] {});
    }
    @SideOnly(Side.CLIENT)
    @Override
    public void randomDisplayTick(World world, int x, int y, int z,
            Random random) {
        if(random.nextBoolean()&&isActive(world, x, y, z)){
        ForgeDirection right= getFacingDir(world, x, y, z).getRotation(ForgeDirection.DOWN);
        ForgeDirection front= getFacingDir(world, x, y, z);
        float yOffset=0.4375F+0.0625F*random.nextInt(6);
        float sideOffset=0.125F+0.0625F*(random.nextInt(11)+1);
        world.spawnParticle("flame", x+(Math.abs(right.offsetX)*sideOffset)+(front.offsetX>0?1:0), y+yOffset, z+(Math.abs(right.offsetZ)*sideOffset)+(front.offsetZ>0?1:0), 0, 0, 0);
        }
    }

}
