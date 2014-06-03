package de.zsgn.industrialsciencedungeonsystem;

import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class DungeonRoom {
public final static int ROOMSIZE=16;
public final static int ROOMHEIGHT=10;
private String[][][] content=new String[ROOMSIZE][ROOMHEIGHT][ROOMSIZE];
private RouteType routetype;
private String name;
public DungeonRoom(RouteType routetype, String[][][] content, String name) {
    super();
    this.routetype = routetype;
    this.content = content;
    this.name = name;
}
public static void generateRoom(int x, int y, int z, World worldobj, ForgeDirection direction){
}
public String[][][] getContent() {
    return content;
}
public static String[][][] getEmptyContent() {
    return new String[ROOMSIZE][ROOMHEIGHT][ROOMSIZE];
}
public RouteType getRoutetype() {
    return routetype;
}
public void setRoutetype(RouteType routetype) {
    this.routetype = routetype;
}
public String getName() {
    return name;
}
}
