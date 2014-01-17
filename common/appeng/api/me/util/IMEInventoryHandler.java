package appeng.api.me.util;

import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import appeng.api.IAEItemStack;
import appeng.api.IItemList;
import appeng.api.config.FuzzyMode;
import appeng.api.config.ItemFlow;
import appeng.api.config.ListMode;

public interface IMEInventoryHandler extends IMEInventory {
	
	public boolean canAccept(IAEItemStack input);
    /**
     * True of False, if you could add a new item type.
     */
    public boolean canHoldNewItem();
	
    /**
     * Returns estimated number of free bytes represented by inventory, used mainly for display.
     */
    public long freeBytes();
    public ItemFlow getFlow();
	
    FuzzyMode getFuzzyModePreformatted();
    
    IGridInterface getGrid();
    
    ListMode getListMode();
    
    String getName();
    
    IMEInventoryHandler getParent();
    
    List<ItemStack> getPreformattedItems();
    
    public int getPriority();	
	boolean isFuzzyPreformatted();
	
	boolean isPreformatted();
	void removeGrid(IGridInterface grid, IMEInventoryHandler ignore, List<IMEInventoryHandler> duplicates );
	
	public void setFlow( ItemFlow p );	
	void setFuzzyPreformatted( boolean nf );
	
	void setGrid( IGridInterface grid );
	
	public void setName(String name);
	void setParent( IMEInventoryHandler p );
	
	void setPreformattedItems(IItemList in, FuzzyMode mode, ListMode m);
	public void setPriority( int p );
	
	/**
     * This tells you where it found your cell, so if your cell changes, you should update this block...
     */
    void setUpdateTarget(TileEntity e);
	/**
     * Returns estimated number of total bytes represented by the inventory, used mainly for display.
     */
    public long totalBytes();
	
	/**
     * The number of items you could add before the freeBytes() decreases.
     */
    public long unusedItemCount();
	/**
     * Returns number of used bytes represented by the inventory, used mainly for display.
     */
    public long usedBytes();
	public void validate( List<IMEInventoryHandler> duplicates );
	
}
