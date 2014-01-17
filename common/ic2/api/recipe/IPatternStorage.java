package ic2.api.recipe;

import net.minecraft.item.ItemStack;

public interface IPatternStorage {

	short getPatternCount();

	ItemStack getPatternItemstack(int index);

	int[] getPatternvalus(ItemStack itemstack);

	boolean transferPattern(ItemStack itemstack, int amountUU , int amountEU);

}
