package industrialscience.modules;

import industrialscience.IndustrialScience;
import industrialscience.modules.mining.MiningPackethandler;
import industrialscience.modules.mining.frontend.items.ItemAEPickaxe;
import industrialscience.modules.mining.frontend.items.MESize;
import industrialscience.modules.mining.frontend.items.MiningSlagItem;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.logging.Level;

import appeng.api.Util;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.oredict.ShapedOreRecipe;

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
        logger.log(Level.INFO, "POST-INIT");
    	if(IndustrialScience.isAeinstalled()){
    	AEPickaxeWood.setCreativeTab(CreativeTab).setTextureName("wood_pickaxe");
        AEPickaxeStone.setCreativeTab(CreativeTab).setTextureName("stone_pickaxe");
        AEPickaxeIron.setCreativeTab(CreativeTab).setTextureName("iron_pickaxe");
        AEPickaxeDiamond.setCreativeTab(CreativeTab).setTextureName("diamond_pickaxe");
        if(IndustrialScience.isIc2installed()){
            AEPickaxeICBronze=new ItemAEPickaxe(getItemIDs().get("AEPickaxeICBronze"), EnumToolMaterial.valueOf("IC2_BRONZE"), this.getPrefix());
            AEPickaxeICBronze.setCreativeTab(CreativeTab);
        }

        MESize.init();
        addAEPickaxeRecipes();
        }

    }
    private void addAEPickaxeRecipes(){
    	for (MESize size : MESize.getSizes()) {
			GameRegistry.addRecipe(getAEPickaxeRecipe(size, new ItemStack(Item.pickaxeWood), new ItemStack(AEPickaxeWood)));
			GameRegistry.addRecipe(getAEPickaxeRecipe(size, new ItemStack(Item.pickaxeStone), new ItemStack(AEPickaxeStone)));
			GameRegistry.addRecipe(getAEPickaxeRecipe(size, new ItemStack(Item.pickaxeIron), new ItemStack(AEPickaxeIron)));
			GameRegistry.addRecipe(getAEPickaxeRecipe(size, new ItemStack(Item.pickaxeDiamond), new ItemStack(AEPickaxeDiamond)));
			if(IndustrialScience.isIc2installed()){
				GameRegistry.addRecipe(getAEPickaxeRecipe(size, new ItemStack(Item.appleGold), new ItemStack(AEPickaxeICBronze)));
			}
    	}
    }
    private ShapedOreRecipe getAEPickaxeRecipe(MESize size, ItemStack pick, ItemStack resultpick){
    	ItemStack result=resultpick.copy();
		ItemStack blockbr=null;
		try {
			blockbr=((ItemStack)Class.forName("appeng.api.Blocks").getField("blkTransitionPlane").get(null)).copy();
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Unable to get 'ME Transitionplane'", e);
		} 
		ItemAEPickaxe.setStorageAmount(size.getSize(), result);
		return new ShapedOreRecipe(result,true,  new Object[]{"xyz"," s ", " s ",Character.valueOf('s'),"stickWood", Character.valueOf('x'),size.getStoragepart(),Character.valueOf('y'),pick.copy(),Character.valueOf('z'),blockbr});
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
