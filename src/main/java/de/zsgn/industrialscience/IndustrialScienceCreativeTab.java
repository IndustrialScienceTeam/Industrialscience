package de.zsgn.industrialscience;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class IndustrialScienceCreativeTab extends CreativeTabs {

    public IndustrialScienceCreativeTab() {
        super("IndustrialScience");
        // TODO Auto-generated constructor stub
    }

    @Override
    public Item getTabIconItem() {
        return IndustrialScience.getInstance().getItemancienttechnology();
    }

}
