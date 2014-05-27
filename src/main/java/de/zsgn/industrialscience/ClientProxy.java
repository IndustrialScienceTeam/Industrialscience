package de.zsgn.industrialscience;

import de.zsgn.industrialscience.item.climbingboots_model;
import net.minecraft.client.model.ModelBiped;

public class ClientProxy extends CommonProxy {

    private static final climbingboots_model tutChest = new climbingboots_model(1.0f);
    private static final climbingboots_model tutLegs = new climbingboots_model(0.5f);
    
    public ModelBiped getArmorModel(int id){
        switch (id) {
        case 0:
        return tutChest;
        case 1:
        return tutLegs;
        default:
        break;
        }
        return tutChest; 
        }
        

}
