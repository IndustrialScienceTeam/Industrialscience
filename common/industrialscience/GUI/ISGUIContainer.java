package industrialscience.GUI;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public abstract  class ISGUIContainer extends GuiContainer {
    protected ResourceLocation texture;
    protected String title;
    

    public ISGUIContainer(Container par1Container, ResourceLocation rl, String title) {
        super(par1Container);
        xSize = 182;
        ySize = 171;
        texture = rl;
        this.title=title;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
        GL11.glColor4f(1, 1, 1, 1);
        Minecraft.getMinecraft().renderEngine.bindTexture(texture);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
        _drawGuiContainerBackgroundLayer(f, i, j);
        
        
    }
    @Override
    protected void drawGuiContainerForegroundLayer(int par1, int par2) {
        fontRenderer.drawString(title, 8, 6, 0x404040);
    }
    protected abstract void _drawGuiContainerBackgroundLayer(float f, int i, int j);
    protected abstract void _drawGuiContainerForegroundLayer(int par1, int par2);
    
    

}
