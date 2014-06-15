package de.zsgn.industrialscience.block.multiblock;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import de.zsgn.industrialscience.IndustrialScience;
import de.zsgn.industrialscience.MultiBlockStructure;
import de.zsgn.industrialscience.block.BlockMultiBlockController;
import de.zsgn.industrialscience.tileentity.multiblock.TileEntityMultiBlockController;
import de.zsgn.industrialscience.tileentity.multiblock.TileEntityTier1Furnace;


public class MultiBlockControllerTier1Furnace extends BlockMultiBlockController {

    public MultiBlockControllerTier1Furnace() {
        super(Material.iron, MultiBlockStructure.FURNACE_TIER1);
        ValidBlocks=new Block[]{this, IndustrialScience.getInstance().getBlockironhull()};
        this.setCreativeTab(IndustrialScience.getInstance().getCreativetab());
        setBlockName("multiblockcontrollertier1furnace");
        setHardness(3.0F);
    }

    @Override
    public TileEntity createNewTileEntity(World var1, int var2) {
        return new TileEntityTier1Furnace();
    }

}
