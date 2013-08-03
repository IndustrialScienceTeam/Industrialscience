package industrialscience.modules.research.frontend.GUI.containers;

import industrialscience.ISContainer;
import industrialscience.modules.ResearchModule;
import industrialscience.modules.research.frontend.TileEntities.CopierTile;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class CopierContainer extends ISContainer {
    public CopierContainer(CopierTile ct, InventoryPlayer ip) {
        super(ip, ct);
        addSlotToContainer(new ResearchCopierSlot(ct, ct.PAGEINPUT, 53, 34, false));
        addSlotToContainer(new ResearchCopierSlot(ct, ct.PAGEOUTPUT, 107, 34, true));
    }
    @Override
    public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2) {
    	return null;
    }
    private class ResearchCopierSlot extends Slot{
    	private boolean isOutput;
		public ResearchCopierSlot(IInventory par1iInventory, int par2,
				int par3, int par4, boolean isOutput) {
			super(par1iInventory, par2, par3, par4);
			this.isOutput=isOutput;
		}
		@Override
		public boolean isItemValid(ItemStack itemstack) {
			if(isOutput&&itemstack.itemID==ResearchModule.researchNoteID)
				return true;
			if(itemstack.itemID==ResearchModule.researchbookID)
				return true;
			return false;
		}
    	
    }

}
