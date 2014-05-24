package de.zsgn.industrialscience.block;

import de.zsgn.industrialscience.IndustrialScience;
import de.zsgn.industrialscience.tileentity.TileEntityMysteriousPortal;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockMysteriousPortal extends BlockContainer {

	public BlockMysteriousPortal() {
		super(Material.iron);
		setCreativeTab(IndustrialScience.getInstance().getCreativetab());
		setBlockName("mysteriousportal");
        setHardness(3.0F);
	}

	@Override
	public TileEntity createNewTileEntity(World var1, int var2) {
		return new TileEntityMysteriousPortal();
	}

	@Override
	public boolean onBlockActivated(World world, int x,
			int y, int z, EntityPlayer player,
			int side, float xOffset, float yOffset,
			float zOffset) {
		if(!world.isRemote){
			if(world.getTileEntity(x, y, z) instanceof TileEntityMysteriousPortal){
				TileEntityMysteriousPortal tileentity=(TileEntityMysteriousPortal)world.getTileEntity(x, y, z);
				return tileentity.onBlockActivated(world, x,
						y, z, player,
						side, xOffset, yOffset,
						zOffset);
			}
		}
		return false;
	}
	

}
