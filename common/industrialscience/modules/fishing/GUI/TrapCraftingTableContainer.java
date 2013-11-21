package industrialscience.modules.fishing.GUI;

import industrialscience.ISContainer;
import industrialscience.modules.fishing.TileEntities.TrapCraftingTableTileEntity;
import net.minecraft.entity.player.InventoryPlayer;

public class TrapCraftingTableContainer extends ISContainer {

    public TrapCraftingTableContainer(TrapCraftingTableTileEntity tile,
            InventoryPlayer ip) {
        super(ip, tile);
    }

}
