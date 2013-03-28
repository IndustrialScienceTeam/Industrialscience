package mod.industrialscience.modules.research;

import java.util.List;

import cpw.mods.fml.common.registry.LanguageRegistry;
import mod.industrialscience.IndustrialScience;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class ResearchBook extends Item {
	private static final String NBTNAME= "Researches";
	public ResearchBook(int id) {
		super(id);
		setMaxStackSize(64);
		setUnlocalizedName("Research Book");
		LanguageRegistry.addName(this, "Research Book");

	}
	@Override
	public void func_94581_a(IconRegister iconRegister)
	{
	         iconIndex = iconRegister.func_94245_a("industrialscience:research-book");
	}
	public void onCreated(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
    if( par1ItemStack.stackTagCompound == null )
    par1ItemStack.setTagCompound( new NBTTagCompound( ) );
    int[] researchesid={};
    par1ItemStack.stackTagCompound.setIntArray(NBTNAME, researchesid);
    
   }
	@Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World,
			EntityPlayer par3EntityPlayer) {
		par3EntityPlayer.openGui(IndustrialScience.instance, 1, par2World, par3EntityPlayer.getPlayerCoordinates().posX, par3EntityPlayer.getPlayerCoordinates().posY, par3EntityPlayer.getPlayerCoordinates().posZ);
		return super.onItemRightClick(par1ItemStack, par2World, par3EntityPlayer);
	}
	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, @SuppressWarnings("rawtypes") List par3List, boolean par4) {
		int[] researchesid= par1ItemStack.stackTagCompound.getIntArray(NBTNAME);
		if(researchesid!=null){
			par3List.add("Researches: "+researchesid.length);
		}
	}
	

}
