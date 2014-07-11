package de.zsgn.industrialscience.factory.tileentity;

import de.zsgn.industrialscience.factory.tileentity.controllers.ITileEntityMultiBlockController;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileEntityMultiBlock extends TileEntity {
    protected boolean activepart = false;
    protected int masterx, mastery, masterz;

    public void setController(int x, int y, int z) {
        masterx = x;
        mastery = y;
        masterz = z;
    }

    public void destroyStructure() {
        if(!worldObj.isRemote){
        if (worldObj.getTileEntity(masterx, mastery, masterz) instanceof ITileEntityMultiBlockController) {
            ((ITileEntityMultiBlockController) worldObj.getTileEntity(masterx,
                    mastery, masterz)).destroyStructure();
            ;
        }
        }
    }

    public void setActivepart(boolean activepart) {
        this.activepart = activepart;
    }

    public boolean isActivePart() {
        return activepart;
    }

    public int getMasterx() {
        return masterx;
    }

    public int getMastery() {
        return mastery;
    }

    public int getMasterz() {
        return masterz;
    }

    @Override
    public void readFromNBT(NBTTagCompound tagCompound) {
        super.readFromNBT(tagCompound);
        masterx = tagCompound.getInteger("masterx");
        mastery = tagCompound.getInteger("mastery");
        masterz = tagCompound.getInteger("masterz");
        activepart = tagCompound.getBoolean("activepart");
    }

    @Override
    public void writeToNBT(NBTTagCompound tagCompound) {
        super.writeToNBT(tagCompound);
        tagCompound.setInteger("masterx", masterx);
        tagCompound.setInteger("mastery", mastery);
        tagCompound.setInteger("masterz", masterz);
        tagCompound.setBoolean("activepart", activepart);
    }

}
