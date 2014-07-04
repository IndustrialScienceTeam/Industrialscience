package de.zsgn.industrialscience;

import net.minecraft.client.model.ModelBiped;
import cpw.mods.fml.client.registry.ClientRegistry;
import de.zsgn.industrialscience.factory.tileentity.TileEntityChimney;
import de.zsgn.industrialscience.item.ModelClimbingBoots;
import de.zsgn.industrialscience.renderer.RendererChimney;

public class ClientProxy extends CommonProxy {

    @Override
    public ModelBiped getArmorModel(int id) {
        return new ModelClimbingBoots(2.0f);
    }

    @Override
    public void registerRenderThings() {
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityChimney.class,
                new RendererChimney());
    }

}
