package industrialscience.modules.research.frontend;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;

public class ResearchNote extends Item {

    public ResearchNote(int id, String prefix) {
        super(id);
        setMaxStackSize(1);
        setUnlocalizedName(prefix+".Research_note");
    }

    @Override
    public void registerIcons(IconRegister iconRegister) {
        itemIcon = iconRegister.registerIcon("paper");
    }

}
