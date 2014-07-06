package de.zsgn.industrialscience.factory.item;

import de.zsgn.industrialscience.IndustrialScience;
import net.minecraft.item.Item;

public class ItemTong extends Item {

    public ItemTong() {
        setUnlocalizedName("tong");
        setCreativeTab(IndustrialScience.getInstance().getCreativetab());
    }

}
