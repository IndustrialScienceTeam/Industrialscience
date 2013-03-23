package mod.industrialscience.modules.research;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.StatCollector;

public class ResearchDeskGUI extends GuiContainer {
    public ResearchDeskGUI(InventoryPlayer player_inventory, ResearchDeskTile tile_entity){
        super(new ResearchDeskContainer(tile_entity, player_inventory));
}

    @Override
    protected void drawGuiContainerForegroundLayer(int i, int j){
   
            fontRenderer.drawString("Tutorial Gui", 6, 6, 0xffffff);
            fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 6, ySize - 96 , 0xffffff);
    }
   
   
    @Override
    protected void drawGuiContainerBackgroundLayer(float f, int i, int j){
           
            GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
            this.mc.renderEngine.func_98187_b("gui/container.png");
            int x = (width - xSize) / 2;
           
            int y = (height - ySize) / 2;
           
            this.drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
    }
}
