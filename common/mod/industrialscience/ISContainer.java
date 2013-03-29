package mod.industrialscience;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;

public abstract class ISContainer extends Container {
protected ISContainer(InventoryPlayer ip){
	bindPlayerInventory(ip);
}

private void bindPlayerInventory(InventoryPlayer ip) {
	for(int i = 0; i < 3; i++){
        for(int j = 0; j < 9; j++){
                addSlotToContainer(new Slot(ip, j + i * 9 + 9, 9 + j * 18, 85 + i * 16));
               
        }
}

for(int i = 0; i < 9; i++){
        addSlotToContainer(new Slot(ip, i, 6 + i * 16, 84));
}
	
}
}
