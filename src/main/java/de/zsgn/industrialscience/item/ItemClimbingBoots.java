package de.zsgn.industrialscience.item;

import de.zsgn.industrialscience.IndustrialScience;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;

public class ItemClimbingBoots extends ItemArmor{
	
	public ItemClimbingBoots(){
		
	//first argument is the material of this armor , the second one is unknown , according to ItemArmor.class it�s the renderIndex , I recommend using 0 , because it is working, the last one is the slot (0 = helmet , 1 = chestplate , 2 = leggings , 3 = boots)	
	super(ArmorMaterial.CLOTH,0,3);
	
	setCreativeTab(IndustrialScience.creativetab);
	
	setUnlocalizedName("climbingboots");
	
	iconString =IndustrialScience.MODID + ":" + this.getUnlocalizedName().substring(5);
	}
	
}



