package industrialscience.modules;

import industrialscience.IndustrialScience;
import industrialscience.modules.mining.MiningPackethandler;
import industrialscience.modules.mining.frontend.items.ItemAEPickaxe;
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
    
    public static Item AEPickaxeWood;
    public static Item AEPickaxeStone;
    public static Item AEPickaxeIron;
    public static Item AEPickaxeDiamond;
    public static Item AEPickaxeICBronze;

    public MiningModule(int blockID, int i) {
        super(NeededItemIDs(), blockID, "mining", "IndustrialScience Mining",
                i, new MiningPackethandler());
    }

    @Override
    public void load() {
        logger.log(Level.INFO, "LOADING");
        
        miningslag.setCreativeTab(CreativeTab);
        AEPickaxeWood.setCreativeTab(CreativeTab).setTextureName("wood_pickaxe");
        AEPickaxeStone.setCreativeTab(CreativeTab).setTextureName("stone_pickaxe");
        AEPickaxeIron.setCreativeTab(CreativeTab).setTextureName("iron_pickaxe");
        AEPickaxeDiamond.setCreativeTab(CreativeTab).setTextureName("diamond_pickaxe");
        

    }
    @Override
    public void init() {
        logger.log(Level.INFO, "INIT");
        initCreativeTab(new ItemStack(Item.pickaxeDiamond));
        miningslagID =getItemIDs().get("mining_slag");
        miningslag = new MiningSlagItem(miningslagID, this.getPrefix());
        AEPickaxeWood=new ItemAEPickaxe(getItemIDs().get("AEPickaxeWood"), EnumToolMaterial.WOOD, this.getPrefix());
        AEPickaxeStone=new ItemAEPickaxe(getItemIDs().get("AEPickaxeStone"), EnumToolMaterial.STONE, this.getPrefix());
        AEPickaxeIron=new ItemAEPickaxe(getItemIDs().get("AEPickaxeIron"), EnumToolMaterial.IRON, this.getPrefix());
        AEPickaxeDiamond=new ItemAEPickaxe(getItemIDs().get("AEPickaxeDiamond"), EnumToolMaterial.EMERALD, this.getPrefix());
    }

    public static Hashtable<String, Integer> NeededItemIDs () {
        Hashtable<String, Integer> ids = new Hashtable<String, Integer>();
        ids.put("mining_slag", 8003);
        ids.put("AEPickaxeWood",8004);
        ids.put("AEPickaxeStone",8005);
        ids.put("AEPickaxeIron",8006);
        ids.put("AEPickaxeDiamond",8007);
        ids.put("AEPickaxeICBronze",8008);
        return ids;
    }

    @Override
    public void postinit() {
        if(IndustrialScience.isIc2installed()){
            AEPickaxeICBronze=new ItemAEPickaxe(getItemIDs().get("AEPickaxeICBronze"), EnumToolMaterial.valueOf("IC2_BRONZE"), this.getPrefix());
        }
        logger.log(Level.INFO, "POST-INIT");
        if(IndustrialScience.isIc2installed()){
            AEPickaxeICBronze.setCreativeTab(CreativeTab);
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
