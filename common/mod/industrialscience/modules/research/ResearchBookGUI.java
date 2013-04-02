package mod.industrialscience.modules.research;

import net.minecraft.client.gui.GuiScreenBook;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class ResearchBookGUI extends GuiScreenBook {
	int[] researchids;
	public ResearchBookGUI(EntityPlayer player, ItemStack istack, int[] researchids) {
		super(player, istack, true);
		this.researchids=researchids;
	}
	


}
