package de.zsgn.industrialscience.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import de.zsgn.industrialscience.IndustrialScience;

public class BlockSingularity extends Block {

    public BlockSingularity(Material material) {
        super(material);
        this.setCreativeTab(IndustrialScience.getInstance().getCreativetab());
        this.setBlockName("singularityblock");
        this.setHardness(3.0F);
    }

}
