package mod.industrialscience.modules.research.backend.model;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;

public class DefaultLocker implements RecipeLocker {
	private Object[] recipe;
	private ItemStack istack;
	public DefaultLocker(Object[] recipe, ItemStack istack){
		this.recipe=recipe;
		this.istack=istack;
	}
	@Override
	public void lock() {
		System.out.println("UNLOCK!!!!!!!!!!!!!");
	}

	@Override
	public void unlock() {
		System.out.println("LOCK!!!!!!!!!!!!!!!");
		CraftingManager.getInstance().addRecipe(istack, recipe);
	}

}
