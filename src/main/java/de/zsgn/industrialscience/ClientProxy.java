package de.zsgn.industrialscience;

import net.minecraft.client.model.ModelBiped;
import de.zsgn.industrialscience.item.ModelClimbingBoots;

public class ClientProxy extends CommonProxy {
    
    @Override
    public ModelBiped getArmorModel(int id) {
        ModelClimbingBoots modelClimbingBoots = new ModelClimbingBoots(1.0f);
        return modelClimbingBoots;
    }
    
}
