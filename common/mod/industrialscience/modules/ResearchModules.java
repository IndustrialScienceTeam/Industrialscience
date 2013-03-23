package mod.industrialscience.modules;

import java.util.Hashtable;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.block.Block;
import mod.industrialscience.modules.research.*;

public class ResearchModules extends ISAbstractModule {
	public Block researchdesk;
	public void load() {
		GameRegistry.registerBlock(researchdesk);
		LanguageRegistry.addName(researchdesk, "Research Table");

	}
	@Override
	public void init() {
		researchdesk=new ResearchDesk(BlockIDs.get("researchdesk"));
		
	}
	@Override
	public Hashtable<String, Integer> getNeededBlockIDs() {		
		Hashtable<String, Integer> blub = new Hashtable<String, Integer>();
		blub.put("researchdesk", 756);
		return blub;
	}
	public Hashtable<String, Integer> getNeededItemIDs() {
		Hashtable<String, Integer> blub = new Hashtable<String, Integer>();
		return blub;
	}

}
