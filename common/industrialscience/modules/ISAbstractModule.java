package industrialscience.modules;

import java.util.Hashtable;
import java.util.logging.Logger;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.LanguageRegistry;

public abstract class ISAbstractModule {
    static protected CreativeTabs CreativeTab = null;

    public ISAbstractModule() {
        logger = Logger.getLogger(getName());
        logger.setParent(cpw.mods.fml.common.FMLLog.getLogger());

    }

    protected Logger logger;
    protected Hashtable<String, Integer> BlockIDs;
    protected Hashtable<String, Integer> ItemIDs;
    static private String prefix="";

    public abstract void load();

    public void setIDs(Hashtable<String, Integer> BlockIDs,
            Hashtable<String, Integer> ItemIDs) {
        this.BlockIDs = BlockIDs;
        this.ItemIDs = ItemIDs;
    }

    public abstract void init();

    public abstract Hashtable<String, Integer> getNeededBlockIDs();

    public abstract Hashtable<String, Integer> getNeededItemIDs();

    public abstract void postinit();

    public abstract String getName();

    public abstract ItemStack getIconitemstack();

    public void initCreativeTab() {
        CreativeTab = new CreativeTabs(getName()) {
            @Override
            public ItemStack getIconItemStack() {
                return getIconitemstack();
            }
        };
        LanguageRegistry.instance().addStringLocalization(
                "itemGroup." + getName(), "en_US", getName());

    }

    public static CreativeTabs getCreativeTab() {
        return CreativeTab;
    }
    public static String getPrefix(){
        return prefix;
    }
    protected static void setPrefix(String prefixarg){
        prefix="INDUSTRIALSCIENCE."+prefixarg+".";
    }
}
