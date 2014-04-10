package de.zsgn.industrialscience.blocks;

import de.zsgn.industrialscience.IndustrialScience;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class SingularityBlock extends Block {

	public SingularityBlock(Material material) {
		super(material);
		this.setCreativeTab(IndustrialScience.creativetab);
		setBlockName("singularityblock");
        setHardness(3.0F);
	}
	
}
