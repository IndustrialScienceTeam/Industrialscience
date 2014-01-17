package appeng.api;

import net.minecraft.item.ItemStack;

/**
 * Lets you manipulate existing recipes.
 */
public interface IAppEngGrinderRecipe {
	
	/**
	 * Energy cost, in turns.
	 * @return number of turns it takes to produce the output from the input.
	 */
	public int getEnergyCost();
	
	/**
	 * the current input
	 * @return input that the grinder will accept.
	 */
	public ItemStack getInput();
	
	/**
	 * gets the current output
	 * @return output that the grinder will produce
	 */
	public ItemStack getOutput();
	
	/**
	 * Allows you to adjust the number of turns 
	 * @param new number of turns to produce output.
	 */
	public void setEnergyCost(int c);
	
	/**
	 * lets you change the grinder recipe by changing its input.
	 * @param input
	 */
	public void setInput( ItemStack input );
	
	/**
	 * allows you to change the output.
	 * @param output
	 */
	public void setOutput( ItemStack output );
}
