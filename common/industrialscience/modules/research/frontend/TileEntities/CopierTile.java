package industrialscience.modules.research.frontend.TileEntities;

import industrialscience.ISIInventory;
import industrialscience.modules.ResearchModule;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class CopierTile extends ISIInventory {
    public final static int PAGEINPUT = 0;
    public final static int PAGEOUTPUT = 1;
    public final static int UPGRADESLOT = 2;

    public CopierTile() {
        super(3,64,"Research Copier");
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer entityplayer) {
        return worldObj.getBlockTileEntity(xCoord, yCoord, zCoord) == this
                && entityplayer.getDistanceSq(xCoord + 0.5, yCoord + 0.5,
                        zCoord + 0.5) < 64;
    }

    @Override
    public boolean isItemValidForSlot(int i, ItemStack itemstack) {
        if ((i == PAGEINPUT)
                && itemstack.itemID == ResearchModule.researchNoteID)
            return true;
        if (itemstack.itemID == ResearchModule.researchbookID)
            return true;
        return false;
    }

}
