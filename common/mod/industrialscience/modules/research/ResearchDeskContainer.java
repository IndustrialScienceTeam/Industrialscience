package mod.industrialscience.modules.research;

import mod.industrialscience.ISContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
public class ResearchDeskContainer extends ISContainer {
	protected ResearchDeskTile tile_entity;
	public ResearchDeskContainer(ResearchDeskTile tile_entity,
			InventoryPlayer player_inventory) {
		super(player_inventory);
        this.tile_entity = tile_entity;
        addSlotToContainer(new Slot(tile_entity, ResearchDeskTile.BOOKSLOT, 153, 61));
        addSlotToContainer(new Slot(tile_entity, ResearchDeskTile.PAGESLOT, 153, 7));
        addSlotToContainer(new Slot(tile_entity, ResearchDeskTile.ITEMSLOT, 7, 7));
        }
       

	@Override
	public boolean canInteractWith(EntityPlayer entityplayer) {
		return tile_entity.isUseableByPlayer(entityplayer);
	}

}
