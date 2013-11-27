package industrialscience;

import industrialscience.GUI.ISGUIHandler;
import industrialscience.modules.ISAbstractModule;
import industrialscience.proxies.CommonProxy;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.io.FilenameUtils;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.Configuration;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

/**
 * The main mod-class It is responsible to initialize the modules and give the
 * needed infomation to them.
 * 
 * @author finesim97
 * 
 */
@NetworkMod(clientSideRequired = true, serverSideRequired = false, channels = {
		Modinfo.CHANNELBASE + "0", Modinfo.CHANNELBASE + "1",
        Modinfo.CHANNELBASE + "2" }, packetHandler = Packethandler.class)
@Mod(modid = Modinfo.MODID, name = Modinfo.READABLEMODNAME, version = "1.0")
public class IndustrialScience {
    /**
     * This field holds the proxy for registering the renders.
     */
    @SidedProxy(clientSide = "industrialscience.proxies.ClientProxy", serverSide = "industrialscience.proxies.CommonProxy")
    public static CommonProxy proxy;

    /**
     * The instance of this mod for forge.
     */
    @Instance(Modinfo.MODID)
    public static IndustrialScience instance;
    /**
     * This array holds the modules of this mod, which should be loaded.
     */
    public final static ISAbstractModule[] MODULES = new ISAbstractModule[3];
    
    private static Logger logger = Logger.getLogger(Modinfo.MODID);
    private static boolean ic2installed=false;
    private static boolean aeinstalled=false;
	private static boolean bcinstalled=false;
	public String langDir="/assets/industrialscience/lang/";

    /**
     * The mod PreInit method. Calls the registermodules and giveIDs(with the
     * suggested config file by forge) method.
     * 
     * @param event
     *            The FMLPreInitializationEvent given by forge.
     */
    @EventHandler
    public void preLoad(FMLPreInitializationEvent event) {
        registermodules();
        giveIDs(event.getSuggestedConfigurationFile());
    }

    /**
     * Generates the defalut config file with the IDs and also reads from the
     * config file and gives the modules their requested IDs.
     * 
     * @param suggestedConfigurationFile
     *            The configfile, in which the mod stores it's block and item
     *            IDs.
     */
    private void giveIDs(File suggestedConfigurationFile) {
        Configuration configuration = new Configuration(
                suggestedConfigurationFile);
        configuration.load();
        for (ISAbstractModule a : MODULES) {
            int suggestedBlockID = a.getBlockID();
            suggestedBlockID = configuration.getBlock(
                    a.getPrefix() + ".blockID", suggestedBlockID).getInt();

            Hashtable<String, Integer> neededItemIDs = a.getItemIDs();
            Hashtable<String, Integer> ItemIDs = new Hashtable<String, Integer>();
            Enumeration<String> itemnames = neededItemIDs.keys();
            while (itemnames.hasMoreElements()) {
                String itemname = itemnames.nextElement();
                ItemIDs.put(
                        itemname,
                        configuration.getItem(itemname,
                                neededItemIDs.get(itemname)).getInt()-256);
            }
            a.setItemIDs(ItemIDs);
            a.setBlockID(suggestedBlockID);
        }
        configuration.save();
    }

    /**
     * The init method of this mod. Calls first initmodules and then
     * loadmodules.
     * 
     * @param event
     *            The FMLInitializationEvent given by forge.
     */
    @EventHandler
    public void load(FMLInitializationEvent event) {
        instance = this;
		loadLanguages();
        NetworkRegistry.instance().registerGuiHandler(instance,
                new ISGUIHandler(Arrays.asList(MODULES)));
        initmodules();
        loadmodules();
        proxy.registerRenderers();
    }

	private void loadLanguages() {
		try {
			File langdirfile = new File(IndustrialScience.class.getResource(langDir).toURI());
			for(File lang : langdirfile.listFiles(new LangfileFilter())){
	        	Properties langprop= new Properties();
	        	try {
					langprop.load(new FileInputStream(lang));
				} catch (FileNotFoundException e) {
					logger.log(Level.WARNING, "Couldn't find language file.", e);
				} catch (IOException e) {
					logger.log(Level.WARNING, "Couldn't load language file.", e);
				}
	        	LanguageRegistry.instance().addStringLocalization(langprop, FilenameUtils.removeExtension(lang.getName()));
	        	}
		} catch (URISyntaxException e) {
			logger.log(Level.WARNING, "Couldn't load lang dir", e);
		}
	}
	class LangfileFilter implements FilenameFilter{

		@Override
		public boolean accept(File dir, String name) {
			if(name.contains(".properties")){
			return true;
			}
			return false;
		}
		
	}
    /**
     * The postinit method of this mod. Calls postinitmodules method.
     * 
     * @param event
     *            The FMLInitializationEvent given by forge.
     */
    @EventHandler
    public void PostInit(FMLPostInitializationEvent event) {
    	ic2installed=Loader.isModLoaded("IC2");
    	aeinstalled=Loader.isModLoaded(""); // Look for the ModId of AE
    	bcinstalled=Loader.isModLoaded("BuildCraft|Core");
        postinitmodules();
    }

    /**
     * Calls the postinit method from every module
     */
    private void postinitmodules() {
        for (ISAbstractModule a : MODULES) {
            a.postinit();
        }

    }

    /**
     * Adds every module to the list.
     */
    private void registermodules() {
        MODULES[0] = new industrialscience.modules.ResearchModule(756, 0);
        MODULES[1] = new industrialscience.modules.FishingModule(757, 1);
        MODULES[2] = new industrialscience.modules.MiningModule(758, 2);

    }

    /**
     * Calls the init method from every module.
     */
    private void initmodules() {
        for (ISAbstractModule a : MODULES) {
            a.init();
        }

    }

    /**
     * Calls the load method from every module.
     */
    private void loadmodules() {
        for (ISAbstractModule a : MODULES) {
            a.load();
        }
    }

	/**
	 * @return Whether IC2 is installed 
	 */
	public static boolean isIc2installed() {
		return ic2installed;
	}

	/**
	 * @return Whether AE is installed 
	 */
	public static boolean isAeinstalled() {
		return aeinstalled;
	}

	/**
	 * @return Whether BC is installed 
	 */
	public static boolean isBcinstalled() {
		return bcinstalled;
	}
    

}
