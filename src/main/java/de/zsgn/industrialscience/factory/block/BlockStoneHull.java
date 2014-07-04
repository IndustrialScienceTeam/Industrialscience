package de.zsgn.industrialscience.factory.block;

import net.minecraft.block.material.Material;
import de.zsgn.industrialscience.IndustrialScience;

public class BlockStoneHull extends IBlockMultiblockHull {

    public BlockStoneHull() {
        super(Material.rock);
        this.setBlockName("cobblestonehull");
        this.setHardness(3.0F);
        textureName = IndustrialScience.MODID + ":"
                + this.getUnlocalizedName().substring(5);
    }

}
