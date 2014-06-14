package de.zsgn.industrialscience;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class MultiBlockStructure {
protected final int SizeToRight;
protected final int SizeToLeft;
protected final int SizeToBottom;
protected final int SizeToTop;
protected final int SizeToBack;
protected final int SizeToFront;
public MultiBlockStructure(int sizeToRight, int sizeToLeft, int sizeToBottom,
        int sizeToTop, int sizeToBack, int sizeToFront) {
    super();
    SizeToRight = sizeToRight;
    SizeToLeft = sizeToLeft;
    SizeToBottom = sizeToBottom;
    SizeToTop = sizeToTop;
    SizeToBack = sizeToBack;
    SizeToFront = sizeToFront;
}

public boolean structureTest(World world, int x, int y, int z, ForgeDirection dir){
    ForgeDirection dirRIGHT =dir.getRotation(ForgeDirection.DOWN);
    ForgeDirection dirLEFT=dirRIGHT.getOpposite();
    ForgeDirection dirDEPTH=dir.getOpposite();
    int startX=x+SizeToRight*dirRIGHT.offsetX+SizeToFront*dir.offsetX;
    int startY=y+SizeToTop;
    int startZ=z+SizeToRight*dirRIGHT.offsetZ+SizeToFront*dir.offsetZ;
    System.out.println("Start Cord.:");
    System.out.println("X: "+Integer.toString(startX)+"  Y:"+Integer.toString(startY)+"  Z:"+Integer.toString(startZ));
    int endX=x+SizeToLeft*dirLEFT.offsetX+SizeToBack*dirDEPTH.offsetX;
    int endY=y-SizeToBottom;
    int endZ=z+SizeToLeft*dirLEFT.offsetZ+SizeToBack*dirDEPTH.offsetZ;
    System.out.println("End Cord.:");
    System.out.println("X: "+Integer.toString(endX)+"  Y:"+Integer.toString(endY)+"  Z:"+Integer.toString(endZ));
    System.out.println("---------------------------");
    for (int movex = 0; movex+Math.min(startX, endX)<=Math.max(startX, endX); movex++) {
        for (int movey = 0; movey+Math.min(startY, endY)<=Math.max(startY,endY); movey++) {
            for (int movez = 0; movez+Math.min(startZ, endZ)<=Math.max(startZ, endZ); movez++) {
                System.out.println("X: "+Integer.toString(movex+Math.min(startX, endX))+"  Y:"+Integer.toString(movey+Math.min(startY, endY))+"  Z:"+Integer.toString(movez+Math.min(startZ, endZ)));
            }
        }
    }
    return false;
}

protected boolean isValidBlock(Block block) {
    return block.equals(Blocks.cobblestone);
}

}
