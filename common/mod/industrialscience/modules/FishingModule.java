package mod.industrialscience.modules;

import java.util.Hashtable;
import java.util.logging.Level;

import cpw.mods.fml.common.registry.GameRegistry;

import mod.industrialscience.modules.fishing.FishtrapBlock;
import mod.industrialscience.modules.fishing.LobsterItem;
import mod.industrialscience.modules.fishing.Lobster_trapBlock;
import mod.industrialscience.modules.fishing.Fishtraptile;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class FishingModule extends ISAbstractModule {
	public Block basicfishtrap;
	public int basicfishtrapid;

	public Block lobster_trap;
	public int lobster_trapid;
	
	public Item lobsteritem;
	public int lobsteritemid;
	
	@Override
	public void load() {
		logger.log(Level.INFO, "LOADING");
		
		//Basicfishtrap
		GameRegistry.registerBlock(basicfishtrap,basicfishtrap.getUnlocalizedName());
		GameRegistry.registerTileEntity(Fishtraptile.class,"Basic-Fishtrap");
		basicfishtrap.setCreativeTab(CreativeTab);
		GameRegistry.addRecipe(new ItemStack(basicfishtrap),new Object[]{"X X X"," X X ","X X X", Character.valueOf('X'), Item.stick});
		
		//Lobster trap
		GameRegistry.registerBlock(lobster_trap,lobster_trap.getUnlocalizedName());
		GameRegistry.registerTileEntity(Fishtraptile.class,"Lobster-Trap");
		lobster_trap.setCreativeTab(CreativeTab);
		GameRegistry.addRecipe(new ItemStack(basicfishtrap),new Object[]{"X X X"," X X ","X X X", Character.valueOf('X'), Item.paper});

		//Lobster item
		GameRegistry.registerItem(lobsteritem , lobsteritem.getUnlocalizedName());
		lobsteritem.setCreativeTab(CreativeTab);
	}

	@Override
	public void init() {
		initCreativeTab();
		logger.log(Level.INFO, "INIT");
		
		//Basicfishtrap
		basicfishtrapid=BlockIDs.get("basicfishtrap");
		basicfishtrap=new FishtrapBlock(basicfishtrapid);
		
		//Lobster trap
		lobster_trapid=BlockIDs.get("lobster_trap");
		lobster_trap=new Lobster_trapBlock(lobster_trapid);
		
		//Lobster item
		
		//test//lobsteritemid=ItemIDs.get(lobsteritem);
		//test//lobsteritem = new Item(lobsteritemid);
		lobsteritem = new Item(760);
	}

	@Override
	public Hashtable<String, Integer> getNeededBlockIDs() {
		Hashtable<String, Integer> NeededBlockIDs = new Hashtable<String, Integer>();
		
		NeededBlockIDs.put("basicfishtrap", 758); //Basicfishtrap
		NeededBlockIDs.put("lobster_trap", 759); //Lobster trap
		
		return NeededBlockIDs;
	}

	@Override
	public Hashtable<String, Integer> getNeededItemIDs() {
		Hashtable<String, Integer> NeededItemIDs = new Hashtable<String, Integer>();
		
		NeededItemIDs.put("lobsteritem" , 760); //Lobster item
		
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

}
