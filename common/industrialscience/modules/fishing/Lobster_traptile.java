package industrialscience.modules.fishing;

import industrialscience.modules.FishingModule;

import java.util.Random;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class Lobster_traptile extends FishingTileEntity implements IInventory {
    static final int UPDATESLOT = 1;
    static final int FISHSLOT = 1;
    protected int fishamout = 5;
    protected int neededwater = 1;
    protected int waterforextrafish = 1;
    protected int range = 1;

    public Lobster_traptile() {
        super(new ItemStack[2], "Lobster Trap");
    }

    public void addFish(int fishamout, int neededwater, int range,
            int waterforextrafish) {
        int waterblocks = countWater(range);
        int fishes = fishamout;
        if (waterblocks >= neededwater) {
            waterblocks = waterblocks - neededwater;
            if (waterblocks > 0) {

            }
            ItemStack stack = null;
            if (Inventory[FISHSLOT] != null) {
                stack = Inventory[FISHSLOT];
                stack.stackSize = stack.stackSize + fishes;
            } else {
                stack = new ItemStack(FishingModule.lobsteritem, fishes);
            }
            Inventory[FISHSLOT] = stack;
        }
    }

    @Override
    public void doUpdateTick(World world, int x, int y, int z, Random random) {
        addFish(fishamout, neededwater, range, waterforextrafish);

    }

	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack) {
		// TODO Auto-generated method stub
		return false;
	}

}
