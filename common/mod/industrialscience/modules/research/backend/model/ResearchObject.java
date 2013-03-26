package mod.industrialscience.modules.research.backend.model;

import net.minecraft.item.ItemStack;

public class ResearchObject {
	private ItemStack neededStack;
	public ResearchObject(ItemStack neededStack) {
		this.neededStack=neededStack;
	}
	public ItemStack getNeededStack() {
		return neededStack;
	}
	public boolean isOkay(ResearchObject ro){
	if(ro.getNeededStack().getItem().itemID==getNeededStack().itemID){
		return true;
	}
	return false;
	}
}
