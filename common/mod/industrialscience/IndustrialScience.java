package mod.industrialscience;

import java.util.ArrayList;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import mod.industrialscience.*;
import mod.industrialscience.modules.*;

@NetworkMod(clientSideRequired = true, serverSideRequired = false)
@Mod(modid = "industrialscience", name = "Industrial Science", version = "1.0")

public class IndustrialScience {
	private ArrayList<ISAbstractModule> modules = new ArrayList<ISAbstractModule>();
	
	@Init
	public void load(FMLInitializationEvent event) 
	{
	 loadmodules();
	}

	private void loadmodules() {
		for (ISAbstractModule a : modules) {
			a.load();
		}
		
	}

}
