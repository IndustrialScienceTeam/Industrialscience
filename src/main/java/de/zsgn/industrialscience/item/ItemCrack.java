package de.zsgn.industrialscience.item;

import de.zsgn.industrialscience.IndustrialScience;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;

public class ItemCrack extends ItemFood {

	public ItemCrack() {
		super(1, true);
		
		setCreativeTab(IndustrialScience.creativetab);
		
		setUnlocalizedName("Crack!");
	}

}
