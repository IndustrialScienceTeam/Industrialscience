package industrialscience.modules.fishing.GUI.containers;

import industrialscience.GUI.ISContainer;
import industrialscience.modules.fishing.TileEntities.TrapCraftingTableTileEntity;
import net.minecraft.entity.player.InventoryPlayer;

public class TrapCraftingTableContainer extends ISContainer {

    public TrapCraftingTableContainer(TrapCraftingTableTileEntity tile,
            InventoryPlayer ip) {
        super(ip, tile);
    }

}
