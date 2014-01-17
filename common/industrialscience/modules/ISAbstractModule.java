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
    protected static Logger logger;
    public static Logger getLogger() {
        return logger;
    }
    private int bitprefix;
    protected CreativeTabs CreativeTab = null;
    private ItemStack IconItemStack;
    private Hashtable<String, Integer> ItemIDs;
    private int modelBlockID;
    private String name;
    private int normalBlockID;
    private IPacketHandler packethandler;

    private String prefix;

    protected ISAbstractModule(Hashtable<String, Integer> itemIDs, int normalBlockID, int modelBlockID,
            String prefix, String name, int bitprefix,
            IPacketHandler packethandler) {
        super();
        ItemIDs = itemIDs;
        this.normalBlockID=normalBlockID;
        this.modelBlockID=modelBlockID;
        this.prefix = "industrialscience." + prefix;
        this.name = name;
        logger = Logger.getLogger(this.prefix);
        logger.setParent(FMLLog.getLogger());
        this.bitprefix = bitprefix;
        this.packethandler = packethandler;
        
    }

    public int formGUIID(int GUIID) {
        return (GUIID << 3) | bitprefix;
    }

    public int getBitprefix() {
        return bitprefix;
    }

    public abstract Object getClientGUIElement(int blockMetadata,
            EntityPlayer player, World world, int x, int y, int z);

    public CreativeTabs getCreativeTab() {
        return CreativeTab;
    }

	public Hashtable<String, Integer> getItemIDs() {
        return ItemIDs;
    }

	public int getModelBlockID() {
		return modelBlockID;
	}

	public String getName() {
        return name;
    }

	public int getNormalBlockID() {
		return normalBlockID;
	}

    public String getPrefix() {
        return prefix;
    }

    public abstract Object getServerGUIElement(int blockMetadata,
            EntityPlayer player, World world, int x, int y, int z);

    public abstract void init();

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

    public abstract void load();

    public void onPacketData(INetworkManager manager,
            Packet250CustomPayload packet, Player player) {
        packethandler.onPacketData(manager, packet, player);

    }

    public abstract void postinit();

    @SideOnly(Side.CLIENT)
    public abstract void registerRenderers();

    public void setItemIDs(Hashtable<String, Integer> itemIDs) {
        ItemIDs = itemIDs;
    }

    public void setModelBlockID(int modelBlockID) {
		this.modelBlockID = modelBlockID;
	}


    public void setNormalBlockID(int normalBlockID) {
		this.normalBlockID = normalBlockID;
	}
}
