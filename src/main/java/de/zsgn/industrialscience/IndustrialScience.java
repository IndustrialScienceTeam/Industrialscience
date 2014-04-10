package de.zsgn.industrialscience;

import org.apache.logging.log4j.Level;

import net.minecraft.init.Blocks;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;

@Mod(modid = IndustrialScience.MODID, version = IndustrialScience.VERSION)
public class IndustrialScience
{
    public static final String MODID = "industrialscience";
    public static final String VERSION = "@VERSION@";
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
		FMLLog.log(Level.INFO, "This is IndustrialScience version: "+IndustrialScience.VERSION);
        System.out.println("DIRT BLOCK >> "+Blocks.dirt.getUnlocalizedName());
    }
}
