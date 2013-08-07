package industrialscience.modules;

import industrialscience.modules.research.ResearchPacketHandler;
import industrialscience.modules.research.frontend.ResearchBlock;
import industrialscience.modules.research.frontend.ResearchBlockType;
import industrialscience.modules.research.frontend.ResearchBook;
import industrialscience.modules.research.frontend.ResearchNote;
import industrialscience.modules.research.frontend.GUI.CopierGUI;
import industrialscience.modules.research.frontend.GUI.ResearchBookGUI;
import industrialscience.modules.research.frontend.GUI.containers.CopierContainer;
import industrialscience.modules.research.frontend.GUI.containers.ResearchBookContainer;
import industrialscience.modules.research.frontend.TileEntities.CopierTile;
import industrialscience.modules.research.frontend.TileEntities.ResearchDeskTile;
import industrialscience.modules.research.frontend.renderer.ResearchCopierRenderer;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.logging.Level;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.registry.GameRegistry;

public class ResearchModule extends ISAbstractModule {
    public ResearchModule(int blockID, int bitprefix) {
        super(NeededItemIDs(), blockID, "research",
                "IndustrialScience Research",bitprefix,new ResearchPacketHandler());
    }

    public static Block researchBlock;

    public static Item researchbook;
    public static int researchbookID;

    public static Item researchNote;
    public static int researchNoteID;

    @Override
    public void load() {
        logger.log(Level.INFO, "LOADING");
        ResearchBlockType.register(researchBlock,getPrefix());
        
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
        researchbook = new ResearchBook(researchbookID-256);
        researchNoteID = getItemIDs().get("researchnote");
        researchNote = new ResearchNote(researchNoteID-256);
        researchBlock = new ResearchBlock(getBlockID());
        initCreativeTab(new ItemStack(researchBlock, 1,
                ResearchBlockType.RESEARCHDESK.ordinal()));
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
	public Object getServerGUIElement(int id, EntityPlayer player,
			World world, int x, int y, int z) {
		if(id==0){
		if(ResearchBlockType.RESEARCHDESK.ordinal()==world.getBlockMetadata(x, y, z))
			return null;
		if(ResearchBlockType.COPIER.ordinal()==world.getBlockMetadata(x, y, z))
			return new CopierContainer((CopierTile)world.getBlockTileEntity(x, y, z), player.inventory);
		return null;
		}
		else {
			switch (id) {
			case 1:
				return new ResearchBookContainer(player.inventory, new int[4]);

			default:
				return null;
			}
		}
	}

	@Override
	public Object getClientGUIElement(int id, EntityPlayer player,
			World world, int x, int y, int z) {;
			if(id==0){
				if(ResearchBlockType.RESEARCHDESK.ordinal()==world.getBlockMetadata(x, y, z))
					return null;
				if(ResearchBlockType.COPIER.ordinal()==world.getBlockMetadata(x, y, z))
					return new CopierGUI((CopierTile)world.getBlockTileEntity(x, y, z), player.inventory);
				return null;
				}
				else {
					switch (id) {
					case 1:
						return new ResearchBookGUI(player, player.getCurrentEquippedItem());

					default:
						return null;
					}
				}		
	}

	@Override
	public void registerRenderers() {
		try {
			
			for (ResearchBlockType element : ResearchBlockType.values()) {
				ClientRegistry.bindTileEntitySpecialRenderer(element.tileentity, element.getRenderer().newInstance());
			}
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
