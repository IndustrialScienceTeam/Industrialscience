package mod.industrialscience.modules;

import java.util.Hashtable;
import java.util.logging.Logger;

import cpw.mods.fml.common.registry.LanguageRegistry;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;


public abstract class ISAbstractModule {
protected CreativeTabs CreativeTab = null;
public ISAbstractModule(){
	logger=Logger.getLogger(getName());
	logger.setParent(cpw.mods.fml.common.FMLLog.getLogger());
	
}
protected Logger logger;
protected Hashtable<String, Integer> BlockIDs;
protected Hashtable<String, Integer> ItemIDs;
public abstract void load();
public void setIDs(Hashtable<String, Integer> BlockIDs, Hashtable<String, Integer> ItemIDs){
	this.BlockIDs= BlockIDs;
	this.ItemIDs=ItemIDs;
}
public abstract void init();
public abstract Hashtable<String, Integer> getNeededBlockIDs();
public abstract Hashtable<String, Integer> getNeededItemIDs();
public abstract void postinit();
public abstract String getName();
public abstract ItemStack getIconitemstack();
public void initCreativeTab(){
	CreativeTab = new CreativeTabs(getName()){
		 public ItemStack getIconItemStack() {
             return getIconitemstack();
		 }};
		 LanguageRegistry.instance().addStringLocalization("itemGroup."+getName(), "en_US", getName());
	
}
}
