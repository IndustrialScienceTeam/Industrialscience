package appeng.api.me.util;

import appeng.api.IAEItemStack;
import appeng.api.IItemList;

/**
 * Lets you access Internal Cell Inventories.
 */
public interface IMEInventory
{
    /**
     *  Adds input, to the inventory, and returns items not added, complete failure yields a copy of the stack that was passed.
     */
    public IAEItemStack addItems( IAEItemStack input );
    
    /**
     * Identical to addItems(...) but it don't change anything, its just a simulation.
     * this is used when dealing with pipes/tubes, and routing.
     */
    public IAEItemStack calculateItemAddition(IAEItemStack stack);
    
    /**
     * True or False if this item is inside this inventory.
     */
    public boolean containsItemType(IAEItemStack i);
    
    /**
     *  Returns how many of this item are in the inventory, regardless of a how many stacks / cells or anything else.
     */
    public long countOfItemType(IAEItemStack i);

    /**
     * Attempts to extract the requested item, in the count specified by the request, returns items extracted, complete failure yields NULL.
     */
    public IAEItemStack extractItems( IAEItemStack request );
    
    /**
     * Returns a list of all available items, with stackSize set to the real amount, without stack limits.
     */
    public IItemList getAvailableItems();
    
    /**
     * Returns a list of all available items, with stackSize set to the real amount, without stack limits.
     */
    public IItemList getAvailableItems( IItemList out );
    
    // calculates available space for a specific item.
	public long getAvailableSpaceByItem( IAEItemStack i, long maxNeeded );
    
    /**
     * The total number of types holdable in this inventory.
     */
    public long getTotalItemTypes();
    
    // DO NOT USE ItemStack.split, these are for information purpose ONLY!
    // I you wan to remove items from the cell, use extractItemss
    
    /**
     * The estimated number of additional items this inventory can hold, regardless of type.
     */
    public long remainingItemCount();
    
    /**
     * The estimated number of additional types the inventory could hold.
     */
    public long remainingItemTypes();
    
    /**
     * The number of stored items total, regardless of type.
     */
    public long storedItemCount();
    
	/**
     * The number of different item types stored in inventory.
     */
    public long storedItemTypes();
}
