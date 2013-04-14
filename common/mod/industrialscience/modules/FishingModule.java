package mod.industrialscience.modules;

import java.util.Hashtable;
import java.util.logging.Level;

import cpw.mods.fml.common.registry.GameRegistry;

import mod.industrialscience.modules.fishing.FishtrapBlock;
import mod.industrialscience.modules.fishing.Fishtraptile;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class FishingModule extends ISAbstractModule {
	public Block basicfishtrap;
	public int basicfishtrapid;

	@Override
	public void load() {
		logger.log(Level.INFO, "LOADING");
		//Basicfishtrap
		GameRegistry.registerBlock(basicfishtrap,basicfishtrap.getUnlocalizedName());
		GameRegistry.registerTileEntity(Fishtraptile.class,"Basic-Fishtrap");
		basicfishtrap.setCreativeTab(CreativeTab);
		GameRegistry.addRecipe(new ItemStack(basicfishtrap),new Object[]{"X X X"," X X ","X X X", Character.valueOf('X'), Item.stick});
		

	}

	@Override
	public void init() {
		initCreativeTab();
		logger.log(Level.INFO, "INIT");
		basicfishtrapid=BlockIDs.get("basicfishtrap");
		basicfishtrap=new FishtrapBlock(basicfishtrapid);

	}

	@Override
	public Hashtable<String, Integer> getNeededBlockIDs() {
		Hashtable<String, Integer> NeededBlockIDs = new Hashtable<String, Integer>();
		NeededBlockIDs.put("basicfishtrap", 758);
		return NeededBlockIDs;
	}

	@Override
	public Hashtable<String, Integer> getNeededItemIDs() {
		Hashtable<String, Integer> neededItemIDs = new Hashtable<String, Integer>();
		return neededItemIDs;
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
