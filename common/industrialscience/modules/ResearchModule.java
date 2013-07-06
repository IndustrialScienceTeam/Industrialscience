package industrialscience.modules;

import industrialscience.modules.research.frontend.ItemResearchBlock;
import industrialscience.modules.research.frontend.ResearchBlock;
import industrialscience.modules.research.frontend.ResearchBlockType;
import industrialscience.modules.research.frontend.ResearchBook;
import industrialscience.modules.research.frontend.ResearchNote;

import java.util.Hashtable;
import java.util.logging.Level;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class ResearchModule extends ISAbstractModule {
    public ResearchModule(int blockID) {
        super(NeededItemIDs(), blockID, "research",
                "IndustrialScience Research", GUIHandler());
    }

    public static Block researchBlock;

    public static Item researchbook;
    public static int researchbookID;

    public static Item researchNote;
    public static int researchNoteID;

    @Override
    public void load() {
        logger.log(Level.INFO, "LOADING");

        GameRegistry.registerBlock(researchBlock, ItemResearchBlock.class,
                getPrefix() + researchBlock.getUnlocalizedName2());
        for (ResearchBlockType typ : ResearchBlockType.values()) {
            GameRegistry.registerTileEntityWithAlternatives(
                    typ.getTileentity(), getPrefix() + typ.name(), typ.name());
            LanguageRegistry.addName(
                    new ItemStack(researchBlock, 1, typ.ordinal()),
                    typ.getReadableName());
        }
        researchBlock.setCreativeTab(CreativeTab);
        researchbook.setCreativeTab(CreativeTab);
        researchNote.setCreativeTab(CreativeTab);
        // Research Desk
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(researchBlock,
                1, 1), new Object[] { "WWW", "S S", "S S",
                Character.valueOf('W'), "slabWood", Character.valueOf('S'),
                "stickWood" }));

        // Researchbook
        GameRegistry.addRecipe(new ShapelessOreRecipe(researchbook,
                new Object[] { Item.book, "dyeLime", "dyeLime" }));
    }

    @Override
    public void init() {
        logger.log(Level.INFO, "INIT");
        researchbookID = getItemIDs().get("researchbook");
        researchbook = new ResearchBook(researchbookID);
        researchNoteID = getItemIDs().get("researchnote");
        researchNote = new ResearchNote(researchNoteID);
        researchBlock = new ResearchBlock(getBlockID());
        initCreativeTab(new ItemStack(researchBlock, 1,
                ResearchBlockType.RESEARCHDESK.ordinal()));
    }

    @Override
    public void postinit() {
        logger.log(Level.INFO, "POST-INIT");

    }

    public static IGuiHandler GUIHandler() {
        return new industrialscience.modules.research.frontend.GUI.GUIHandler();
    }

    public static Hashtable<String, Integer> NeededItemIDs() {
        Hashtable<String, Integer> neededItemIDs = new Hashtable<String, Integer>();
        neededItemIDs.put("researchbook", 8123);
        neededItemIDs.put("researchnote", 8124);
        return neededItemIDs;
    }

}
