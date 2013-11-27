package industrialscience.modules.fishing.TileEntities;

import industrialscience.ISIInventory;
import net.minecraft.entity.player.EntityPlayer;

public class TrapCraftingTableTileEntity extends ISIInventory{

    public TrapCraftingTableTileEntity() {
    	super(11,64,"TRAPCRAFTINGTABLE");
    }

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		 return worldObj.getBlockTileEntity(xCoord, yCoord, zCoord) == this
	                && player.getDistanceSq(xCoord + 0.5, yCoord + 0.5,
	                        zCoord + 0.5) < 64;
	}

}
