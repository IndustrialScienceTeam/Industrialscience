package mod.industrialscience;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
public abstract class ISContainer extends Container {
protected IInventory tile_entity;
protected ISContainer(InventoryPlayer ip, IInventory tile_entity){
	this.tile_entity=tile_entity;
	bindPlayerInventory(ip);
}
public boolean canInteractWith(EntityPlayer entityplayer) {
	return tile_entity.isUseableByPlayer(entityplayer);
}
private void bindPlayerInventory(InventoryPlayer ip) {
	for(int i = 0; i < 3; i++){
        for(int j = 0; j < 9; j++){
                addSlotToContainer(new Slot(ip, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
               
        }
}

for(int i = 0; i < 9; i++){
        addSlotToContainer(new Slot(ip, i, 8 + (i * 18), 142));
}
	
}
}
