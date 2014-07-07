package de.zsgn.industrialscience;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;
import cpw.mods.fml.client.registry.ClientRegistry;
import de.zsgn.industrialscience.factory.tileentity.TileEntityChimney;
import de.zsgn.industrialscience.item.ModelClimbingBoots;
import de.zsgn.industrialscience.renderer.ItemRendererChimney;
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
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(IndustrialScience.getInstance().getFactoryModule().getBlockChimney()), new ItemRendererChimney());
    }

}
