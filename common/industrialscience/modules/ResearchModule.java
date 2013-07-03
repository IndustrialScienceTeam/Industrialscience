package industrialscience.modules;
import industrialscience.modules.research.frontend.ItemResearchBlock;
import industrialscience.modules.research.frontend.ResearchBlock;
import industrialscience.modules.research.frontend.ResearchBlockType;
import industrialscience.modules.research.frontend.ResearchBook;
import industrialscience.modules.research.frontend.ResearchItem;
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
    public static Block researchBlock;
    public static int researchBlockID;

    public static ResearchItem researchitem;
    public static int researchitemid;
    @Override
    public void load() {
        logger.log(Level.INFO, "LOADING");
        researchitem.register();
        GameRegistry.registerBlock(researchBlock,ItemResearchBlock.class,getPrefix()+researchBlock.getUnlocalizedName2());
        for (ResearchBlockType typ : ResearchBlockType.values()) {
            GameRegistry.registerTileEntityWithAlternatives(typ.getTileentity(), getPrefix()+typ.name(), typ.name());
            LanguageRegistry.addName(new ItemStack(researchBlock, 1, typ.ordinal()), typ.getReadableName());
        }
        
        // Research Desk
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(researchBlock, 1, 1), new Object[] {
                "WWW", "S S", "S S", Character.valueOf('W'), "slabWood",
                Character.valueOf('S'), "stickWood" }));

        // Researchbook
       // GameRegistry.addRecipe(new ShapelessOreRecipe(researchbook,
        //        new Object[] { Item.book, "dyeLime", "dyeLime" }));

        // Reseach Note

        addresearches();
    }

    @Override
    public void init() {
        logger.log(Level.INFO, "INIT");
        initCreativeTab();
        setPrefix("RESEARCH-MODULE");
        researchBlock = new ResearchBlock(BlockIDs.get("researchblock"));
        researchBlockID=BlockIDs.get("researchblock");
        researchitem = new ResearchItem(ItemIDs.get("researchitem"));
        researchitemid = ItemIDs.get("researchitem");

    }

    private void addresearches() {
    }

    @Override
    public Hashtable<String, Integer> getNeededBlockIDs() {
        Hashtable<String, Integer> neededBlockIDs = new Hashtable<String, Integer>();
        neededBlockIDs.put("researchblock", 757);
        return neededBlockIDs;
    }

    @Override
    public Hashtable<String, Integer> getNeededItemIDs() {
        Hashtable<String, Integer> neededItemIDs = new Hashtable<String, Integer>();
        neededItemIDs.put("researchitem", 8123);
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
        return new ItemStack(researchitem,1,ResearchItem.RESEARCHBOOKID);
    }

    @Override
    public IGuiHandler getGUIHandler() {
        // TODO Auto-generated method stub
        return null;
    }

}
