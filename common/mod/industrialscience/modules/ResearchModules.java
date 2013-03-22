package mod.industrialscience.modules;

import net.minecraft.block.Block;
import mod.industrialscience.modules.research.*;

public class ResearchModules extends ISAbstractModule {
	public Block researchdesk;
	public void load() {
		

	}
	@Override
	public void init() {
		researchdesk=new ResearchDesk(754);
		
	}

}
