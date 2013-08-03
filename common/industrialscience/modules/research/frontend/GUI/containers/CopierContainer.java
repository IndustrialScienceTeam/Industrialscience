package industrialscience.modules.research.frontend.GUI.containers;

import industrialscience.ISContainer;
import industrialscience.modules.research.frontend.TileEntities.CopierTile;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;

public class CopierContainer extends ISContainer {
    public CopierContainer(CopierTile ct, InventoryPlayer ip) {
        super(ip, ct);
        addSlotToContainer(new Slot(ct, ct.PAGEINPUT, 53, 34));
        addSlotToContainer(new Slot(ct, ct.PAGEOUTPUT, 107, 34));
    }

}
