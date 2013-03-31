package mod.industrialscience.modules.research;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import mod.industrialscience.ISContainer;

public class CopierContainer extends ISContainer {
	private CopierTile tile_entity = null;

	public CopierContainer(CopierTile ct,InventoryPlayer ip) {
		super(ip);
		tile_entity=ct;
	}

	@Override
	public boolean canInteractWith(EntityPlayer entityplayer) {
		return tile_entity.isUseableByPlayer(entityplayer);
	}
	

}
