package tconstruct.library.util;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class TabTools extends CreativeTabs
{
    ItemStack display;

    public TabTools(String label)
    {
        super(label);
    }

    @Override
	public ItemStack getIconItemStack ()
    {
        return display;
    }

    public void init (ItemStack stack)
    {
        display = stack;
    }
}