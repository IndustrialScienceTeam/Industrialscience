package de.zsgn.industrialscience.factory.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import de.zsgn.industrialscience.IndustrialScience;
import de.zsgn.industrialscience.factory.tileentity.controllers.TileEntityMultiBlockElectricFurnace;
import de.zsgn.industrialscience.factory.tileentity.controllers.TileEntityMultiBlockFurnace;
import de.zsgn.industrialscience.util.MultiBlockStructure;
import de.zsgn.industrialscience.util.RelativeCoordinate;

public class BlockIronFurnaceController extends IBlockMultiBlockController {
    public BlockIronFurnaceController() {
        super(Material.rock,IndustrialScience.MODID
                        + ":"
                        + IndustrialScience.getInstance().getFactoryModule()
                                .getBlockIronhull().getUnlocalizedName()
                                .substring(5));
        this.setCreativeTab(IndustrialScience.getInstance().getCreativetab());
        this.setBlockName("tier1ironfurnace");
        this.setHardness(3.0F);
    }

    @Override
    public TileEntity createNewTileEntity(World var1, int var2) {
        return new TileEntityMultiBlockElectricFurnace(20, new RelativeCoordinate[] {
                new RelativeCoordinate(1, 0, 1),
                new RelativeCoordinate(-1, 0, 1),
                new RelativeCoordinate(0, 0, 2),
                new RelativeCoordinate(0, 1, 1),
                new RelativeCoordinate(0, -1, 1) }, new RelativeCoordinate[] {});
    }

    @Override
    protected MultiBlockStructure getMultiBlockStructure() {
        MultiBlockStructure structure=MultiBlockStructure.FURNACE_TIER1;
        structure.setValidBlocks(new Block[] {
                this,
                IndustrialScience.getInstance().getFactoryModule()
                        .getBlockIronhull() });
        return structure;
    }

}
