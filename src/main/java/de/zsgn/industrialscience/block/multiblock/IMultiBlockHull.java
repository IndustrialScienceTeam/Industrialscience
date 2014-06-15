package de.zsgn.industrialscience.block.multiblock;

import de.zsgn.industrialscience.tileentity.TileEntityMultiBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public interface IMultiBlockHull {
    TileEntityMultiBlock createNewTileEntity(World var1, int var2);

}
