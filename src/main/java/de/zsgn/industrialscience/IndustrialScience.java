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
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import de.zsgn.industrialscience.blocks.BlockSingularity;
import de.zsgn.industrialscience.commands.IndustrialScienceMainCommand;
import de.zsgn.industrialscience.items.ItemAncientTechnology;

@Mod(modid = IndustrialScience.MODID, version = IndustrialScience.VERSION)
public class IndustrialScience
{
    public static final String MODID = "industrialscience";
    public static final String VERSION = "@VERSION@";
    
    public Block blocksingularity= new BlockSingularity(Material.rock);
    
    public Item itemAncientTechnology = new  ItemAncientTechnology();
    
    public static CreativeTabs creativetab = new IndustrialScienceCreativeTab();
    
    public final IndustrialScienceMainCommand industrialScienceMainCommand=new IndustrialScienceMainCommand();
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
		FMLLog.log(Level.INFO, "This is IndustrialScience version: "+IndustrialScience.VERSION);
		
        GameRegistry.registerBlock(blocksingularity, blocksingularity.getUnlocalizedName());
        
        GameRegistry.registerItem(itemAncientTechnology, itemAncientTechnology.getUnlocalizedName());        
        
        addRecipes();
        
        
        
    }

	private void addRecipes() {
		GameRegistry.addSmelting(Items.diamond, new ItemStack(blocksingularity), 9001);
		GameRegistry.addRecipe(new ItemStack(blocksingularity), " X ", " X ","BBB",'X',Items.apple,'B', Items.book);
	}
	
	@EventHandler
	public void serverStart(FMLServerStartingEvent event)
	{
	         MinecraftServer server = MinecraftServer.getServer();
	         ServerCommandManager manager = (ServerCommandManager) server.getCommandManager();
	         manager.registerCommand(industrialScienceMainCommand);
	         
	}
}
