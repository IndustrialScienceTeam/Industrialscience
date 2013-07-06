package industrialscience.modules;

import industrialscience.modules.fishing.FishingBlock;
import industrialscience.modules.fishing.FishingBlockType;
import industrialscience.modules.fishing.ItemFishingBlock;
import industrialscience.modules.fishing.LobsterItem;

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
    public static int fishingblockID;

    public static Item lobsteritem;
    public static int lobsteritemID;

    @Override
    public void load() {
        logger.log(Level.INFO, "LOADING");

        // Basicfishtrap
        GameRegistry.registerBlock(fishingblock, ItemFishingBlock.class,
                getPrefix() + fishingblock.getUnlocalizedName2());
        for (FishingBlockType typ : FishingBlockType.values()) {
            GameRegistry.registerTileEntityWithAlternatives(
                    typ.getTileentity(), getPrefix() + typ.name(), typ.name());
            LanguageRegistry.addName(
                    new ItemStack(fishingblock, 1, typ.ordinal()),
                    typ.getReadableName());
        }
        // GameRegistry
        // .addRecipe(new ItemStack(basicfishtrap), new Object[] {
        // "X X X", " X X ", "X X X", Character.valueOf('X'),
        // Item.stick });
        // GameRegistry
        // .addRecipe(new ItemStack(basicfishtrap), new Object[] {
        // "X X X", " X X ", "X X X", Character.valueOf('X'),
        // Item.paper });

        // Lobster item
        GameRegistry
                .registerItem(lobsteritem, lobsteritem.getUnlocalizedName());

    }

    @Override
    public void init() {
        initCreativeTab();
        logger.log(Level.INFO, "INIT");
        fishingblockID = BlockIDs.get("fishingblock");
        fishingblock = new FishingBlock(fishingblockID);
        // Lobster item
        lobsteritemID = ItemIDs.get("lobsteritem");
        lobsteritem = new LobsterItem(lobsteritemID);

    }

    @Override
    public Hashtable<String, Integer> getNeededBlockIDs() {
        Hashtable<String, Integer> NeededBlockIDs = new Hashtable<String, Integer>();
        NeededBlockIDs.put("fishingblock", 758);
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
        return "IndustrialScience fishing module";
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
