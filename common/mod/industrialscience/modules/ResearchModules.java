package mod.industrialscience.modules;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import mod.industrialscience.modules.research.*;

public class ResearchModules extends ISAbstractModule {
	public Block researchdesk;
	public void load() {
		GameRegistry.registerBlock(researchdesk);

	}
	@Override
	public void init() {
		researchdesk=new ResearchDesk(754);
		
	}

}
