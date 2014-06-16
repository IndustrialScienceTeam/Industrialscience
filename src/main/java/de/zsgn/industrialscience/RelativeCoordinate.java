package de.zsgn.industrialscience;

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
        DepthCoord = depthCoord;
    }
}
