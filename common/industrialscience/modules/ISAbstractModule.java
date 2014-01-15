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
    private int normalBlockID;
    private int modelBlockID;
    private String prefix;
    private String name;
    private ItemStack IconItemStack;
    private int bitprefix;
    private IPacketHandler packethandler;

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

    public abstract void load();

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

    public void setNormalBlockID(int normalBlockID) {
		this.normalBlockID = normalBlockID;
	}

	public void setModelBlockID(int modelBlockID) {
		this.modelBlockID = modelBlockID;
	}

	public int getNormalBlockID() {
		return normalBlockID;
	}

	public int getModelBlockID() {
		return modelBlockID;
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


    @SideOnly(Side.CLIENT)
    public abstract void registerRenderers();
}
