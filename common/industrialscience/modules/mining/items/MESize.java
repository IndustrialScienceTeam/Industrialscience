package industrialscience.modules.mining.items;

import industrialscience.modules.ISAbstractModule;

import java.util.ArrayList;
import java.util.logging.Level;

import net.minecraft.item.ItemStack;

public class MESize {
protected static ArrayList<MESize> sizes= new ArrayList<MESize>();
public static ArrayList<MESize> getSizes() {
	return sizes;
}
public static void init(){
	try{
	sizes.add(new MESize(1024, (ItemStack) Class.forName("appeng.api.Materials").getField("matStorageCell").get(null)));
	sizes.add(new MESize(4096, (ItemStack) Class.forName("appeng.api.Materials").getField("matStorageSegment").get(null)));
	sizes.add(new MESize(16384, (ItemStack) Class.forName("appeng.api.Materials").getField("matStorageBlock").get(null)));
	sizes.add(new MESize(65536, (ItemStack) Class.forName("appeng.api.Materials").getField("matStorageCluster").get(null)));
	}
	 catch (Exception e) {
		ISAbstractModule.getLogger().log(Level.WARNING, "Couldn't get AppEng material", e);
	}
}
private int size;
private ItemStack storagepart;
public MESize(int size, ItemStack storagepart) {
	this.size = size;
	this.storagepart = storagepart;
}
public boolean add(MESize arg0) {
	return sizes.add(arg0);
}
public int getSize() {
	return size;
}
public ItemStack getStoragepart() {
	return storagepart.copy();
}
}
