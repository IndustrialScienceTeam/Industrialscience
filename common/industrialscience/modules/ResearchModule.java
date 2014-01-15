package industrialscience.modules;

import industrialscience.blocksystem.ISModuleModelBlock;
import industrialscience.modules.research.ResearchModuleModelBlock;
import industrialscience.modules.research.ResearchPacketHandler;
import industrialscience.modules.research.frontend.ResearchBook;
import industrialscience.modules.research.frontend.ResearchNote;
import industrialscience.modules.research.frontend.GUI.CopierGUI;
import industrialscience.modules.research.frontend.GUI.ResearchBookGUI;
import industrialscience.modules.research.frontend.GUI.containers.CopierContainer;
import industrialscience.modules.research.frontend.GUI.containers.ResearchBookContainer;
import industrialscience.modules.research.frontend.TileEntities.CopierTile;
import industrialscience.modules.research.frontend.blocks.ResearchDeskISBlock;

import java.util.Hashtable;
import java.util.logging.Level;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

public class ResearchModule extends ISAbstractModule {
    public ResearchModule(int normalblockID,int modelblockid, int bitprefix) {
        super(NeededItemIDs(), normalblockID,modelblockid, "research",
                "IndustrialScience Research", bitprefix,
                new ResearchPacketHandler());
    }

    public static ISModuleModelBlock researchBlock;

    public static Item researchbook;
    public static int researchbookID;

    public static Item researchNote;
    public static int researchNoteID;

    @Override
    public void load() {
        logger.log(Level.INFO, "LOADING");
        researchBlock.register();
        researchBlock.setCreativeTab(CreativeTab);
        
        researchbook.setCreativeTab(CreativeTab);
        researchNote.setCreativeTab(CreativeTab);

        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(researchBlock,
                1, 1), new Object[] { "WWW", "S S", "S S",
                Character.valueOf('W'), "slabWood", Character.valueOf('S'),
                "stickWood" }));
        GameRegistry.addRecipe(new ShapelessOreRecipe(researchbook,
                new Object[] { Item.book, "dyeLime", "dyeLime" }));
    }

    @Override
    public void init() {
        logger.log(Level.INFO, "INIT");
        researchbookID = getItemIDs().get("researchbook");
        researchbook = new ResearchBook(researchbookID - 256);
        researchNoteID = getItemIDs().get("researchnote");
        researchNote = new ResearchNote(researchNoteID - 256);
        researchBlock = new ResearchModuleModelBlock(getModelBlockID(), this.getPrefix());
        initCreativeTab(new ItemStack(researchBlock, 1,
                1));
    }

    @Override
    public void postinit() {
        logger.log(Level.INFO, "POST-INIT");

    }

    public static Hashtable<String, Integer> NeededItemIDs() {
        Hashtable<String, Integer> neededItemIDs = new Hashtable<String, Integer>();
        neededItemIDs.put("researchbook", 8123);
        neededItemIDs.put("researchnote", 8124);
        return neededItemIDs;
    }

    @Override
    public Object getServerGUIElement(int id, EntityPlayer player, World world,
            int x, int y, int z) {
        if (id == 0) {
            if (ResearchModuleModelBlock.RESEARCHDESKMETAID == world
                    .getBlockMetadata(x, y, z))
                return null;
            if (ResearchModuleModelBlock.COPIERMETAID == world.getBlockMetadata(x,
                    y, z))
                return new CopierContainer(
                        (CopierTile) world.getBlockTileEntity(x, y, z),
                        player.inventory);
            return null;
        } else {
            switch (id) {
                case 1:
                    return new ResearchBookContainer(player.inventory,
                            new int[4]);

                default:
                    return null;
            }
        }
    }

    @Override
    public Object getClientGUIElement(int id, EntityPlayer player, World world,
            int x, int y, int z) {
        if (id == 0) {
            if (ResearchModuleModelBlock.RESEARCHDESKMETAID == world
                    .getBlockMetadata(x, y, z))
                return null;
            if (ResearchModuleModelBlock.COPIERMETAID == world.getBlockMetadata(x,
                    y, z))
                return new CopierGUI((CopierTile) world.getBlockTileEntity(x,
                        y, z), player.inventory);
            return null;
        } else {
            switch (id) {
                case 1:
                    return new ResearchBookGUI(player,
                            player.getCurrentEquippedItem());

                default:
                    return null;
            }
        }
    }

    @Override
    public void registerRenderers() {
    researchBlock.registerRenderers();
        }
}
