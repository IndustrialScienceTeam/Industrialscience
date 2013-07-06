package industrialscience.modules;

import java.util.Hashtable;
import java.util.logging.Logger;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.registry.LanguageRegistry;

public abstract class ISAbstractModule {
    protected CreativeTabs CreativeTab = null;
    protected Logger logger;
    private Hashtable<String, Integer> ItemIDs;
    private int BlockID;
    private String prefix;
    private String name;
    private ItemStack IconItemStack;
    protected IGuiHandler guihandler;

    protected ISAbstractModule(Hashtable<String, Integer> itemIDs, int blockID,
            String prefix, String name, IGuiHandler guihandler) {
        super();
        ItemIDs = itemIDs;
        BlockID = blockID;
        this.prefix = "industrialscience." + prefix;
        this.name = name;
        this.guihandler = guihandler;
        logger = Logger.getLogger(this.prefix);
        logger.setParent(FMLLog.getLogger());
    }

    public abstract void load();

    public void setIDs(int suggestedBlockID, Hashtable<String, Integer> ItemIDs) {
        BlockID = suggestedBlockID;
        this.ItemIDs = ItemIDs;
    }

    public abstract void init();

    public abstract void postinit();

    public void initCreativeTab(ItemStack iconitemstack) {
        IconItemStack = iconitemstack;
        CreativeTab = new CreativeTabs(prefix) {
            @Override
            public ItemStack getIconItemStack() {
                return IconItemStack;
            }
        };
        LanguageRegistry.instance().addStringLocalization(
                "itemGroup." + prefix, "en_US", name);

    }

    public Hashtable<String, Integer> getItemIDs() {
        return ItemIDs;
    }

    public void setItemIDs(Hashtable<String, Integer> itemIDs) {
        ItemIDs = itemIDs;
    }

    public CreativeTabs getCreativeTab() {
        return CreativeTab;
    }

    public Logger getLogger() {
        return logger;
    }

    public void setBlockID(int blockID) {
        BlockID = blockID;
    }

    public int getBlockID() {
        return BlockID;
    }

    public String getPrefix() {
        return prefix;
    }

    public String getName() {
        return name;
    }

    public IGuiHandler getGuihandler() {
        return guihandler;
    }
}
