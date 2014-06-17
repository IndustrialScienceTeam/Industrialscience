package de.zsgn.industrialscience.factory.tileentity;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import de.zsgn.industrialscience.AbsoluteCoordinate;
import de.zsgn.industrialscience.RelativeCoordinate;

public interface IHatchSupport extends IInventory{
public RelativeCoordinate[] getRelativeOutputHatchCoords();
public RelativeCoordinate[] getRelativeInputHatchCoords();
public RelativeCoordinate[] getRelativeInterfaceHatchCoords();
public int[] getOutputSlots();
public int[] getInputSlots();
public boolean canExtractItem(int var1, ItemStack var2);
}
