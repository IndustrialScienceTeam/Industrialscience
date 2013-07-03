package industrialscience.modules.research.frontend;

import java.util.List;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;


import industrialscience.IISItem;
import industrialscience.modules.ISAbstractModule;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public class ResearchItem extends Item {
    public static final int RESEARCHBOOKID = 0;
    public static final int RESEARCHNOTEID=1;
    private IISItem subitems[]= new IISItem[2];
    public ResearchItem(int par1) {
        super(par1);
        subitems[0]=new ResearchBook();
        subitems[1]=new ResearchNote();
        this.setHasSubtypes(true);
        setCreativeTab(ISAbstractModule.getCreativeTab());
    }
    @Override
    public Icon getIconFromDamage(int par1){
        return subitems[par1].getItemIcon();
    }
    @Override
    public void registerIcons(IconRegister par1IconRegister) {
        for (IISItem item : subitems) {
            item.registerIcons(par1IconRegister);
        }
    }
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public void getSubItems(int par1, CreativeTabs par2CreativeTabs, List par3List)
    {
        for (int i = 0; i < subitems.length; i++) {
            par3List.add(new ItemStack(par1, 1, i));
        }
    }
    @Override
    public String getUnlocalizedName(ItemStack itemstack)
    {
          return getUnlocalizedName() + subitems[itemstack.getItemDamage()].getUnlocalizedName()+".name";
    }
    
    
    
    public void onCreated(ItemStack par1ItemStack, World par2World,
            EntityPlayer par3EntityPlayer) {
        subitems[par1ItemStack.getItemDamage()].onCreated(par1ItemStack, par2World, par3EntityPlayer);
    }
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World,
            EntityPlayer par3EntityPlayer) {
        return subitems[par1ItemStack.getItemDamage()].onItemRightClick(par1ItemStack, par2World, par3EntityPlayer);
    }
    @SuppressWarnings("rawtypes")
    public void addInformation(ItemStack par1ItemStack,
            EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
        subitems[par1ItemStack.getItemDamage()].addInformation(par1ItemStack, par2EntityPlayer, par3List, par4);
        
    }
    public String getReadableName(int metadata){
        return subitems[metadata].getReadableName();
    }
    public void register(){
        GameRegistry.registerItem(this, getUnlocalizedName());
        for (int i = 0; i < subitems.length; i++) {
            LanguageRegistry.addName(new ItemStack(this, 1,i), subitems[i].getReadableName());
        }
    }
  

}
