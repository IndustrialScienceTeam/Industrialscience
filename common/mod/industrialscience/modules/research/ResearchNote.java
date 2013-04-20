package mod.industrialscience.modules.research;

import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;

public class ResearchNote extends Item {

	public ResearchNote(int id) {
		super(id);
		setMaxStackSize(1);
		setUnlocalizedName("Research Note");
		LanguageRegistry.addName(this, "Research Note");
	}
	public void updateIcons(IconRegister iconRegister)
	{
		this.iconIndex = iconRegister.registerIcon("paper");
	}

}
