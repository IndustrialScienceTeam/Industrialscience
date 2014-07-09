package de.zsgn.industrialscience.factory.tileentity;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.ForgeDirection;
import ic2.api.energy.event.EnergyTileLoadEvent;
import ic2.api.energy.event.EnergyTileUnloadEvent;
import ic2.api.energy.tile.IEnergySink;

public class TileEntityEnergyLink extends TileEntityMultiBlock implements
        IEnergySink {
    protected boolean energynetloaded=false;

    public TileEntityEnergyLink() {
        super();
    }

    @Override
    public boolean acceptsEnergyFrom(TileEntity emitter,
            ForgeDirection direction) {
        return true;
    }

    @Override
    public double getDemandedEnergy() {
        return 5;
    }

    @Override
    public int getSinkTier() {
        System.err.println("Tier");
        return 2;
    }

    @Override
    public double injectEnergy(ForgeDirection directionFrom, double amount,
            double voltage) {
        System.err.println("Dir: "+directionFrom.name()+"  Amount: "+amount+" Voltage: "+voltage);
        return amount-5;
    }

    @Override
    public void updateEntity() {
        if(!energynetloaded&&!worldObj.isRemote){
            MinecraftForge.EVENT_BUS.post(new EnergyTileLoadEvent(this));
            energynetloaded=true;
        }
        super.updateEntity();
    }

    @Override
    public void invalidate() {
        if(!worldObj.isRemote){
            MinecraftForge.EVENT_BUS.post(new EnergyTileUnloadEvent(this));
        }
        super.invalidate();
    }

    @Override
    public void onChunkUnload() {
        if(!worldObj.isRemote){
            MinecraftForge.EVENT_BUS.post(new EnergyTileUnloadEvent(this));
        }
        super.onChunkUnload();
    }

}
