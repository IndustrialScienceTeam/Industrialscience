package de.zsgn.industrialscience.block;

import de.zsgn.industrialscience.IndustrialScience;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockSingularity extends Block {

	public BlockSingularity(Material material) {
		super(material);
		this.setCreativeTab(IndustrialScience.getInstance().getCreativetab());
		setBlockName("singularityblock");
        setHardness(3.0F);
	}
	
}
