package industrialscience.modules;

import java.util.Hashtable;
import java.util.logging.Logger;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.world.World;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public abstract class ISAbstractModule {
    protected CreativeTabs CreativeTab = null;
    protected Logger logger;
    private Hashtable<String, Integer> ItemIDs;
    private int BlockID;
    private String prefix;
    private String name;
    private ItemStack IconItemStack;
    private int bitprefix;
    private IPacketHandler packethandler;
    private boolean ic2installed=true;

    protected ISAbstractModule(Hashtable<String, Integer> itemIDs, int blockID,
            String prefix, String name, int bitprefix,
            IPacketHandler packethandler) {
        super();
        ItemIDs = itemIDs;
        BlockID = blockID;
        this.prefix = "industrialscience." + prefix;
        this.name = name;
        logger = Logger.getLogger(this.prefix);
        logger.setParent(FMLLog.getLogger());
        this.bitprefix = bitprefix;
        this.packethandler = packethandler;
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

    public int getBitprefix() {
        return bitprefix;
    }

    public int formGUIID(int GUIID) {
        return (GUIID << 3) | bitprefix;
    }

    public abstract Object getServerGUIElement(int blockMetadata,
            EntityPlayer player, World world, int x, int y, int z);

    public abstract Object getClientGUIElement(int blockMetadata,
            EntityPlayer player, World world, int x, int y, int z);

    public void onPacketData(INetworkManager manager,
            Packet250CustomPayload packet, Player player) {
        packethandler.onPacketData(manager, packet, player);

    }
    public boolean isIC2installed(){
        if(ic2installed)
            ic2installed=!(ic2.api.item.Items.getItem("copperOre")==null);
        return ic2installed;
    }

    @SideOnly(Side.CLIENT)
    public abstract void registerRenderers();
}
