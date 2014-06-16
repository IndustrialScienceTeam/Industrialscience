package de.zsgn.industrialscience.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import de.zsgn.industrialscience.IndustrialScience;

public class BlockReinforcedBricks extends Block {

    public BlockReinforcedBricks() {
        super(Material.rock);
        setCreativeTab(IndustrialScience.getInstance().getCreativetab());
        setBlockName("reinforcedbricks");
        setBlockTextureName(IndustrialScience.MODID+":"+getUnlocalizedName().substring(5));
        setHardness(2.0F);
        setResistance(1500.0F);
    }

}
