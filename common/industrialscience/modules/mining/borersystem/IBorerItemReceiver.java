package industrialscience.modules.mining.borersystem;

import net.minecraft.item.ItemStack;

public interface IBorerItemReceiver {
	public boolean receiveStack(ItemStack transported);
}
