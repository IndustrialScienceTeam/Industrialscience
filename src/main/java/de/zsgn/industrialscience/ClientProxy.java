package de.zsgn.industrialscience;

import de.zsgn.industrialscience.item.climbingboots_model;
import net.minecraft.client.model.ModelBiped;

public class ClientProxy extends CommonProxy {

    private static final climbingboots_model normal = new climbingboots_model(1.0f);
    private static final climbingboots_model smaller = new climbingboots_model(0.5f);
    
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
