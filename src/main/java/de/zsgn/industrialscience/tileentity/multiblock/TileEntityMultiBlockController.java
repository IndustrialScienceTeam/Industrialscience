package de.zsgn.industrialscience.tileentity.multiblock;

import net.minecraft.util.Vec3;

public abstract class TileEntityMultiBlockController extends TileEntityMultiBlock {
    private Vec3[] structure; 
    public void setStructure(Vec3[] blocks) {
        structure=blocks;
    }

}
