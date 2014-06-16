package de.zsgn.industrialscience.block;

import net.minecraft.block.material.Material;
import de.zsgn.industrialscience.block.multiblock.BlockMultiblockHull;

public class MultiBlockHullStoneHull extends BlockMultiblockHull {

    public MultiBlockHullStoneHull() {
        super(Material.rock);
        this.setBlockName("stonehull");
        this.setHardness(3.0F);
    }

}
