package de.zsgn.industrialscience;

public class AbsoluteCoordinate {
    public final int xCoord;
    public final int yCoord;
    public final int zCoord;
    public AbsoluteCoordinate(int xCoord, int yCoord, int zCoord) {
        this.xCoord = xCoord;
        this.yCoord = yCoord;
        this.zCoord = zCoord;
    }
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof AbsoluteCoordinate){
            AbsoluteCoordinate coord =(AbsoluteCoordinate) obj;
            return (this.xCoord==coord.xCoord&&this.yCoord==coord.yCoord&&this.zCoord==coord.zCoord);
        }
        return false;
    }
    @Override
    public String toString() {
        return "X: "+xCoord+" - Y: "+yCoord+" - Z: "+zCoord;
    }

}
