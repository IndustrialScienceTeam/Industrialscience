package de.zsgn.industrialscience.block;

import de.zsgn.industrialscience.IndustrialScience;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockReinforcedBricks extends Block {

    public BlockReinforcedBricks() {
        super(Material.rock);
        setCreativeTab(IndustrialScience.getInstance().getCreativetab());
        setBlockName("reinforcedbricks");
        setBlockTextureName(IndustrialScience.MODID+":"+getUnlocalizedName().substring(5));
    }

}
