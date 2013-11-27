package industrialscience.modules.fishing.Items;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class LobsterItem extends Item {

    public LobsterItem(int id, String prefix) {
        super(id);
        setUnlocalizedName(prefix+".item.lobster");
        setMaxStackSize(64);
    }

    @Override
    public void registerIcons(IconRegister iconRegister) {

        itemIcon = iconRegister.registerIcon("industrialscience:lobsteritem");
    }
}