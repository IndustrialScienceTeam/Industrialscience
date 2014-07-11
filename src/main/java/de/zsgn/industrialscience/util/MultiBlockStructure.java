package de.zsgn.industrialscience.util;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import de.zsgn.industrialscience.factory.tileentity.TileEntityMultiBlock;

public enum MultiBlockStructure {
    FURNACE_TIER1(1, 1, 1, 1, 2, 0,
            new RelativeCoordinate[] { new RelativeCoordinate(0, 0, 1) });
    protected final int sizeToRight;
    protected final int sizeToLeft;
    protected final int sizeToBottom;
    protected final int sizeToTop;
    protected final int sizeToBack;
    protected final int sizeToFront;
    protected final int resultsize;
    protected final RelativeCoordinate[] holes;
    protected Block[] validBlocks = {};
    
    MultiBlockStructure(int sizeToRight, int sizeToLeft, int sizeToBottom,
            int sizeToTop, int sizeToBack, int sizeToFront,
            RelativeCoordinate[] holes) {
        this.sizeToRight = sizeToRight;
        this.sizeToLeft = sizeToLeft;
        this.sizeToBottom = sizeToBottom;
        this.sizeToTop = sizeToTop;
        this.sizeToBack = sizeToBack;
        this.sizeToFront = sizeToFront;
        resultsize = (sizeToRight + 1 + sizeToLeft)
                * (sizeToTop + 1 + sizeToBottom)
                * (sizeToBack + 1 + sizeToFront);
        this.holes = holes;
    }

    public AbsoluteCoordinate[] structureTest(World world, int x, int y, int z,
            ForgeDirection dir) {
        ForgeDirection dirRIGHT = dir.getRotation(ForgeDirection.DOWN);
        ForgeDirection dirLEFT = dirRIGHT.getOpposite();
        ForgeDirection dirDEPTH = dir.getOpposite();
        int firstX = x + sizeToRight * dirRIGHT.offsetX + sizeToFront
                * dir.offsetX;
        int firstY = y + sizeToTop;
        int firstZ = z + sizeToRight * dirRIGHT.offsetZ + sizeToFront
                * dir.offsetZ;
        int secondX = x + sizeToLeft * dirLEFT.offsetX + sizeToBack
                * dirDEPTH.offsetX;
        int secondY = y - sizeToBottom;
        int secondZ = z + sizeToLeft * dirLEFT.offsetZ + sizeToBack
                * dirDEPTH.offsetZ;
        AbsoluteCoordinate start=new AbsoluteCoordinate(Math.min(firstX, secondX),Math.min(firstY, secondY),Math.min(firstZ, secondZ));
        AbsoluteCoordinate end=new AbsoluteCoordinate(Math.max(firstX, secondX),Math.max(firstY, secondY),Math.max(firstZ, secondZ));
        AbsoluteCoordinate[] absholes = getAbsHoles(x, y, z, dirRIGHT, dirDEPTH);
        return getAllStructureCoords(world, absholes, start, end);

    }
    protected AbsoluteCoordinate[] getAbsHoles(int x, int y, int z, ForgeDirection dirRIGHT, ForgeDirection dirDEPTH){
        AbsoluteCoordinate[] absholes = new AbsoluteCoordinate[holes.length];
        for (int i = 0; i < holes.length; i++) {
            absholes[i] = holes[i].convertToAbsolute(x, y, z, dirRIGHT,
                    dirDEPTH);
        }
        return absholes;
    }
    protected AbsoluteCoordinate[] getAllStructureCoords(World world, AbsoluteCoordinate[] absholes, AbsoluteCoordinate start, AbsoluteCoordinate end){
        AbsoluteCoordinate[] result = new AbsoluteCoordinate[resultsize];
        int i = 0;
        for (int movex = 0; movex + start.xCoord <= end.xCoord; movex++) {
            for (int movey = 0; movey + start.yCoord <= end.yCoord; movey++) {
                for (int movez = 0; movez + start.zCoord  <= end.zCoord; movez++) {
                    if (!this.isValidBlock(absholes, world, movex
                            + start.xCoord,
                            movey + start.yCoord,
                            movez + start.zCoord)) {
                        return null;
                    }
                    result[i] = new AbsoluteCoordinate(movex
                            + start.xCoord,
                            movey + start.yCoord,
                            movez + start.zCoord);
                    i++;
                }
            }
        }
        return result;
    }

    protected boolean isValidBlock(AbsoluteCoordinate[] absholes, World world, int x, int y, int z) {
        if (world.getTileEntity(x, y, z) instanceof TileEntityMultiBlock
                && ((TileEntityMultiBlock) world.getTileEntity(x, y, z))
                .isActivePart()) {
            return false;
        }
        for (AbsoluteCoordinate abshole : absholes) {
            if (x == abshole.xCoord && y == abshole.yCoord
                    && z == abshole.zCoord) {
                if (world.isAirBlock(x, y, z)) {
                    return true;
                } else {
                    return false;
                }
            }
        }
        for (Block validBlock : validBlocks) {
            if (validBlock.getClass().isInstance(world.getBlock(x, y, z))) {
                return true;
            }
        }
        return false;
    }

    public void setValidBlocks(Block[] validBlocks) {
        this.validBlocks = validBlocks;
    }

}
