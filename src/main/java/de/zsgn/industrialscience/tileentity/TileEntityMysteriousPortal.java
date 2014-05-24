package de.zsgn.industrialscience.tileentity;

import de.zsgn.industrialscience.IndustrialScience;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class TileEntityMysteriousPortal extends TileEntity {
	private boolean activated=false;
	public boolean onBlockActivated(World world, int x, int y, int z,
			EntityPlayer player, int side, float xOffset, float yOffset,
			float zOffset) {
		if(!activated&&(player.inventory.getStackInSlot(player.inventory.currentItem)!=null)){
			if(player.inventory.getStackInSlot(player.inventory.currentItem).getItem()==IndustrialScience.instance.itemAncientTechnology){
				--player.inventory.getStackInSlot(player.inventory.currentItem).stackSize;
				activated=true;
			}
					
		}
		return false;
	}
	@Override
	public void readFromNBT(NBTTagCompound p_145839_1_) {
		activated=p_145839_1_.getBoolean("activated");
	}
	@Override
	public void writeToNBT(NBTTagCompound p_145841_1_) {
		p_145841_1_.setBoolean("activated", activated);
	}

}
