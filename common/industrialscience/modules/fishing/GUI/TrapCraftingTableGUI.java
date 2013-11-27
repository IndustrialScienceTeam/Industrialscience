package industrialscience.modules.fishing.GUI;

import industrialscience.Modinfo;
import industrialscience.GUI.ISGUIContainer;
import industrialscience.modules.fishing.GUI.containers.TrapCraftingTableContainer;
import industrialscience.modules.fishing.TileEntities.TrapCraftingTableTileEntity;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class TrapCraftingTableGUI extends ISGUIContainer {
	public TrapCraftingTableGUI(TrapCraftingTableTileEntity traptileentity, InventoryPlayer ip) {
		super(new TrapCraftingTableContainer(traptileentity, ip), new ResourceLocation(Modinfo.MODID, "/textures/gui/GUITemplate.png"),"Fishtrap Craftingtable");
	}

	@Override
	protected void _drawGuiContainerBackgroundLayer(float f, int i, int j) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void _drawGuiContainerForegroundLayer(int par1, int par2) {
		// TODO Auto-generated method stub
		
	}

}
