package de.zsgn.industrialscience;

import renderer.RendererChimney;
import cpw.mods.fml.client.registry.ClientRegistry;
import net.minecraft.client.model.ModelBiped;
import de.zsgn.industrialscience.factory.tileentity.TileEntityChimney;
import de.zsgn.industrialscience.item.ModelClimbingBoots;

public class ClientProxy extends CommonProxy {

    @Override
    public ModelBiped getArmorModel(int id) {
        return new ModelClimbingBoots(2.0f);
    }
    
    public void registerRenderThings() {
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityChimney.class, new RendererChimney());
}

}
