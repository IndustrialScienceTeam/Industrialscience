package industrialscience.modules;

import industrialscience.IndustrialScience;
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
import cpw.mods.fml.common.registry.LanguageRegistry;

public class MiningModule extends ISAbstractModule {
    public static Item miningslag;
    public static int miningslagID;
    
    public static Item MinerPickaxeWood;
    public static Item MinerPickaxeStone;
    public static Item MinerPickaxeIron;
    public static Item MinerPickaxeDiamond;
    public static Item MinerPickaxeICBronze;

    public MiningModule(int blockID, int i) {
        super(NeededItemIDs(), blockID, "mining", "IndustrialScience Mining",
                i, new MiningPackethandler());
    }

    @Override
    public void load() {
        logger.log(Level.INFO, "LOADING");
        
        miningslag.setCreativeTab(CreativeTab);
        MinerPickaxeWood.setCreativeTab(CreativeTab).setTextureName("wood_pickaxe");
        LanguageRegistry.addName(MinerPickaxeWood, "Wooden Miner Pickaxe");
        MinerPickaxeStone.setCreativeTab(CreativeTab).setTextureName("stone_pickaxe");
        LanguageRegistry.addName(MinerPickaxeStone, "Stone Miner Pickaxe");
        MinerPickaxeIron.setCreativeTab(CreativeTab).setTextureName("iron_pickaxe");
        LanguageRegistry.addName(MinerPickaxeIron, "Iron Miner Pickaxe");
        MinerPickaxeDiamond.setCreativeTab(CreativeTab).setTextureName("diamond_pickaxe");
        LanguageRegistry.addName(MinerPickaxeDiamond, "Diamond Miner Pickaxe");
        

    }
    @Override
    public void init() {
        logger.log(Level.INFO, "INIT");
        initCreativeTab(new ItemStack(Item.pickaxeDiamond));
        miningslagID =getItemIDs().get("mining_slag");
        miningslag = new MiningSlagItem(miningslagID);
        MinerPickaxeWood=new ItemMinerPickaxe(getItemIDs().get("MinerPickaxeWood"), EnumToolMaterial.WOOD);
        MinerPickaxeStone=new ItemMinerPickaxe(getItemIDs().get("MinerPickaxeStone"), EnumToolMaterial.STONE);
        MinerPickaxeIron=new ItemMinerPickaxe(getItemIDs().get("MinerPickaxeIron"), EnumToolMaterial.IRON);
        MinerPickaxeDiamond=new ItemMinerPickaxe(getItemIDs().get("MinerPickaxeDiamond"), EnumToolMaterial.EMERALD);
    }

    public static Hashtable<String, Integer> NeededItemIDs () {
        Hashtable<String, Integer> ids = new Hashtable<String, Integer>();
        ids.put("mining_slag", 8003);
        ids.put("MinerPickaxeWood",8004);
        ids.put("MinerPickaxeStone",8005);
        ids.put("MinerPickaxeIron",8006);
        ids.put("MinerPickaxeDiamond",8007);
        ids.put("MinerPickaxeICBronze",8008);
        return ids;
    }

    @Override
    public void postinit() {
        if(IndustrialScience.isIc2installed()){
            MinerPickaxeICBronze=new ItemMinerPickaxe(getItemIDs().get("MinerPickaxeICBronze"), EnumToolMaterial.valueOf("IC2_BRONZE"));
        }
        logger.log(Level.INFO, "POST-INIT");
        if(IndustrialScience.isIc2installed()){
            MinerPickaxeICBronze.setCreativeTab(CreativeTab);
            LanguageRegistry.addName(MinerPickaxeICBronze, "Bronze Miner Pickaxe");
        }

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
