package de.zsgn.industrialscience.util;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class AbsoluteCoordinate {
    public final int xCoord;
    public final int yCoord;
    public final int zCoord;

    public AbsoluteCoordinate(int xCoord, int yCoord, int zCoord) {
        this.xCoord = xCoord;
        this.yCoord = yCoord;
        this.zCoord = zCoord;
    }

    public AbsoluteCoordinate(NBTTagCompound compound) {
        xCoord = compound.getInteger("xCoord");
        yCoord = compound.getInteger("yCoord");
        zCoord = compound.getInteger("zCoord");
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof AbsoluteCoordinate) {
            AbsoluteCoordinate coord = (AbsoluteCoordinate) obj;
            return xCoord == coord.xCoord && yCoord == coord.yCoord
                    && zCoord == coord.zCoord;
        }
        return false;
    }

    @Override
    public String toString() {
        return "X: " + xCoord + " - Y: " + yCoord + " - Z: " + zCoord;
    }

    public static NBTTagList getArrayAsNBTTagList(AbsoluteCoordinate[] coords) {
        NBTTagList list = new NBTTagList();
        for (AbsoluteCoordinate coord : coords) {
            list.appendTag(coord.getasNBTTagCompound());
        }
        return list;
    }

    public NBTTagCompound getasNBTTagCompound() {
        NBTTagCompound result = new NBTTagCompound();
        result.setInteger("xCoord", xCoord);
        result.setInteger("yCoord", yCoord);
        result.setInteger("zCoord", zCoord);
        return result;
    }

    public static AbsoluteCoordinate[] getArrayFromNBTTagList(NBTTagList list) {
        if (list != null && list.tagCount() > 0) {
            AbsoluteCoordinate[] result = new AbsoluteCoordinate[list
                    .tagCount()];
            for (int i = 0; i < list.tagCount(); i++) {
                if (list.getCompoundTagAt(i) instanceof NBTTagCompound) {
                    result[i] = new AbsoluteCoordinate(list.getCompoundTagAt(i));
                } else {
                    System.err.println("Received Invalid Taglist!");
                    return new AbsoluteCoordinate[] {};
                }
            }
            return result;
        } else {
            return new AbsoluteCoordinate[] {};
        }
    }

}
