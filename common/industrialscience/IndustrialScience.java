package industrialscience;

import industrialscience.modules.ISAbstractModule;

import java.io.File;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Hashtable;

import net.minecraftforge.common.Configuration;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
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
@NetworkMod(clientSideRequired = true, serverSideRequired = false, channels = {
        Modinfo.channelbase + "0", Modinfo.channelbase + "1",
        Modinfo.channelbase + "2" }, packetHandler = Packethandler.class)
@Mod(modid = Modinfo.ModID, name = Modinfo.ReadableModName, version = "1.0")
public class IndustrialScience {
    /**
     * This field holds the proxy for registering the renders.
     */
    @SidedProxy(clientSide = "industrialscience.ClientProxy", serverSide = "industrialscience.CommonProxy")
    public static CommonProxy proxy;

    /**
     * The instance of this mod for forge.
     */
    @Instance(Modinfo.ModID)
    public static IndustrialScience instance;
    /**
     * This array holds the modules of this mod, which should be loaded.
     */
    public final static ISAbstractModule[] modules = new ISAbstractModule[3];

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
        for (ISAbstractModule a : modules) {
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
                                neededItemIDs.get(itemname)).getInt());
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
        NetworkRegistry.instance().registerGuiHandler(instance,
                new ISGUIHandler(Arrays.asList(modules)));
        initmodules();
        loadmodules();
        proxy.registerRenderers();
    }

    /**
     * The postinit method of this mod. Calls postinitmodules method.
     * 
     * @param event
     *            The FMLInitializationEvent given by forge.
     */
    @EventHandler
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
     * Adds every module to the list.
     */
    private void registermodules() {
        modules[0] = new industrialscience.modules.ResearchModule(756, 0);
        modules[1] = new industrialscience.modules.FishingModule(757, 1);
        modules[2] = new industrialscience.modules.MiningModule(758, 2);

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
     * Calls the load method from every module.
     */
    private void loadmodules() {
        for (ISAbstractModule a : modules) {
            a.load();
        }
    }

}
