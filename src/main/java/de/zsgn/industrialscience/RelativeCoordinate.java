package de.zsgn.industrialscience;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.common.util.ForgeDirection;

public class RelativeCoordinate {
    // Right is +
    public final int sideCoord;
    // Up is +
    public final int heightCoord;
    // Depth is +
    public final int DepthCoord;

    public RelativeCoordinate(int sideCoord, int heightCoord, int depthCoord) {
        this.sideCoord = sideCoord;
        this.heightCoord = heightCoord;
        DepthCoord = depthCoord;
    }

    public RelativeCoordinate(NBTTagCompound compound) {
        sideCoord = compound.getInteger("sideCoord");
        heightCoord = compound.getInteger("heightCoord");
        DepthCoord = compound.getInteger("DepthCoord");
    }

    public AbsoluteCoordinate convertToAbsolute(int x, int y, int z,
            ForgeDirection dirRIGHT, ForgeDirection dirDEPTH) {
        return new AbsoluteCoordinate(x + dirRIGHT.offsetX * sideCoord
                + dirDEPTH.offsetX * DepthCoord, y + heightCoord, z
                + dirRIGHT.offsetZ * sideCoord + dirDEPTH.offsetZ * DepthCoord);
    }

    public static NBTTagList getArrayAsNBTTagList(RelativeCoordinate[] coords) {
        NBTTagList list = new NBTTagList();
        for (RelativeCoordinate coord : coords) {
            list.appendTag(coord.getasNBTTagCompound());
        }
        return list;
    }

    public NBTTagCompound getasNBTTagCompound() {
        NBTTagCompound result = new NBTTagCompound();
        result.setInteger("sideCoord", sideCoord);
        result.setInteger("heightCoord", heightCoord);
        result.setInteger("DepthCoord", DepthCoord);
        return result;
    }

    public static RelativeCoordinate[] getArrayFromNBTTagList(NBTTagList list) {
        if (list != null && list.tagCount() > 0) {
            RelativeCoordinate[] result = new RelativeCoordinate[list
                    .tagCount()];
            for (int i = 0; i < list.tagCount(); i++) {
                if (list.getCompoundTagAt(i) instanceof NBTTagCompound) {
                    result[i] = new RelativeCoordinate(list.getCompoundTagAt(i));
                } else {
                    System.err.println("Received Invalid Taglist!");
                    return new RelativeCoordinate[] {};
                }
            }
            return result;
        } else {
            return new RelativeCoordinate[] {};
        }
    }
}
