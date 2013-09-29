package industrialscience.modules;

import industrialscience.modules.mining.MiningPackethandler;
import industrialscience.modules.mining.frontend.items.ItemMinerPickaxe;
import industrialscience.modules.mining.frontend.items.MiningSlagItem;

import java.util.Hashtable;
import java.util.logging.Level;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class MiningModule extends ISAbstractModule {
    public static Item miningslag;
    public static int miningslagID;
    public static Item ironminerpickaxe;
    public static int ironminerpickaxeID;

    public MiningModule(int blockID, int i) {
        super(NeededItemIDs(), blockID, "mining", "IndustrialScience Mining",
                i, new MiningPackethandler());
    }

    @Override
    public void load() {
        logger.log(Level.INFO, "LOADING");
        miningslag.setCreativeTab(CreativeTab);
        ironminerpickaxe.setCreativeTab(CreativeTab)
                .setTextureName("ironpickaxe").setTextureName("iron_pickaxe");
        ;

    }

    @Override
    public void init() {
        logger.log(Level.INFO, "INIT");
        initCreativeTab(new ItemStack(Item.pickaxeDiamond));
        miningslagID = NeededItemIDs().get("mining_slag");
        miningslag = new MiningSlagItem(miningslagID);
        ironminerpickaxeID = NeededItemIDs().get("iron_minerpickaxe");
        ironminerpickaxe = new ItemMinerPickaxe(ironminerpickaxeID,
                EnumToolMaterial.IRON);

    }

    public static Hashtable<String, Integer> NeededItemIDs() {
        Hashtable<String, Integer> ids = new Hashtable<String, Integer>();
        ids.put("mining_slag", 8003);
        ids.put("iron_minerpickaxe", 8004);
        return ids;
    }

    @Override
    public void postinit() {
        logger.log(Level.INFO, "POST-INIT");

    }

    @Override
    public Object getServerGUIElement(int blockMetadata, EntityPlayer player,
            World world, int x, int y, int z) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object getClientGUIElement(int blockMetadata, EntityPlayer player,
            World world, int x, int y, int z) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void registerRenderers() {
        // TODO Auto-generated method stub

    }

}
