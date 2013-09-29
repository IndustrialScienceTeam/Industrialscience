// Date: 07.08.2013 10:09:53
// Template version 1.1
// Java generated by Techne
// Keep in mind that you still need to fill in some blanks
// - ZeuX

package industrialscience.modules.research.frontend.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.tileentity.TileEntity;

public class ResearchDeskModel extends ModelBase {
    // fields
    ModelRenderer Leg1;
    ModelRenderer Leg2;
    ModelRenderer Leg3;
    ModelRenderer Leg4;
    ModelRenderer tablebase;
    ModelRenderer brace1_1;
    ModelRenderer brace2_1;
    ModelRenderer brace1_2;
    ModelRenderer brace2_2;

    public ResearchDeskModel() {
        textureWidth = 128;
        textureHeight = 32;

        Leg1 = new ModelRenderer(this, 0, 0);
        Leg1.addBox(0F, 0F, 0F, 2, 14, 2);
        Leg1.setRotationPoint(-8F, 10F, 6F);
        Leg1.setTextureSize(128, 32);
        Leg1.mirror = true;
        setRotation(Leg1, 0F, 0F, 0F);
        Leg2 = new ModelRenderer(this, 0, 0);
        Leg2.addBox(0F, 0F, 0F, 2, 14, 2);
        Leg2.setRotationPoint(6F, 10F, 6F);
        Leg2.setTextureSize(128, 32);
        Leg2.mirror = true;
        setRotation(Leg2, 0F, 0F, 0F);
        Leg3 = new ModelRenderer(this, 0, 0);
        Leg3.addBox(0F, 0F, 0F, 2, 14, 2);
        Leg3.setRotationPoint(6F, 10F, -8F);
        Leg3.setTextureSize(128, 32);
        Leg3.mirror = true;
        setRotation(Leg3, 0F, 0F, 0F);
        Leg4 = new ModelRenderer(this, 0, 0);
        Leg4.addBox(0F, 0F, 0F, 2, 14, 2);
        Leg4.setRotationPoint(-8F, 10F, -8F);
        Leg4.setTextureSize(128, 32);
        Leg4.mirror = true;
        setRotation(Leg4, 0F, 0F, 0F);
        tablebase = new ModelRenderer(this, 0, 0);
        tablebase.addBox(0F, 0F, 0F, 16, 2, 16);
        tablebase.setRotationPoint(-8F, 8F, -8F);
        tablebase.setTextureSize(128, 32);
        tablebase.mirror = true;
        setRotation(tablebase, 0F, 0F, 0F);
        brace1_1 = new ModelRenderer(this, 0, 20);
        brace1_1.addBox(0F, 0F, 0F, 2, 9, 1);
        brace1_1.setRotationPoint(6F, 8F, -1F);
        brace1_1.setTextureSize(128, 32);
        brace1_1.mirror = true;
        setRotation(brace1_1, -0.7853982F, 0F, 0F);
        brace2_1 = new ModelRenderer(this, 0, 20);
        brace2_1.addBox(0F, 0F, 0F, 2, 9, 1);
        brace2_1.setRotationPoint(-8F, 8F, -1F);
        brace2_1.setTextureSize(128, 32);
        brace2_1.mirror = true;
        setRotation(brace2_1, -0.7853982F, 0F, 0F);
        brace1_2 = new ModelRenderer(this, 0, 20);
        brace1_2.addBox(0F, 0F, 0F, 2, 8, 1);
        brace1_2.setRotationPoint(6F, 9F, 1F);
        brace1_2.setTextureSize(128, 32);
        brace1_2.mirror = true;
        setRotation(brace1_2, 0.7853982F, 0F, 0F);
        brace2_2 = new ModelRenderer(this, 0, 20);
        brace2_2.addBox(0F, 0F, 0F, 2, 8, 1);
        brace2_2.setRotationPoint(-8F, 9F, 1F);
        brace2_2.setTextureSize(128, 32);
        brace2_2.mirror = true;
        setRotation(brace2_2, 0.7853982F, 0F, 0F);
    }

    public void render(TileEntity entity, float f, float f1, float f2,
            float f3, float f4, float f5) {
        super.render(null, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        Leg1.render(f5);
        Leg2.render(f5);
        Leg3.render(f5);
        Leg4.render(f5);
        tablebase.render(f5);
        brace1_1.render(f5);
        brace2_1.render(f5);
        brace1_2.render(f5);
        brace2_2.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void setRotationAngles(float f, float f1, float f2, float f3,
            float f4, float f5, TileEntity entity) {
    }

}
