package industrialscience.modules.research.frontend.GUI.containers;

import industrialscience.ISContainer;
import industrialscience.modules.research.frontend.TileEntities.CopierTile;
import net.minecraft.entity.player.InventoryPlayer;

public class CopierContainer extends ISContainer {
    public CopierContainer(CopierTile ct, InventoryPlayer ip) {
        super(ip, ct);
    }

}
