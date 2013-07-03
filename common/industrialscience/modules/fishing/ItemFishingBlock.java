package industrialscience.modules.fishing;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemFishingBlock extends ItemBlock {

    public ItemFishingBlock(int par1) {
        super(par1);
        setHasSubtypes(true);
    }
    @Override
    public String getUnlocalizedName(ItemStack itemstack)
    {
          return getUnlocalizedName() + FishingBlockType.values()[itemstack.getItemDamage()].name()+".name";
    }
   
    public int getMetadata(int par1)
    {
          return par1;
    }

}
