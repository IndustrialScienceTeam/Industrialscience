package de.zsgn.industrialscience.factory.tileentity;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import de.zsgn.industrialscience.RelativeCoordinate;

public interface IHatchSupport extends IInventory {
    public RelativeCoordinate[] getRelativeItemHatchCoords();

    public RelativeCoordinate[] getRelativeInterfaceHatchCoords();

    public int[] getSlots();

    public boolean canExtractItem(int var1, ItemStack var2);
}
