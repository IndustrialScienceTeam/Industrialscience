package de.zsgn.industrialscience.tileentity;

import java.util.Iterator;
import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import de.zsgn.industrialscience.IndustrialScience;

public class TileEntityMysteriousPortal extends TileEntity {
    private boolean useable=false;
    private boolean active=false;
    private NBTTagCompound technologycompund=null;
    public boolean onBlockActivated(World world, int x, int y, int z,
            EntityPlayer player, int side, float xOffset, float yOffset,
            float zOffset) {
        if(!useable&&(player.inventory.getStackInSlot(player.inventory.currentItem)!=null&&player.inventory.getStackInSlot(player.inventory.currentItem).getItem()==IndustrialScience.getInstance().getItemancienttechnology())&&side==1){
            technologycompund=player.inventory.getStackInSlot(player.inventory.currentItem).getTagCompound();
            --player.inventory.getStackInSlot(player.inventory.currentItem).stackSize;
            useable=true;
            player.addChatMessage(new ChatComponentText(StatCollector.translateToLocal("mysteriousportal_activated")));
            return true;
        }
        return false;
    }
    
    @SuppressWarnings("rawtypes")
    @Override
    public void updateEntity() {
        if(isUseable()){
                    setActive(worldObj.getIndirectPowerLevelTo(xCoord, yCoord, zCoord, 0)>0);
                    if(isActive()){
                        AxisAlignedBB axisalignedbb =  AxisAlignedBB.getAABBPool().getAABB((double)(this.xCoord - 1), (double)(this.yCoord+1), (double)(this.zCoord - 1), (double)(this.xCoord + 1), (double)(this.yCoord + 3), (double)(this.zCoord + 1));
                        List list=  this.worldObj.getEntitiesWithinAABB(Entity.class, axisalignedbb);
                        Iterator iterator = list.iterator();
                        while (iterator.hasNext()) {
                            Object object = iterator.next();
                            if(object instanceof Entity){
                                Entity entity =(Entity) object;
                                entity.setDead();
                            }
                            
                        }
                    }
        }
    }

    @Override
    public void readFromNBT(NBTTagCompound tagCompound) {
        super.readFromNBT(tagCompound);
        useable=tagCompound.getBoolean("useable");
        active=tagCompound.getBoolean("active");
        if(tagCompound.getTag("technologycompund") instanceof NBTTagCompound){
        technologycompund=(NBTTagCompound) tagCompound.getTag("technologycompund");
        }
    }
    @Override
    public void writeToNBT(NBTTagCompound tagCompound) {
        super.writeToNBT(tagCompound);
        tagCompound.setBoolean("active", active);
        tagCompound.setBoolean("useable", useable);
        if(technologycompund!=null){
        tagCompound.setTag("technologycompund", technologycompund);
        }
    }
    public boolean isUseable() {
        return useable;
    }
    public NBTTagCompound getTechnologycompund() {
        return technologycompund;
    }
    public boolean isActive() {
        return active;
    }
    public void setActive(boolean active) {
        this.active = active;
    }

}
