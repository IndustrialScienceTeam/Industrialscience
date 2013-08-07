package industrialscience;

import industrialscience.modules.ISAbstractModule;
import industrialscience.modules.research.frontend.ResearchBlockType;
import industrialscience.modules.research.frontend.TileEntities.ResearchDeskTile;
import cpw.mods.fml.client.registry.ClientRegistry;

public class ClientProxy extends CommonProxy {
    @Override
    public void registerRenderers() {
    	for (int i = 0; i < IndustrialScience.modules.length; i++) {
			ISAbstractModule element = IndustrialScience.modules[i];
			element.registerRenderers();
		}
    }
}
