package mod.industrialscience.modules;

import java.util.Hashtable;


public abstract class ISAbstractModule {
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
}
