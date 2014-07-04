package de.zsgn.industrialscience.renderer;

import org.lwjgl.opengl.GL11;

import de.zsgn.industrialscience.models.ModelChimney;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;

public class ItemRendererChimney implements IItemRenderer {
    protected final ModelChimney model;
    public ItemRendererChimney() {
        super();
        this.model = new ModelChimney();
    }

    @Override
    public boolean handleRenderType(ItemStack item, ItemRenderType type) {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item,
            ItemRendererHelper helper) {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
        GL11.glPushMatrix();
        GL11.glTranslatef((float)  0.5F, (float) 1.5F, (float) 0.5F);
        ResourceLocation textures = new ResourceLocation(
                "industrialscience:textures/blocks/ModelChimney.png");
        Minecraft.getMinecraft().renderEngine.bindTexture(textures);
        GL11.glPushMatrix();
        GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
        model.render((Entity) null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
        GL11.glPopMatrix();
        GL11.glPopMatrix();
    }

}
