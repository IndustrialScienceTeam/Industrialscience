package industrialscience.modules.research;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;

public class ResearchBookContainer extends Container {
    public ResearchBookContainer(InventoryPlayer player_inventory,
            int[] researchids) {
        bindPlayerInventory(player_inventory);
    }

    private void bindPlayerInventory(InventoryPlayer ip) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                addSlotToContainer(new Slot(ip, j + i * 9 + 9, 8 + j * 18,
                        84 + i * 18));

            }
        }

        for (int i = 0; i < 9; i++) {
            addSlotToContainer(new Slot(ip, i, 8 + i * 18, 142));
        }

    }

    @Override
    public boolean canInteractWith(EntityPlayer entityplayer) {
        return true;
    }
}
