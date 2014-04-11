package de.zsgn.industrialscience.blocks;

import de.zsgn.industrialscience.IndustrialScience;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockSingularity extends Block {

	public BlockSingularity(Material material) {
		super(material);
		this.setCreativeTab(IndustrialScience.creativetab);
		setBlockName("singularityblock");
        setHardness(3.0F);
	}
	
}
