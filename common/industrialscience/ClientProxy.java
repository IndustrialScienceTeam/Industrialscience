package industrialscience;

import industrialscience.modules.ISAbstractModule;

public class ClientProxy extends CommonProxy {
    @Override
    public void registerRenderers() {
        for (int i = 0; i < IndustrialScience.modules.length; i++) {
            ISAbstractModule element = IndustrialScience.modules[i];
            element.registerRenderers();
        }
    }
}
