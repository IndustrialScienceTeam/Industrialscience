package industrialscience.blocksystem;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ISModuleBlockItem extends ItemBlock{

	public ISModuleBlockItem(int id) {
		super(id);
		setHasSubtypes(true);
	}
	@Override
	public int getMetadata (int damageValue) {
		return damageValue;
	}
	@Override
	public String getUnlocalizedName(ItemStack itemstack) {
		if(Block.blocksList[itemstack.itemID] instanceof ISModuleModelBlock){
			ISModuleModelBlock mBlock=(ISModuleModelBlock)Block.blocksList[itemstack.itemID];
			return mBlock.getIDName(itemstack.getItemDamage());
		}
		return "NO NAME";
	}
	
	
}