package de.zsgn.industrialscience;

import net.minecraftforge.common.util.ForgeDirection;

public class RelativeCoordinate {
    //Right is +
    public final int sideCoord;
    //Up is +
    public final int heightCoord;
    //Depth is +
    public final int DepthCoord;
    public RelativeCoordinate(int sideCoord, int heightCoord, int depthCoord) {
        super();
        this.sideCoord = sideCoord;
        this.heightCoord = heightCoord;
        this.DepthCoord = depthCoord;
    }
    public AbsoluteCoordinate convertToAbsolute(int x, int y, int z, ForgeDirection dirRIGHT, ForgeDirection dirDEPTH) {
        return new AbsoluteCoordinate(x+dirRIGHT.offsetX*sideCoord+dirDEPTH.offsetX*DepthCoord, y+heightCoord, z+dirRIGHT.offsetZ*sideCoord+(dirDEPTH.offsetZ*DepthCoord));
    }
}
