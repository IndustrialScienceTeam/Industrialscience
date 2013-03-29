package mod.industrialscience.modules.research;

import org.lwjgl.opengl.GL11;

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
		GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        this.mc.renderEngine.func_98187_b("/mods/industrialscience/textures/gui/GUITemplate.png");
        int x = (width - xSize) / 2;
       
        int y = (height - ySize) / 2;
       
        this.drawTexturedModalRect(x, y, 0, 0, xSize, ySize);

	}

}
