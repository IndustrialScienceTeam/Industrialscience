package de.zsgn.industrialscience.item;

import de.zsgn.industrialscience.IndustrialScience;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemCrack extends ItemFood {

	public ItemCrack() {
		//refers to the superclass and gives it arguments. The first argument is the food filling value and the second argument determines, whether dogs will eat the food.
		super(1, true);
		
		//sets the creative tab to our creative tab so one can actually find the item ingame
		setCreativeTab(IndustrialScience.creativetab);
		
		//required for the naming stuff (this is not the display name)
		setUnlocalizedName("Crack");
		
		//makes sure, that you can eat crack all day
		setAlwaysEdible();
	}

	@Override
	protected void onFoodEaten(ItemStack par1ItemStack, World par2World,
			EntityPlayer par3EntityPlayer) {
		if(!par2World.isRemote) {
			
			//Adds Instant damage effect with duration of 1 tick (I think duration does not matter with this type of potion effect)
			par3EntityPlayer.addPotionEffect(new PotionEffect(7, 1));
			
			//Adds Jump Boost (8) for 10 seconds (200 ticks) and increases its level by 1 (1): Jump boost 2 for 10 seconds
			par3EntityPlayer.addPotionEffect(new PotionEffect(8, 200, 1));
		}
	}

	@Override
	public void registerIcons(IIconRegister par1IconRegister) {
		itemIcon = par1IconRegister.registerIcon(IndustrialScience.MODID + ":" + this.getUnlocalizedName().substring(5));
	}

}
