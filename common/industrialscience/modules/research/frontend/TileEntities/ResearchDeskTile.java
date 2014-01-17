package industrialscience.modules.research.frontend.TileEntities;

import industrialscience.ISIInventory;
import net.minecraft.entity.player.EntityPlayer;

public class ResearchDeskTile extends ISIInventory {
    public final static int BOOKSLOT = 2;
    public final static int ITEMSLOT = 1;
    public final static int PAGESLOT = 0;

    public ResearchDeskTile() {
    	super(3,64,"Research Desk");
    }


    @Override
    public boolean isUseableByPlayer(EntityPlayer player) {
        return worldObj.getBlockTileEntity(xCoord, yCoord, zCoord) == this
                && player.getDistanceSq(xCoord + 0.5, yCoord + 0.5,
                        zCoord + 0.5) < 64;
    }


}
