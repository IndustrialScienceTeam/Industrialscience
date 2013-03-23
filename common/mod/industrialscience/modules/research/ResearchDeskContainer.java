package mod.industrialscience.modules.research;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
public class ResearchDeskContainer extends Container {
	protected ResearchDeskTile tile_entity;
	public ResearchDeskContainer(ResearchDeskTile tile_entity,
			InventoryPlayer player_inventory) {
        this.tile_entity = tile_entity;
        int o=0;
        for(int q = 0; q <2; q++){
        for(int p = 0; p <3; p++){
       
       
        addSlotToContainer(new Slot(tile_entity, o, 9+p*18, 9+q*18));
       
        o++;
        }
        }
        bindPlayerInventory(player_inventory);
	}
    protected void bindPlayerInventory(InventoryPlayer player_inventory){
        for(int i = 0; i < 3; i++){
                for(int j = 0; j < 9; j++){
                        addSlotToContainer(new Slot(player_inventory, j + i * 9 + 9, 9 + j * 18, 85 + i * 16));
                       
                }
        }

        for(int i = 0; i < 9; i++){
                addSlotToContainer(new Slot(player_inventory, i, 6 + i * 16, 142));
        }
       
}

	@Override
	public boolean canInteractWith(EntityPlayer entityplayer) {
		return tile_entity.isUseableByPlayer(entityplayer);
	}

}
