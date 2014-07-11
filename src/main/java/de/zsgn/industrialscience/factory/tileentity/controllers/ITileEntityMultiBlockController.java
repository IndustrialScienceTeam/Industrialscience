package de.zsgn.industrialscience.factory.tileentity.controllers;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.inventory.IInventory;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import de.zsgn.industrialscience.factory.tileentity.TileEntityMultiBlock;
import de.zsgn.industrialscience.util.AbsoluteCoordinate;

public abstract class ITileEntityMultiBlockController extends
        TileEntityMultiBlock {
    protected AbsoluteCoordinate[] structure = {};
    protected AbsoluteCoordinate itemhole=null;

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
        if(this instanceof IInventory&&itemhole!=null){
            IInventory inventory =(IInventory)this;
            for (int i = 0; i < inventory.getSizeInventory(); i++) {
                if(inventory.getStackInSlot(i)!=null){
                    worldObj.spawnEntityInWorld(new EntityItem(worldObj, itemhole.xCoord+0.5, itemhole.yCoord+0.5, itemhole.zCoord+0.5, inventory.getStackInSlot(i)));
                    inventory.setInventorySlotContents(i, null);
                }
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
        if(tagCompound.getCompoundTag("itemhole")!=null){
        itemhole=new AbsoluteCoordinate(tagCompound.getCompoundTag("itemhole"));
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound tagCompound) {
        super.writeToNBT(tagCompound);
        tagCompound.setTag("structure",
                AbsoluteCoordinate.getArrayAsNBTTagList(structure));
        if(itemhole!=null){
        tagCompound.setTag("itemhole", itemhole.getasNBTTagCompound());
        }
    }

    public void setStructure(AbsoluteCoordinate[] blocks) {
        structure = blocks;
        if(this instanceof IInventory){
            for (AbsoluteCoordinate coordinate : structure) {
                if(worldObj.isAirBlock(coordinate.xCoord, coordinate.yCoord, coordinate.zCoord)){
                    itemhole=coordinate;
                    break;
                }
            }
        }
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
