package mod.industrialscience.modules.research;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;

public class ResearchBookGUI extends GuiContainer {
	int[] researchids;
	public ResearchBookGUI(InventoryPlayer player_inventory,int[] researchids) {
		super(new ResearchBookContainer(player_inventory, researchids));
		this.researchids=researchids;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
		// TODO Auto-generated method stub

	}

}
