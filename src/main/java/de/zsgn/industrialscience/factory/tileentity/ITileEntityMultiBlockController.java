package de.zsgn.industrialscience.factory.tileentity;

import de.zsgn.industrialscience.AbsoluteCoordinate;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Vec3;

public abstract class ITileEntityMultiBlockController extends TileEntityMultiBlock {
    protected AbsoluteCoordinate[] structure={}; 
    @Override
    public void updateEntity() {
        if((worldObj.getBlockMetadata(xCoord, yCoord, zCoord)&1)!=(isProcessing()?1:0)){
            System.out.println(Integer.toBinaryString(worldObj.getBlockMetadata(xCoord, yCoord, zCoord)|((isProcessing())?1:0)));
            int newmeta= ((worldObj.getBlockMetadata(xCoord, yCoord, zCoord)>>1)<<1)|((isProcessing())?1:0);;
            worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, newmeta, 3);
            worldObj.scheduleBlockUpdate(xCoord, yCoord, zCoord, worldObj.getBlock(xCoord, yCoord, zCoord), 20);
        }
    }
    @Override
    public void destroyStructure() {
        for (int i = 0; i < structure.length; i++) {
            AbsoluteCoordinate Coord = structure[i];
            TileEntity tileEntity= worldObj.getTileEntity(Coord.xCoord, Coord.yCoord, Coord.zCoord);
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
            structure = new AbsoluteCoordinate[structurecompound.getInteger("length")];
            for (int i = 0; i < structure.length; i++) {
                structure[i]=new AbsoluteCoordinate(structurecompound.getInteger("x"+i),structurecompound.getInteger("y"+i),structurecompound.getInteger("z"+i));
            }
        }
        else {
            structure = new AbsoluteCoordinate[]{};
        }
    }
    @Override
    public void writeToNBT(NBTTagCompound tagCompound) {
        super.writeToNBT(tagCompound);
        if(structure!=null){
            NBTTagCompound structurecompound = new NBTTagCompound();
            structurecompound.setInteger("length", structure.length);
            for (int i = 0; i < structure.length; i++) {
                AbsoluteCoordinate coord = structure[i];
                structurecompound.setInteger("x"+i, coord.xCoord);
                structurecompound.setInteger("y"+i, coord.yCoord);
                structurecompound.setInteger("z"+i, coord.zCoord);
            }
            tagCompound.setTag("structuretag", structurecompound);
        }
        else {
            tagCompound.setTag("structuretag", null);
        }
    }
    public void setStructure(AbsoluteCoordinate[] blocks) {
        structure=blocks;
    }
    @Override
    public void setController(int x, int y, int z) {
        masterx=x;
        mastery=y;
        masterz=z;
    }
    public boolean isProcessing() {
        return isActivePart();
    }

}
