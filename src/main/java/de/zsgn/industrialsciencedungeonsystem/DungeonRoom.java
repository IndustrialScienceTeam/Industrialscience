package de.zsgn.industrialsciencedungeonsystem;

import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class DungeonRoom {
public final static int ROOMSIZE=16;
public final static int ROOMHEIGHT=10;
private String[][][] content=new String[ROOMSIZE-2][ROOMHEIGHT-2][ROOMSIZE-2];
private RouteType routetype;
public DungeonRoom(RouteType routetype, String[][][] content) {
    super();
    this.routetype = routetype;
    this.content = content;
}
public static void generateRoom(int x, int y, int z, World worldobj, ForgeDirection direction){
}
public String[][][] getContent() {
    return content;
}
public static String[][][] getEmptyContent() {
    return new String[ROOMSIZE-2][ROOMHEIGHT-2][ROOMSIZE-2];
}
public RouteType getRoutetype() {
    return routetype;
}
public void setRoutetype(RouteType routetype) {
    this.routetype = routetype;
}
}
