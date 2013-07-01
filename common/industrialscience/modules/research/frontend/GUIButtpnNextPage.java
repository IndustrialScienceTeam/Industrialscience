package industrialscience.modules.research.frontend;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
class GuiButtonNextPage extends GuiButton {
    /**
     * True for pointing right (next page), false for pointing left (previous
     * page).
     */
    private final boolean nextPage;

    public GuiButtonNextPage(int par1, int par2, int par3, boolean par4) {
        super(par1, par2, par3, 23, 13, "");
        nextPage = par4;
    }

    /**
     * Draws this button to the screen.
     */
    @Override
    public void drawButton(Minecraft par1Minecraft, int par2, int par3) {
        if (drawButton) {
            boolean flag = par2 >= xPosition && par3 >= yPosition
                    && par2 < xPosition + width && par3 < yPosition + height;
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            par1Minecraft.renderEngine.bindTexture("/gui/book.png");
            int k = 0;
            int l = 192;

            if (flag) {
                k += 23;
            }

            if (!nextPage) {
                l += 13;
            }

            this.drawTexturedModalRect(xPosition, yPosition, k, l, 23, 13);
        }
    }
}
