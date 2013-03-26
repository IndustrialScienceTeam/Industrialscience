package mod.industrialscience.modules;

import java.util.Hashtable;
import java.util.logging.Logger;


public abstract class ISAbstractModule {
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
}
