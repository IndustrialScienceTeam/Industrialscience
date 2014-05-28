package de.zsgn.industrialscience;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class IndustrialScienceCreativeTab extends CreativeTabs {

    public IndustrialScienceCreativeTab() {
        super("IndustrialScience");
    }

    @Override
    public Item getTabIconItem() {
        return IndustrialScience.getInstance().getItemancienttechnology();
    }

}
