package de.zsgn.industrialscience.item;

import net.minecraft.item.ItemArmor;
import de.zsgn.industrialscience.IndustrialScience;

public class ItemClimbingBoots extends ItemArmor{

    public ItemClimbingBoots(){

        //first argument is the material of this armor , the second one is unknown , according to ItemArmor.class it is the renderIndex , I recommend using 0 , because it is working, the last one is the slot (0 = helmet , 1 = chestplate , 2 = leggings , 3 = boots)	
        super(ArmorMaterial.CLOTH,0,3);

        setCreativeTab(IndustrialScience.getInstance().getCreativetab());

        setUnlocalizedName("climbingboots");

        iconString =IndustrialScience.MODID + ":" + this.getUnlocalizedName().substring(5);
    }

}

