package industrialscience.modules;

import industrialscience.modules.fishing.FishingBlock;
import industrialscience.modules.fishing.FishingBlockTyp;
import industrialscience.modules.fishing.ItemFishingBlock;
import industrialscience.modules.fishing.LobsterItem;
import industrialscience.modules.fishing.Lobster_trapBlock;
import industrialscience.modules.fishing.Lobster_traptile;

import java.util.Hashtable;
import java.util.logging.Level;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class FishingModule extends ISAbstractModule {
    public static Block fishingblock;
    public static int fishingblockid;

    public static Block lobster_trap;
    public static int lobster_trapid;

    public static Item lobsteritem;
    public static int lobsteritemid;
    
    
    
    @Override
    public void load() {
        logger.log(Level.INFO, "LOADING");

        // Basicfishtrap
        GameRegistry.registerBlock(fishingblock,ItemFishingBlock.class,getPrefix()+fishingblock.getUnlocalizedName2());
        for (FishingBlockTyp typ : FishingBlockTyp.values()) {
            GameRegistry.registerTileEntityWithAlternatives(typ.getTileentity(), getPrefix()+typ.name(), typ.name());
            LanguageRegistry.addName(new ItemStack(fishingblock, 1, typ.ordinal()), typ.getReadableName());
        }
//        GameRegistry
//                .addRecipe(new ItemStack(basicfishtrap), new Object[] {
//                        "X X X", " X X ", "X X X", Character.valueOf('X'),
//                        Item.stick });

        // Lobster trap
        GameRegistry.registerBlock(lobster_trap,
                lobster_trap.getUnlocalizedName());
        GameRegistry.registerTileEntity(Lobster_traptile.class, "Lobster-Trap");
//        GameRegistry
//                .addRecipe(new ItemStack(basicfishtrap), new Object[] {
//                        "X X X", " X X ", "X X X", Character.valueOf('X'),
//                        Item.paper });

        // Lobster item
        GameRegistry
                .registerItem(lobsteritem, lobsteritem.getUnlocalizedName());

    }

    @Override
    public void init() {
        initCreativeTab();
        logger.log(Level.INFO, "INIT");
        fishingblockid = BlockIDs.get("fishingblock");
        fishingblock = new FishingBlock(fishingblockid);

        // Lobster trap
        lobster_trapid = BlockIDs.get("lobster_trap");
        lobster_trap = new Lobster_trapBlock(lobster_trapid);

        // Lobster item
        lobsteritemid = ItemIDs.get("lobsteritem");
        lobsteritem = new LobsterItem(ItemIDs.get("lobsteritem"));

    }

    @Override
    public Hashtable<String, Integer> getNeededBlockIDs() {
        Hashtable<String, Integer> NeededBlockIDs = new Hashtable<String, Integer>();

        NeededBlockIDs.put("fishingblock", 758); // Basicfishtrap
        NeededBlockIDs.put("lobster_trap", 759); // Lobster trap

        return NeededBlockIDs;
    }

    @Override
    public Hashtable<String, Integer> getNeededItemIDs() {
        Hashtable<String, Integer> NeededItemIDs = new Hashtable<String, Integer>();

        NeededItemIDs.put("lobsteritem", 760); // Lobster item

        return NeededItemIDs;
    }

    @Override
    public void postinit() {
        logger.log(Level.INFO, "POST-INIT");

    }

    @Override
    public String getName() {
        return "IS Fishing Module";
    }

    @Override
    public ItemStack getIconitemstack() {
        return new ItemStack(Item.fishingRod);
    }

    @Override
    public IGuiHandler getGUIHandler() {
        return null;
    }

}
