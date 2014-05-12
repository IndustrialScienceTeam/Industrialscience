package de.zsgn.industrialscience.blocks;

import de.zsgn.industrialscience.IndustrialScience;
import de.zsgn.industrialscience.tileentity.TileEntityMultiblockStructureBase;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockMultiblockStructureBase extends Block {

	protected BlockMultiblockStructureBase() {
		super(Material.wood);
		this.setCreativeTab(IndustrialScience.creativetab);
		this.setBlockName("multiblockstructurebase");
		this.setHardness(3.0F);
	}

	@Override
	public boolean hasTileEntity(int metadata) {
		return true;
	}

	@Override
	public TileEntity createTileEntity(World world, int metadata) {
		return new TileEntityMultiblockStructureBase(world, metadata);
	}

}
