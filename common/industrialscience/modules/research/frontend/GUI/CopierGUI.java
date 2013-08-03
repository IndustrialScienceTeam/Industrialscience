package industrialscience.modules.research.frontend.GUI;

import org.lwjgl.opengl.GL11;

import industrialscience.ISGUIContainer;
import industrialscience.modules.research.frontend.GUI.containers.CopierContainer;
import industrialscience.modules.research.frontend.TileEntities.CopierTile;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

public class CopierGUI extends GuiContainer {

	private static final ResourceLocation texture = new ResourceLocation("industrialscience","textures/gui/GUIResearchCopier.png");

	public CopierGUI(CopierTile ct,InventoryPlayer ip) {
		super(new CopierContainer(ct, ip));
		xSize=182;
		ySize=171;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
		GL11.glColor4f(1, 1, 1, 1);
		Minecraft.getMinecraft().func_110434_K().func_110577_a(texture);
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
		
	}

}
