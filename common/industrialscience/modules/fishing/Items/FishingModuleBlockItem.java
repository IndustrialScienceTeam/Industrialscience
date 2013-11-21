package industrialscience.modules.fishing.Items;

import industrialscience.modules.fishing.Blocks.FishingModuleBlockType;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class FishingModuleBlockItem extends ItemBlock {

    public FishingModuleBlockItem(int par1) {
        super(par1);
        setHasSubtypes(true);
    }

    @Override
    public String getUnlocalizedName(ItemStack itemstack) {
        return getUnlocalizedName()
                + FishingModuleBlockType.values()[itemstack.getItemDamage()].name()
                + ".name";
    }

    @Override
    public int getMetadata(int par1) {
        return par1;
    }

}
