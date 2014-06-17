package de.zsgn.industrialscience.block.multiblock;

import net.minecraft.block.material.Material;
import de.zsgn.industrialscience.IndustrialScience;

public class MultiBlockHullStoneHull extends BlockMultiblockHull {

    public MultiBlockHullStoneHull() {
        super(Material.rock);
        this.setBlockName("cobblestonehull");
        this.setHardness(3.0F);
        this.textureName=IndustrialScience.MODID + ":" + this.getUnlocalizedName().substring(5);
    }

}
