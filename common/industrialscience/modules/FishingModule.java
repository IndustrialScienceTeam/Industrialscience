package industrialscience.modules;

import industrialscience.blocksystem.ISModuleModelBlock;
import industrialscience.modules.fishing.FishingModuleModelBlock;
import industrialscience.modules.fishing.FishingModulePackethandler;
import industrialscience.modules.fishing.GUI.TrapCraftingTableGUI;
import industrialscience.modules.fishing.GUI.containers.TrapCraftingTableContainer;
import industrialscience.modules.fishing.Items.LobsterItem;
import industrialscience.modules.fishing.TileEntities.TrapCraftingTableTileEntity;

import java.util.Hashtable;
import java.util.logging.Level;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import cpw.mods.fml.common.registry.GameRegistry;

public class FishingModule extends ISAbstractModule {
    public FishingModule(int normalblockid,int modelblockid, int i) {
        super(NeededItemIDs(), normalblockid,modelblockid, "fishingmodule",
                "IndustrialScience Fishing", i, new FishingModulePackethandler());
    }

    public static ISModuleModelBlock fishingblock;

    public static Item lobsteritem;
    public static int lobsteritemID;

    @Override
    public void load() {
        logger.log(Level.INFO, "LOADING");
        fishingblock.register();
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
        fishingblock = new FishingModuleModelBlock(getModelBlockID(), getPrefix());
        lobsteritemID = getItemIDs().get("lobsteritem");
        lobsteritem = new LobsterItem(lobsteritemID, this.getPrefix());

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
        switch(world.getBlockMetadata(x, y, z)){
    	case FishingModuleBlock.TRAPCRAFTINGBLOCKMETAID:
    		return new TrapCraftingTableContainer((TrapCraftingTableTileEntity)world.getBlockTileEntity(x, y, z), player.inventory);
    }
    return null;
    }

    @Override
    public Object getClientGUIElement(int blockMetadata, EntityPlayer player,
            World world, int x, int y, int z) {
        switch(world.getBlockMetadata(x, y, z)){
        	case FishingModuleBlock.TRAPCRAFTINGBLOCKMETAID:
        		return new TrapCraftingTableGUI((TrapCraftingTableTileEntity)world.getBlockTileEntity(x, y, z), player.inventory);
        }
        return null;
    }

    @Override
    public void registerRenderers() {
       fishingblock.registerRenderers();

    }
}
