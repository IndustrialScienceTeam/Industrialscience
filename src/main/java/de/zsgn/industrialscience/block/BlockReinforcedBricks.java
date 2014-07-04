package de.zsgn.industrialscience.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import de.zsgn.industrialscience.IndustrialScience;

public class BlockReinforcedBricks extends Block {

    public BlockReinforcedBricks() {
        super(Material.rock);
        this.setCreativeTab(IndustrialScience.getInstance().getCreativetab());
        this.setBlockName("reinforcedbricks");
        this.setBlockTextureName(IndustrialScience.MODID + ":"
                + this.getUnlocalizedName().substring(5));
        this.setHardness(2.0F);
        this.setResistance(1500.0F);
    }

}
