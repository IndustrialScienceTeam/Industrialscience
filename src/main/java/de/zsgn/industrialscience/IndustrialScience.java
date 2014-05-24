package de.zsgn.industrialscience;

import org.apache.logging.log4j.Level;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.command.ServerCommandManager;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import de.zsgn.industrialscience.block.*;
import de.zsgn.industrialscience.command.*;
import de.zsgn.industrialscience.item.*;

@Mod(modid = IndustrialScience.MODID, version = IndustrialScience.VERSION)
public class IndustrialScience{
    public static final String MODID = "industrialscience";
    public static final String VERSION = "@VERSION@";
    @Instance
    private static IndustrialScience instance;
    
    private Block blocksingularity= new BlockSingularity(Material.rock);
    private Block blockmysteriousportal = new BlockMysteriousPortal();
    
    private Item itemancienttechnology = new  ItemAncientTechnology();
    private Item itemcrack = new ItemCrack();
    private Item itemclimbingboots = new ItemClimbingBoots();
    
    private CreativeTabs creativetab = new IndustrialScienceCreativeTab();
    
    private IndustrialScienceMainCommand industrialScienceMainCommand=new IndustrialScienceMainCommand();
    @EventHandler
    public void init(FMLInitializationEvent event){
		FMLLog.log(Level.INFO, "This is IndustrialScience version: "+IndustrialScience.VERSION);
		instance=this;
        GameRegistry.registerBlock(blocksingularity, blocksingularity.getUnlocalizedName());
        GameRegistry.registerBlock(blockmysteriousportal, blockmysteriousportal.getUnlocalizedName());
        
        GameRegistry.registerItem(itemancienttechnology, itemancienttechnology.getUnlocalizedName());
        
        //registers the item so the game can actually use it while playing
        GameRegistry.registerItem(itemcrack, itemcrack.getUnlocalizedName());
        GameRegistry.registerItem(itemclimbingboots , itemclimbingboots.getUnlocalizedName());
        
        addRecipes();
        
        
        
    }

	private void addRecipes() {
		GameRegistry.addSmelting(Items.diamond, new ItemStack(blocksingularity), 9001);
		GameRegistry.addShapelessRecipe(new ItemStack(itemcrack), new ItemStack(Items.potionitem, 1, 0), Items.reeds);
		GameRegistry.addRecipe(new ItemStack(blocksingularity), " X ", " X ","BBB",'X',Items.apple,'B', Items.book);
		GameRegistry.addRecipe(new ItemStack(itemclimbingboots),"   "," XY"," Z ",'X',Items.leather_boots,'Y',Items.iron_ingot,'Z',Items.gold_ingot);
		//GameRegistry.addRecipe(new ItemStack(itemclimbingboots), " X ", " X ","BBB",'X',Items.apple,'B', Items.book);
	}
	
	@EventHandler
	public void serverStart(FMLServerStartingEvent event){
	         MinecraftServer server = MinecraftServer.getServer();
	         ServerCommandManager manager = (ServerCommandManager) server.getCommandManager();
	         manager.registerCommand(industrialScienceMainCommand);
	         
	}

	public static IndustrialScience getInstance() {
		return instance;
	}

	public Block getBlocksingularity() {
		return blocksingularity;
	}

	public Block getBlockmysteriousportal() {
		return blockmysteriousportal;
	}

	public Item getItemancienttechnology() {
		return itemancienttechnology;
	}

	public Item getItemcrack() {
		return itemcrack;
	}

	public Item getItemclimbingboots() {
		return itemclimbingboots;
	}

	public CreativeTabs getCreativetab() {
		return creativetab;
	}

	public IndustrialScienceMainCommand getIndustrialScienceMainCommand() {
		return industrialScienceMainCommand;
	}
}
