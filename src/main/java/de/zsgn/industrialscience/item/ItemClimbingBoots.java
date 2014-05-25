package de.zsgn.industrialscience.item;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import de.zsgn.industrialscience.IndustrialScience;

public class ItemClimbingBoots extends ItemArmor{
    
    public ItemClimbingBoots(){
        
        //first argument is the material of this armor , the second one is unknown , according to ItemArmor.class it is the renderIndex , I recommend using 0 , because it is working, the last one is the slot (0 = helmet , 1 = chestplate , 2 = leggings , 3 = boots)	
        super(ArmorMaterial.IRON,0,3);
        
        setCreativeTab(IndustrialScience.getInstance().getCreativetab());
        
        setUnlocalizedName("climbingboots");
        
        iconString =IndustrialScience.MODID + ":" + this.getUnlocalizedName().substring(5);
    }
    
    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
            return IndustrialScience.MODID + ":" + "textures/items/" + this.getUnlocalizedName().substring(5)+"_model.png";
    }
    
    
}
