package industrialscience;

import industrialscience.modules.ISAbstractModule;

import java.io.File;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

import net.minecraftforge.common.Configuration;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;

/**
 * The main mod-class It is responsible to initialize the modules and give the
 * needed infomation to them.
 * 
 * @author finesim97
 * 
 */
@NetworkMod(clientSideRequired = true, serverSideRequired = false, channels = { "isresearchtable" }, packetHandler = Packethandler.class)
@Mod(modid = "industrialscience", name = "Industrial Science", version = "1.0")
public class IndustrialScience {
    /**
     * This field holds the proxy for registering the renders.
     */
    @SidedProxy(clientSide = "industrialscience.ClientProxy", serverSide = "industrialscience.CommonProxy")
    public static ClientProxy proxy;

    /**
     * The instance of this mod for forge.
     */
    @Instance("Industrial Science")
    public static IndustrialScience instance;
    /**
     * This ArrayList holds the modules of this mod, which should be loaded.
     */
    private ArrayList<ISAbstractModule> modules = new ArrayList<ISAbstractModule>();

    /**
     * The mod PreInit method. Calls the registermodules and giveIDs(with the
     * suggested config file by forge) method.
     * 
     * @param event
     *            The FMLPreInitializationEvent given by forge.
     */
    @PreInit
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
        for (ISAbstractModule a : modules) {
            Hashtable<String, Integer> neededBlockids = a.getNeededBlockIDs();
            Hashtable<String, Integer> BlockIDs = new Hashtable<String, Integer>();
            Enumeration<String> blocknames = neededBlockids.keys();
            while (blocknames.hasMoreElements()) {
                String blockname = blocknames.nextElement();
                BlockIDs.put(
                        blockname,
                        configuration.getBlock(blockname,
                                neededBlockids.get(blockname)).getInt());
            }
            Hashtable<String, Integer> neededItemIDs = a.getNeededItemIDs();
            Hashtable<String, Integer> ItemIDs = new Hashtable<String, Integer>();
            Enumeration<String> itemnames = neededItemIDs.keys();
            while (itemnames.hasMoreElements()) {
                String itemname = itemnames.nextElement();
                ItemIDs.put(
                        itemname,
                        configuration.getItem(itemname,
                                neededItemIDs.get(itemname)).getInt());
            }
            a.setIDs(BlockIDs, ItemIDs);
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
    @Init
    public void load(FMLInitializationEvent event) {
        instance = this;
        initmodules();
        loadmodules();
    }

    /**
     * The postinit method of this mod. Calls postinitmodules method.
     * 
     * @param event
     *            The FMLInitializationEvent given by forge.
     */
    @cpw.mods.fml.common.Mod.PostInit
    public void PostInit(FMLPostInitializationEvent event) {
        postinitmodules();
    }

    /**
     * Calls the postinit method from every module
     */
    private void postinitmodules() {
        for (ISAbstractModule a : modules) {
            a.postinit();
        }

    }

    /**
     * Adds every module to the ArrayList.
     */
    private void registermodules() {
        modules.add(new industrialscience.modules.ResearchModule());
        modules.add(new industrialscience.modules.FishingModule());

    }

    /**
     * Calls the init method from every module.
     */
    private void initmodules() {
        for (ISAbstractModule a : modules) {
            a.init();
        }

    }

    /**
     * Calls the load method from every module and registers the GUIHandler from
     * every module at the NetworkRegistry.
     */
    private void loadmodules() {
        for (ISAbstractModule a : modules) {
            a.load();
            if (a.getGUIHandler() != null) {
                NetworkRegistry.instance().registerGuiHandler(instance,
                        a.getGUIHandler());
            }
        }

    }

}
