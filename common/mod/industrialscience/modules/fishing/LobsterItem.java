package mod.industrialscience.modules.fishing;


import cpw.mods.fml.common.registry.LanguageRegistry;
import mod.industrialscience.modules.FishingModule;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;

public class LobsterItem extends Item {

	
	public LobsterItem(int id) {
                super(id);
               
                setMaxStackSize(64);
                setUnlocalizedName("Lobster Item");
                LanguageRegistry.addName(this, "Lobster Item");
                setCreativeTab(FishingModule.getCreativeTab());
        }
    @Override
	public void registerIcons(IconRegister iconRegister)
	{
	     
		this.itemIcon = iconRegister.registerIcon("industrialscience:lobsteritem");
	}
}