package mod.industrialscience.modules.research;

import mod.industrialscience.ISContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;

public class ResearchBookContainer extends ISContainer {
	public ResearchBookContainer(InventoryPlayer player_inventory, int[] researchids){
		super(player_inventory);
	}

	@Override
	public boolean canInteractWith(EntityPlayer entityplayer) {
		return true;
	}

}
