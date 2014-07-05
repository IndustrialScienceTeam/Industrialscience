package de.zsgn.industrialscience.factory.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import de.zsgn.industrialscience.util.AbsoluteCoordinate;

public abstract class ITileEntityMultiBlockController extends
        TileEntityMultiBlock {
    protected AbsoluteCoordinate[] structure = {};

    @Override
    public void updateEntity() {
        if (!worldObj.isRemote&&(worldObj.getBlockMetadata(xCoord, yCoord, zCoord) & 1) != (this
                .isProcessing() ? 1 : 0)) {
            int newmeta = worldObj.getBlockMetadata(xCoord, yCoord, zCoord) >> 1 << 1
                    | (this.isProcessing() ? 1 : 0);
            worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord,
                    newmeta, 2);
        }
    }

    @Override
    public void destroyStructure() {
        if(!worldObj.isRemote){
        for (AbsoluteCoordinate coord : structure) {
            TileEntity tileEntity = worldObj.getTileEntity(coord.xCoord,
                    coord.yCoord, coord.zCoord);
            if (tileEntity instanceof TileEntityMultiBlock) {
                ((TileEntityMultiBlock) tileEntity).setActivepart(false);
            }
        }
        structure = null;
        }
    }

    @Override
    public void readFromNBT(NBTTagCompound tagCompound) {
        super.readFromNBT(tagCompound);
        structure = AbsoluteCoordinate.getArrayFromNBTTagList(tagCompound
                .getTagList("structure", new NBTTagCompound().getId()));
    }

    @Override
    public void writeToNBT(NBTTagCompound tagCompound) {
        super.writeToNBT(tagCompound);
        tagCompound.setTag("structure",
                AbsoluteCoordinate.getArrayAsNBTTagList(structure));
    }

    public void setStructure(AbsoluteCoordinate[] blocks) {
        structure = blocks;
    }

    @Override
    public void setController(int x, int y, int z) {
        masterx = x;
        mastery = y;
        masterz = z;
    }

    public boolean isProcessing() {
        return this.isActivePart();
    }

}
