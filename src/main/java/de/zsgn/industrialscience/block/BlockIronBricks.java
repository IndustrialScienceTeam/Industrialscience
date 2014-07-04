package de.zsgn.industrialscience.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import de.zsgn.industrialscience.IndustrialScience;

public class BlockIronBricks extends Block {

    public BlockIronBricks(Material material) {
        super(material);
        this.setCreativeTab(IndustrialScience.getInstance().getCreativetab());
        this.setBlockName("ironbricks");
        this.setHardness(3.0F);
    }

}
