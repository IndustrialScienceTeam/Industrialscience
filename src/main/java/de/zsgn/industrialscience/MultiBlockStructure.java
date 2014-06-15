package de.zsgn.industrialscience;

import de.zsgn.industrialscience.tileentity.multiblock.TileEntityMultiBlock;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.Vec3;
import net.minecraft.util.Vec3Pool;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public enum MultiBlockStructure {
    FURNACE_TIER1(1,1,1,1,2,0);
protected final int sizeToRight;
protected final int sizeToLeft;
protected final int sizeToBottom;
protected final int sizeToTop;
protected final int sizeToBack;
protected final int sizeToFront;
protected final int resultsize;

MultiBlockStructure(int sizeToRight, int sizeToLeft, int sizeToBottom,
        int sizeToTop, int sizeToBack, int sizeToFront) {
    this.sizeToRight = sizeToRight;
    this.sizeToLeft = sizeToLeft;
    this.sizeToBottom = sizeToBottom;
    this.sizeToTop = sizeToTop;
    this.sizeToBack = sizeToBack;
    this.sizeToFront = sizeToFront;
    resultsize=(sizeToRight+1+sizeToLeft)*(sizeToTop+1+sizeToBottom)*(sizeToBack+1+sizeToFront);
}

public Vec3[] structureTest(World world, int x, int y, int z, ForgeDirection dir, Block[] ValidBlocks){
    ForgeDirection dirRIGHT =dir.getRotation(ForgeDirection.DOWN);
    ForgeDirection dirLEFT=dirRIGHT.getOpposite();
    ForgeDirection dirDEPTH=dir.getOpposite();
    int startX=x+sizeToRight*dirRIGHT.offsetX+sizeToFront*dir.offsetX;
    int startY=y+sizeToTop;
    int startZ=z+sizeToRight*dirRIGHT.offsetZ+sizeToFront*dir.offsetZ;
    System.out.println("Start Cord.:");
    System.out.println("X: "+Integer.toString(startX)+"  Y:"+Integer.toString(startY)+"  Z:"+Integer.toString(startZ));
    int endX=x+sizeToLeft*dirLEFT.offsetX+sizeToBack*dirDEPTH.offsetX;
    int endY=y-sizeToBottom;
    int endZ=z+sizeToLeft*dirLEFT.offsetZ+sizeToBack*dirDEPTH.offsetZ;
    System.out.println("End Cord.:");
    System.out.println("X: "+Integer.toString(endX)+"  Y:"+Integer.toString(endY)+"  Z:"+Integer.toString(endZ));
    System.out.println("---------------------------");
    Vec3[] result = new Vec3[resultsize];
    int i =0;
    for (int movex = 0; movex+Math.min(startX, endX)<=Math.max(startX, endX); movex++) {
        for (int movey = 0; movey+Math.min(startY, endY)<=Math.max(startY,endY); movey++) {
            for (int movez = 0; movez+Math.min(startZ, endZ)<=Math.max(startZ, endZ); movez++) {
                System.out.println("X: "+Integer.toString(movex+Math.min(startX, endX))+"  Y:"+Integer.toString(movey+Math.min(startY, endY))+"  Z:"+Integer.toString(movez+Math.min(startZ, endZ)));
                if(!isValidBlock(ValidBlocks, world, movex+Math.min(startX, endX), movey+Math.min(startY, endY), movez+Math.min(startZ, endZ))){
                    return null;
                }
                result[i]=Vec3.fakePool.getVecFromPool(movex+Math.min(startX, endX), movey+Math.min(startY, endY), movez+Math.min(startZ, endZ));
                i++;
            }
        }
    }
    return result;
}

protected boolean isValidBlock(Block[] ValidBlocks, World world, int x, int y, int z) {
    if(world.getTileEntity(x, y, z) instanceof TileEntityMultiBlock&&((TileEntityMultiBlock)world.getTileEntity(x, y, z)).isActivePart()){
        return false;
    }
    for (int i = 0; i < ValidBlocks.length; i++) {
       if(world.getBlock(x, y, z) == ValidBlocks[i]){
           return true;
       }
    }
    return false;
}

}
