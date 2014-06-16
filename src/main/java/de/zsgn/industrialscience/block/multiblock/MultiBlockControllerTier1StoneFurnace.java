package de.zsgn.industrialscience.block.multiblock;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import de.zsgn.industrialscience.IndustrialScience;
import de.zsgn.industrialscience.MultiBlockStructure;
import de.zsgn.industrialscience.block.BlockMultiBlockController;
import de.zsgn.industrialscience.tileentity.multiblock.TileEntityTier1StoneFurnace;


public class MultiBlockControllerTier1StoneFurnace extends BlockMultiBlockController {

    public MultiBlockControllerTier1StoneFurnace() {
        super(Material.rock, MultiBlockStructure.FURNACE_TIER1);
        ValidBlocks=new Block[]{this,IndustrialScience.getInstance().getBlockstonehull()};
        this.setCreativeTab(IndustrialScience.getInstance().getCreativetab());
        setBlockName("tier1stonefurnace");
        setHardness(3.0F);
    }

    @Override
    public TileEntity createNewTileEntity(World var1, int var2) {
        return new TileEntityTier1StoneFurnace();
    }

}
