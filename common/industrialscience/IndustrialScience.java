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

@NetworkMod(clientSideRequired = true, serverSideRequired = false, channels = { "isresearchtable" }, packetHandler = Packethandler.class)
@Mod(modid = "mod_industrialscience", name = "Industrial Science", version = "1.0")
public class IndustrialScience {
    @SidedProxy(clientSide = "mod.industrialscience.ClientProxy", serverSide = "mod.industrialscience.CommonProxy")
    public static ClientProxy proxy;
    @Instance("Industrial Science")
    public static IndustrialScience instance;
    private ArrayList<ISAbstractModule> modules = new ArrayList<ISAbstractModule>();

    @PreInit
    public void preLoad(FMLPreInitializationEvent event) {
        registermodules();
        giveIDs(event.getSuggestedConfigurationFile());
    }

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

    @Init
    public void load(FMLInitializationEvent event) {
        NetworkRegistry.instance().registerGuiHandler(this, proxy);
        instance = this;
        initmodules();
        loadmodules();
    }

    @cpw.mods.fml.common.Mod.PostInit
    public void PostInit(FMLPostInitializationEvent event) {
        postinitmodules();
    }

    private void postinitmodules() {
        for (ISAbstractModule a : modules) {
            a.postinit();
        }

    }

    private void registermodules() {
        modules.add(new industrialscience.modules.ResearchModule());
        modules.add(new industrialscience.modules.FishingModule());

    }

    private void initmodules() {
        for (ISAbstractModule a : modules) {
            a.init();
        }

    }

    private void loadmodules() {
        for (ISAbstractModule a : modules) {
            a.load();
        }

    }

}
