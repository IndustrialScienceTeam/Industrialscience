package industrialscience.modules.research.frontend.renderer;

import industrialscience.modules.research.frontend.ResearchBlockType;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

import org.lwjgl.opengl.GL11;

public class ResearchDeskRenderer extends TileEntitySpecialRenderer {

    @Override
    public void renderTileEntityAt(TileEntity tileentity, double d0, double d1,
            double d2, float f) {
        int metavalue = 0;
        if (tileentity.getWorldObj() != null) {
            metavalue = tileentity.getWorldObj().getBlockMetadata(
                    tileentity.xCoord, tileentity.yCoord, tileentity.zCoord);
        }
      //  bindTextureByName(ResearchBlockType.values()[metavalue].getModelfile());
        GL11.glPushMatrix();
        GL11.glTranslatef((float) d0 + 0.5F, (float) d1 + 1.5F,
                (float) d2 + 0.5F);
        GL11.glRotatef(0, 0.0F, 1.0F, 0.0F);
        GL11.glScalef(1.0F, -1F, -1F);
        try {
            ResearchBlockType.values()[metavalue].getModel().newInstance()
                    .renderModel(0.0625F);
        } catch (InstantiationException e) {
            // IMPOSSIBLE
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // IMPOSSIBLE
            e.printStackTrace();
        }
        GL11.glPopMatrix();

    }

}
