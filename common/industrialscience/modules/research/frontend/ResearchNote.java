package industrialscience.modules.research.frontend;

import industrialscience.IISItem;
import net.minecraft.client.renderer.texture.IconRegister;

public class ResearchNote extends IISItem {

    public ResearchNote() {
        super("researchnote","Research Note",1);
    }

    @Override
    public void registerIcons(IconRegister iconRegister) {
        itemIcon = iconRegister.registerIcon("paper");
    }

}
