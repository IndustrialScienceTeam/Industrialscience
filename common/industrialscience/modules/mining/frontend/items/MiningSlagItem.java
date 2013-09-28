package industrialscience.modules.mining.frontend.items;

import industrialscience.IndustrialScience;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;

public class MiningSlagItem extends Item {

    public MiningSlagItem(int par1) {
        super(par1);
        setUnlocalizedName(IndustrialScience.modules[2].getPrefix()+"Mining_Slag");
        LanguageRegistry.addName(this, "Mining Slag");
    }
    @Override
    public void registerIcons(IconRegister iconRegister) {
        itemIcon = iconRegister.registerIcon("apple");
    }
}
