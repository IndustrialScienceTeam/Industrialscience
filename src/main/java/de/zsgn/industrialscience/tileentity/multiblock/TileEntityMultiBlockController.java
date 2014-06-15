package de.zsgn.industrialscience.tileentity.multiblock;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Vec3;

public abstract class TileEntityMultiBlockController extends TileEntityMultiBlock {
    protected Vec3[] structure={}; 
    public void setStructure(Vec3[] blocks) {
        structure=blocks;
    }
    @Override
    public void setController(int x, int y, int z) {
        masterx=x;
        mastery=y;
        masterz=z;
    }
    @Override
    public void destroyStructure() {
       for (int i = 0; i < structure.length; i++) {
        Vec3 Coord = structure[i];
        TileEntity tileEntity= worldObj.getTileEntity((int)Coord.xCoord, (int)Coord.yCoord, (int)Coord.zCoord);
        if(tileEntity instanceof TileEntityMultiBlock){
            ((TileEntityMultiBlock)tileEntity).setActivepart(false);
        }
            
       }
    }

}
