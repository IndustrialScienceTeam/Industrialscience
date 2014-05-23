package de.zsgn.industrialscience.items;

import de.zsgn.industrialscience.IndustrialScience;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;

public class itemCrack extends ItemFood {

	public itemCrack() {
		super(1, true);
		
		setCreativeTab(IndustrialScience.creativetab);
		
		setUnlocalizedName("Crack!");
		// TODO Auto-generated constructor stub
	}

}
