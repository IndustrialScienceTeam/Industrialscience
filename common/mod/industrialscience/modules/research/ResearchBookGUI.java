package mod.industrialscience.modules.research;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;

public class ResearchBookGUI extends GuiContainer {

	public ResearchBookGUI(InventoryPlayer player_inventory) {
		super(new ResearchBookContainer(player_inventory));
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
		// TODO Auto-generated method stub

	}

}
