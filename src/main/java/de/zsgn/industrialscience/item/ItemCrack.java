package de.zsgn.industrialscience.item;

import de.zsgn.industrialscience.IndustrialScience;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;

public class ItemCrack extends ItemFood {

	public ItemCrack() {
		//refers to the superclass and gives it arguments. The first argument is the food filling value and the second argument determines, whether dogs will eat the food.
		super(6, true);
		
		//sets the creative tab to our creative tab so one can actually find the item ingame
		setCreativeTab(IndustrialScience.creativetab);
		
		//required for the naming stuff (this is not the display name)
		setUnlocalizedName("Crack");
	}

}
