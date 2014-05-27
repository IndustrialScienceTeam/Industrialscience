package de.zsgn.industrialscience;

import de.zsgn.industrialscience.item.ModelClimbingBoots;
import net.minecraft.client.model.ModelBiped;

public class ClientProxy extends CommonProxy {

    private static final ModelClimbingBoots normal = new ModelClimbingBoots(1.0f);
    private static final ModelClimbingBoots smaller = new ModelClimbingBoots(0.5f);
    
    public ModelBiped getArmorModel(int id){
        switch (id) {
        case 0:
        return normal;
        case 1:
        return normal;
        default:
        break;
        }
        return normal; 
        }
        

}
