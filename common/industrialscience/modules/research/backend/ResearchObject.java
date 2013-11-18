package industrialscience.modules.research.backend;

import net.minecraft.item.ItemStack;

public class ResearchObject {
    private ItemStack neededStack;

    public ResearchObject(ItemStack neededStack) {
        this.neededStack = neededStack;
    }

    public ItemStack getNeededStack() {
        return neededStack;
    }

    public boolean isOkay(ResearchObject ro) {
        if (ItemStack.areItemStacksEqual(ro.getNeededStack(), getNeededStack()))
            return true;
        return false;
    }
}
