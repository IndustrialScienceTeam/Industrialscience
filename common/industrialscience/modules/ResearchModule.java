package industrialscience.modules;
import industrialscience.modules.research.CopierTile;
import industrialscience.modules.research.ResearchBlock;
import industrialscience.modules.research.ResearchBlockTyp;
import industrialscience.modules.research.ResearchBook;
import industrialscience.modules.research.ResearchDesk;
import industrialscience.modules.research.ResearchDeskTile;
import industrialscience.modules.research.ResearchNote;

import java.util.Hashtable;
import java.util.logging.Level;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlockWithMetadata;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class ResearchModule extends ISAbstractModule {
    public static Block researchBlock;
    public static int researchBlockID;
    
    public static Block researchdesk;
    public static int researchdeskid;

    public static Item researchbook;
    public static int researchbookid;

    public static Item researchNote;
    public static int researchNoteid;

    @Override
    public void load() {
        logger.log(Level.INFO, "LOADING");
        
        GameRegistry.registerBlock(researchBlock,researchBlock.getUnlocalizedName());
        
        for (ResearchBlockTyp typ : ResearchBlockTyp.values()) {
            GameRegistry.registerTileEntityWithAlternatives(typ.getTileentity(), "INDUSTRIALSCIENCE.RESEARCH."+typ.name(), typ.name());
            LanguageRegistry.addName(new ItemStack(researchBlock, 1, typ.ordinal()), typ.getReadableName());
        }
        
        // Research Desk
        GameRegistry.registerBlock(researchdesk,
                researchdesk.getUnlocalizedName());
        GameRegistry
                .registerTileEntity(ResearchDeskTile.class, "Research-Desk");
        GameRegistry.addRecipe(new ShapedOreRecipe(researchdesk, new Object[] {
                "WWW", "S S", "S S", Character.valueOf('W'), "slabWood",
                Character.valueOf('S'), "stickWood" }));

        // Researchbook
        GameRegistry.addRecipe(new ShapelessOreRecipe(researchbook,
                new Object[] { Item.book, "dyeLime", "dyeLime" }));

        // Reseach Note

        addresearches();
    }

    @Override
    public void init() {
        initCreativeTab();
        logger.log(Level.INFO, "INIT");
        researchBlock = new ResearchBlock(BlockIDs.get("researchblock"));
        researchBlockID=BlockIDs.get("researchblock");
        researchdesk = new ResearchDesk(BlockIDs.get("researchdesk"));
        researchdeskid = BlockIDs.get("researchdesk");
        researchbook = new ResearchBook(ItemIDs.get("researchbook"));
        researchbookid = ItemIDs.get("researchbook");
        researchNote = new ResearchNote(ItemIDs.get("researchnote"));
        researchNoteid = ItemIDs.get("researchnote");

    }

    private void addresearches() {
    }

    @Override
    public Hashtable<String, Integer> getNeededBlockIDs() {
        Hashtable<String, Integer> neededBlockIDs = new Hashtable<String, Integer>();
        neededBlockIDs.put("researchdesk", 756);
        neededBlockIDs.put("researchblock", 757);
        return neededBlockIDs;
    }

    @Override
    public Hashtable<String, Integer> getNeededItemIDs() {
        Hashtable<String, Integer> neededItemIDs = new Hashtable<String, Integer>();
        neededItemIDs.put("researchbook", 8123);
        neededItemIDs.put("researchnote", 8124);
        return neededItemIDs;
    }

    @Override
    public void postinit() {
        logger.log(Level.INFO, "POST-INIT");

    }

    @Override
    public String getName() {
        return "Research Module";
    }

    @Override
    public ItemStack getIconitemstack() {
        return new ItemStack(researchbook);
    }

}
