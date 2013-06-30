package mod.industrialscience.modules.fishing;

import mod.industrialscience.modules.ISAbstractModule;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class LobsterItem extends Item {

    public LobsterItem(int id) {
        super(id);

        setMaxStackSize(64);
        setUnlocalizedName("Lobster Item");
        LanguageRegistry.addName(this, "Lobster Item");
        setCreativeTab(ISAbstractModule.getCreativeTab());
    }

    @Override
    public void registerIcons(IconRegister iconRegister) {

        itemIcon = iconRegister.registerIcon("industrialscience:lobsteritem");
    }
}