package industrialscience.modules.mining.tileentities;

import ic2.api.energy.prefab.BasicSink;
import ic2.api.energy.tile.IEnergySink;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;

public class BasicMinerMachineTileEntity extends TileEntity {
	private BasicSink ic2EnergySink = new BasicSink(this, 1000, 1);
	    @Override
	      public void invalidate() {
	        ic2EnergySink.invalidate();
	          super.invalidate();
	      }
	  
	      @Override
	      public void onChunkUnload() {
	          ic2EnergySink.onChunkUnload(); 
	           
	      }
	  
	      @Override
	      public void readFromNBT(NBTTagCompound tag) {
	          super.readFromNBT(tag);
	  
	          ic2EnergySink.readFromNBT(tag);
	           
	      }
	  
	      @Override
	      public void writeToNBT(NBTTagCompound tag) {
	          super.writeToNBT(tag);
	  
	          ic2EnergySink.writeToNBT(tag);
	           
	      }
	  
	      @Override
	      public void updateEntity() {
	          ic2EnergySink.updateEntity(); 
	           
	          if (ic2EnergySink.useEnergy(1000)) { 
	        	  System.out.println("LOL");
	          }
	           
	      }

}
