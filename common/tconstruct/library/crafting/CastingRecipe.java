package tconstruct.library.crafting;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

public class CastingRecipe
{
    public ItemStack cast;
    public FluidStack castingMetal;
    public boolean consumeCast;
    public int coolTime;
    public ItemStack output;

    public CastingRecipe(ItemStack replacement, FluidStack metal, ItemStack cast, boolean consume, int delay)
    {
        castingMetal = metal;
        this.cast = cast;
        output = replacement;
        consumeCast = consume;
        coolTime = delay;
    }

    public ItemStack getResult ()
    {
        return output.copy();
    }

    public boolean matches (FluidStack metal, ItemStack inputCast)
    {
        if (castingMetal.isFluidEqual(metal)
                && ((cast != null && cast.getItemDamage() == Short.MAX_VALUE && inputCast.getItem() == cast.getItem()) || ItemStack.areItemStacksEqual(this.cast, inputCast)))
        {
            return true;
        }
        else
            return false;
    }
}