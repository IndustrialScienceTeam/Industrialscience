package appeng.api.me.util;

import java.util.List;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;
import appeng.api.IItemList;
import appeng.api.me.tiles.IPushable;

/**
 * Interact with the internals of assembler patterns, get this via Util.getAssemblerPattern(...)
 */
public interface IAssemblerPattern
{
	
	/**
     * Returns a condensed list of requirements.
     * 
     * Example: sticks, will return a single stack of 2, rather then two stacks of 1.
     * The same Item will not show more than one stack.
     */
    public List<ItemStack> condensedRequirements();
	
    /** 
     * Encode a pattern.
     * craftingMatrix - accepts a 3x3 grid of ItemStacks and Nulls.
     * output - accepts a single ItemStack, NEVER SEND NULL 
     */
    void encodePattern(ItemStack[] craftingMatrix, ItemStack output);
    
    /**
	 * Compare to Patterns
	 * @param obj
	 * @return
	 */
	@Override
	public boolean equals(Object obj);
    
    IAssemblerCluster getCluster();
    
    /** 
     * Same as getCraftingMatrix(), but gets a crafting inventory for real crafting.
     * 
     * Item pool is optional, null will work, but it won't be able to edit items... 
     */
    InventoryCrafting getCraftingInv( World w, IMEInventory itemPool, List<ItemStack> missing, List<ItemStack> used, IItemList all );
    
    /** Returns a 3x3 matrix of nulls or ItemStacks, or null if it is not included. */
    ItemStack[] getCraftingMatrix();
    
    /** Returns the Tile Entity for the interface... */
	List<IPushable> getInterface();
    
    /** returns the recipe for the patterns */
	IRecipe getMatchingRecipe( InventoryCrafting ic, World w );
    
    /** Returns a ItemStack, if the pattern is encoded, this will ALWAYS have a value. */
    ItemStack getOutput();
	
	/** returns the output of the pattern */
	ItemStack getRecipeOutput( InventoryCrafting ic, World w );
	
	/**
     * I have no idea what the World is for, its just part of IRecipe...
     */
    boolean isCraftable(World w);
	
	/** Returns true if there is a pattern encoded. */
    boolean isEncoded();
	
	/** Sets the Tile Entity for the interface... */
	void setInterface(IPushable a);
	
}

