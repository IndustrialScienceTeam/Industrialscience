package mods.battlegear2.api;


import net.minecraft.item.ItemStack;

//This is a tempory fix until we get the heradry system up and running
public interface IDyable {

    /**
     * Return the color for the specified armor ItemStack.
     */
    public int getColor(ItemStack par1ItemStack);


    public int getDefaultColor(ItemStack par1ItemStack);

    /**
     * Return whether the specified armor ItemStack has a color.
     */
    public boolean hasColor(ItemStack par1ItemStack);

    /**
     * Remove the color from the specified armor ItemStack.
     */
    public void removeColor(ItemStack par1ItemStack);

    public void setColor(ItemStack dyable, int rgb);


}
