package de.zsgn.industrialscience.tileentity.multiblock;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Vec3;

public abstract class TileEntityMultiBlockController extends TileEntityMultiBlock {
    protected Vec3[] structure={}; 
    @Override
    public void destroyStructure() {
        for (int i = 0; i < structure.length; i++) {
            Vec3 Coord = structure[i];
            TileEntity tileEntity= worldObj.getTileEntity((int)Coord.xCoord, (int)Coord.yCoord, (int)Coord.zCoord);
            if(tileEntity instanceof TileEntityMultiBlock){
                ((TileEntityMultiBlock)tileEntity).setActivepart(false);
            }   
        }
        structure=null;
    }
    @Override
    public void readFromNBT(NBTTagCompound tagCompound) {
        super.readFromNBT(tagCompound);
        NBTTagCompound structurecompound = tagCompound.getCompoundTag("structuretag");
        if(structurecompound!=null){
            structure = new Vec3[structurecompound.getInteger("length")];
            for (int i = 0; i < structure.length; i++) {
                structure[i].xCoord=structurecompound.getInteger("x"+i);
                structure[i].yCoord=structurecompound.getInteger("y"+i);
                structure[i].zCoord=structurecompound.getInteger("z"+i);
            }
        }
        else {
            structure = new Vec3[]{};
        }
    }
    @Override
    public void writeToNBT(NBTTagCompound tagCompound) {
        super.writeToNBT(tagCompound);
        if(structure!=null){
            NBTTagCompound structurecompound = new NBTTagCompound();
            structurecompound.setInteger("length", structure.length);
            for (int i = 0; i < structure.length; i++) {
                Vec3 vec3 = structure[i];
                structurecompound.setInteger("x"+i, (int)vec3.xCoord);
                structurecompound.setInteger("y"+i, (int)vec3.yCoord);
                structurecompound.setInteger("z"+i, (int)vec3.zCoord);
            }
            tagCompound.setTag("structuretag", structurecompound);
        }
        else {
            tagCompound.setTag("structuretag", null);
        }
    }
    public void setStructure(Vec3[] blocks) {
        structure=blocks;
    }
    @Override
    public void setController(int x, int y, int z) {
        masterx=x;
        mastery=y;
        masterz=z;
    }

}
