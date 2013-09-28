package industrialscience.modules;

import industrialscience.modules.fishing.FishingBlock;
import industrialscience.modules.fishing.FishingBlockType;
import industrialscience.modules.fishing.FishingPackethandler;
import industrialscience.modules.fishing.LobsterItem;

import java.util.Hashtable;
import java.util.logging.Level;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import cpw.mods.fml.common.registry.GameRegistry;

public class FishingModule extends ISAbstractModule {
    public FishingModule(int blockid, int i) {
        super(NeededItemIDs(), blockid, "fishingmodule",
                "IndustrialScience Fishing",i, new FishingPackethandler());
    }

    public static Block fishingblock;

    public static Item lobsteritem;
    public static int lobsteritemID;

    @Override
    public void load() {
        logger.log(Level.INFO, "LOADING");
        FishingBlockType.register(fishingblock,getPrefix());
        fishingblock.setCreativeTab(CreativeTab);
        lobsteritem.setCreativeTab(CreativeTab);
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
        logger.log(Level.INFO, "INIT");
        initCreativeTab(new ItemStack(Item.fishingRod));
        fishingblock = new FishingBlock(getBlockID());
        lobsteritemID = getItemIDs().get("lobsteritem");
        lobsteritem = new LobsterItem(lobsteritemID);

    }

    @Override
    public void postinit() {
        logger.log(Level.INFO, "POST-INIT");

    }

    private static Hashtable<String, Integer> NeededItemIDs() {
        Hashtable<String, Integer> NeededItemIDs = new Hashtable<String, Integer>();
        NeededItemIDs.put("lobsteritem", 760);
        return NeededItemIDs;
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
