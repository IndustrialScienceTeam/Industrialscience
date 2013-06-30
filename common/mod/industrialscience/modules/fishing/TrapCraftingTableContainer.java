package mod.industrialscience.modules.fishing;

import mod.industrialscience.ISContainer;
import net.minecraft.entity.player.InventoryPlayer;

public class TrapCraftingTableContainer extends ISContainer {

    public TrapCraftingTableContainer(TrapCraftingTableTile tile,
            InventoryPlayer ip) {
        super(ip, tile);
    }

}
