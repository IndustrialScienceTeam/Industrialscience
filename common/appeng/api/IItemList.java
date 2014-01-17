package appeng.api;

import java.util.Iterator;
import java.util.List;

import net.minecraft.item.ItemStack;

/**
 * Represents a list of items in AE.
 * 
 * Don't Implement.
 * 
 * Construct with Util.createItemList()
 * 
 */
public interface IItemList extends Iterable<IAEItemStack>
{
	public void add( IAEItemStack option ); // adds stack as is.
	public void addCrafting( IAEItemStack option ); // adds a stack as craftable.
	public void addRequestable( IAEItemStack option ); // adds a stack as requestable.
	public void addStorage( IAEItemStack option ); // adds a stack as stored.
	
	IAEItemStack findItem(IAEItemStack i);
	
	IAEItemStack getFirstItem();
	public List<ItemStack> getItems();
	@Override
	public Iterator<IAEItemStack> iterator();
	
	public void setCurrentPriority(int priority);
	int size();
}
