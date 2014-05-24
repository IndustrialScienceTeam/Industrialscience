package de.zsgn.industrialscience.item;

import java.util.List;

import de.zsgn.industrialscience.IndustrialScience;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.StatCollector;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.world.World;
import net.minecraftforge.common.ChestGenHooks;

public class ItemAncientTechnology extends Item {
	protected final static int descriptionamount=3;
	public ItemAncientTechnology() {
		setCreativeTab(IndustrialScience.getInstance().getCreativetab());
		setUnlocalizedName("ancient-technology");
		setMaxStackSize(1);
		ChestGenHooks.addItem(ChestGenHooks.PYRAMID_DESERT_CHEST, new WeightedRandomChestContent(new ItemStack(this, 1), 1, 1, 7));
		ChestGenHooks.addItem(ChestGenHooks.PYRAMID_JUNGLE_CHEST, new WeightedRandomChestContent(new ItemStack(this, 1), 1, 1, 7));
		ChestGenHooks.addItem(ChestGenHooks.DUNGEON_CHEST, new WeightedRandomChestContent(new ItemStack(this, 1), 1, 1, 7));
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void addInformation(ItemStack par1ItemStack,
			EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
		par3List.add(StatCollector.translateToLocal("item.ancient-technology.descr"+getDescriptionNumber(par1ItemStack)));
	}

	private int getDescriptionNumber(ItemStack par1ItemStack) {
		if(par1ItemStack.stackTagCompound==null)
			par1ItemStack.stackTagCompound=new NBTTagCompound();
		int descriptionnumber=par1ItemStack.stackTagCompound.getInteger(IndustrialScience.MODID+"_descriptionnumber");
		if(descriptionnumber==0){
			descriptionnumber=itemRand.nextInt(descriptionamount)+1;
			par1ItemStack.stackTagCompound.setInteger(IndustrialScience.MODID+"_descriptionnumber", descriptionnumber);
		}
		return descriptionnumber;
	}

	@Override
	public void onCreated(ItemStack par1ItemStack, World par2World,
			EntityPlayer par3EntityPlayer) {
		getDescriptionNumber(par1ItemStack);
	}

}
