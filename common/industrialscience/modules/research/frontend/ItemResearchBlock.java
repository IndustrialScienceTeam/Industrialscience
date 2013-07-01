package industrialscience.modules.research.frontend;

import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemResearchBlock extends ItemBlock {

    public ItemResearchBlock(int par1) {
        super(par1);
        setHasSubtypes(true);
    }
    @Override
    public String getUnlocalizedName(ItemStack itemstack)
    {
          return getUnlocalizedName() + ResearchBlockTyp.values()[itemstack.getItemDamage()].name()+".name";
    }
   
    public int getMetadata(int par1)
    {
          return par1;
    }
}

