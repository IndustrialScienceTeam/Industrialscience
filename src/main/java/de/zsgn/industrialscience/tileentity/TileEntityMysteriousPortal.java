package de.zsgn.industrialscience.tileentity;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;

public class TileEntityMysteriousPortal extends TileEntity {
    private static Integer[] effectlist={};
    private boolean useable=false;
    private boolean active=false;
    private int badeffectchance=1;
    private Random random= new Random();
    private NBTTagCompound technologycompund=null;    
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
                                teleport(entity);
                            }
                            
                        }
                    }
        }
    }

    private void teleport(Entity entity) {
        if(entity instanceof EntityLivingBase){
            ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(9,100,1));
            if(random.nextInt(badeffectchance)==0){
                applyBadEffect((EntityLivingBase)entity);
            }
        }
        entity.travelToDimension(-1);
    }

    private void applyBadEffect(EntityLivingBase entity) {
        int effectamount = random.nextInt(effectlist.length);
        for (int i = 0; i < effectamount; i++) {
            entity.addPotionEffect(new PotionEffect(effectlist[random.nextInt(effectlist.length)], (int) (random.nextDouble()*500), 1));
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

    public void setUseable(boolean useable) {
        this.useable = useable;
    }

    public void setTechnologycompund(NBTBase nbtBase) {
        if(nbtBase instanceof NBTTagCompound){
        this.technologycompund = (NBTTagCompound)nbtBase;
        }
    }

    public int getBadeffectchance() {
        return badeffectchance;
    }

    public void setBadeffectchance(int badeffectchance) {
        this.badeffectchance = badeffectchance;
    }

    public static void setEffectlist(Integer[] integers) {
        TileEntityMysteriousPortal.effectlist = integers;
    }

}
