package de.zsgn.industrialscience.item;

import de.zsgn.industrialscience.IndustrialScience;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;

public class ItemClimbingBoots extends ItemArmor{
	
	public ItemClimbingBoots(){
	super(ArmorMaterial.CLOTH,0,0);
	
	setCreativeTab(IndustrialScience.creativetab);
	
	setUnlocalizedName("climbingboots");
	
	
	}
}


