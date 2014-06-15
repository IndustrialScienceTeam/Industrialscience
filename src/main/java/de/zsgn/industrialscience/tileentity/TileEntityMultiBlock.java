package de.zsgn.industrialscience.tileentity;

import de.zsgn.industrialscience.tileentity.multiblock.TileEntityMultiBlockController;
import net.minecraft.tileentity.TileEntity;

public class TileEntityMultiBlock extends TileEntity {
    protected boolean activepart=false;
    protected int masterx,mastery,masterz;

    public void setController(int x, int y, int z) {
        masterx=x;
        mastery=y;
        masterz=z;
    }

    public void destroyStructure() {
        if(worldObj.getTileEntity(masterx,mastery,masterz) instanceof TileEntityMultiBlockController){
        ((TileEntityMultiBlockController)worldObj.getTileEntity(masterx,mastery,masterz)).destroyStructure();;
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

}
