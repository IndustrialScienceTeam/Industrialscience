package industrialscience.modules.fishing;

import java.util.Random;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class Fishtraptile extends FishingTileEntity implements IInventory {
    private static final int FISHSLOT = 1;

    public Fishtraptile() {
        super(new ItemStack[2], "Basic Fishtrap");
    }

    @Override
    public void doUpdateTick(World world, int x, int y, int z, Random random) {
        addFish(1, 4, 10, 5, FISHSLOT);

    }

}
