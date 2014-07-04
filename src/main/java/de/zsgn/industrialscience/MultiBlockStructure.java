package de.zsgn.industrialscience;

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
            ForgeDirection dir, Block[] ValidBlocks) {
        ForgeDirection dirRIGHT = dir.getRotation(ForgeDirection.DOWN);
        ForgeDirection dirLEFT = dirRIGHT.getOpposite();
        ForgeDirection dirDEPTH = dir.getOpposite();
        int startX = x + sizeToRight * dirRIGHT.offsetX + sizeToFront
                * dir.offsetX;
        int startY = y + sizeToTop;
        int startZ = z + sizeToRight * dirRIGHT.offsetZ + sizeToFront
                * dir.offsetZ;
        int endX = x + sizeToLeft * dirLEFT.offsetX + sizeToBack
                * dirDEPTH.offsetX;
        int endY = y - sizeToBottom;
        int endZ = z + sizeToLeft * dirLEFT.offsetZ + sizeToBack
                * dirDEPTH.offsetZ;
        AbsoluteCoordinate[] absholes = new AbsoluteCoordinate[holes.length];
        AbsoluteCoordinate[] result = new AbsoluteCoordinate[resultsize];
        for (int i = 0; i < holes.length; i++) {
            absholes[i] = holes[i].convertToAbsolute(x, y, z, dirRIGHT,
                    dirDEPTH);
        }
        int i = 0;
        for (int movex = 0; movex + Math.min(startX, endX) <= Math.max(startX,
                endX); movex++) {
            for (int movey = 0; movey + Math.min(startY, endY) <= Math.max(
                    startY, endY); movey++) {
                for (int movez = 0; movez + Math.min(startZ, endZ) <= Math.max(
                        startZ, endZ); movez++) {
                    if (!this.isValidBlock(ValidBlocks, absholes, world, movex
                            + Math.min(startX, endX),
                            movey + Math.min(startY, endY),
                            movez + Math.min(startZ, endZ))) {
                        return null;
                    }
                    result[i] = new AbsoluteCoordinate(movex
                            + Math.min(startX, endX), movey
                            + Math.min(startY, endY), movez
                            + Math.min(startZ, endZ));
                    i++;
                }
            }
        }
        return result;
    }

    protected boolean isValidBlock(Block[] ValidBlocks,
            AbsoluteCoordinate[] absholes, World world, int x, int y, int z) {
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
        for (Block validBlock : ValidBlocks) {
            if (validBlock.getClass().isInstance(world.getBlock(x, y, z))) {
                return true;
            }
        }
        return false;
    }

}
