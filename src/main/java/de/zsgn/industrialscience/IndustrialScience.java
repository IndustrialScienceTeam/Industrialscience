package de.zsgn.industrialscience;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.command.ServerCommandManager;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;

import org.apache.logging.log4j.Level;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import de.zsgn.industrialscience.block.BlockMysteriousPortal;
import de.zsgn.industrialscience.block.BlockReinforcedBricks;
import de.zsgn.industrialscience.block.BlockSingularity;
import de.zsgn.industrialscience.command.IndustrialScienceMainCommand;
import de.zsgn.industrialscience.item.ItemAncientTechnology;
import de.zsgn.industrialscience.item.ItemClimbingBoots;
import de.zsgn.industrialscience.item.ItemCrystalReed;

@Mod(modid = IndustrialScience.MODID, version = IndustrialScience.VERSION)
public class IndustrialScience{
    public static final String MODID = "industrialscience";
    public static final String VERSION = "@VERSION@";
    @Instance
    private static IndustrialScience instance;

    private Block blocksingularity;
    private Block blockmysteriousportal;
    private Block blockreinforcedbricks;

    private Item itemancienttechnology;
    private Item itemcrystalreed;
    private Item itemclimbingboots;

    private CreativeTabs creativetab;

    private IndustrialScienceMainCommand industrialScienceMainCommand=new IndustrialScienceMainCommand();
    @EventHandler
    public void init(FMLInitializationEvent event){
        FMLLog.log(Level.INFO, "This is IndustrialScience version: "+IndustrialScience.VERSION);
        instance=this;
        initFields();
        GameRegistry.registerBlock(blocksingularity, blocksingularity.getUnlocalizedName().substring(5));
        GameRegistry.registerBlock(blockmysteriousportal, blockmysteriousportal.getUnlocalizedName().substring(5));
        GameRegistry.registerBlock(blockreinforcedbricks, blockreinforcedbricks.getUnlocalizedName().substring(5));
        
        GameRegistry.registerItem(itemancienttechnology, itemancienttechnology.getUnlocalizedName().substring(5));

        //registers the item so the game can actually use it while playing
        GameRegistry.registerItem(itemcrystalreed, itemcrystalreed.getUnlocalizedName().substring(5));
        GameRegistry.registerItem(itemclimbingboots , itemclimbingboots.getUnlocalizedName().substring(5));

        addRecipes();



    }

    private void initFields() {
        creativetab = new IndustrialScienceCreativeTab();
        blockmysteriousportal= new BlockMysteriousPortal();
        blocksingularity= new BlockSingularity(Material.rock);
        blockreinforcedbricks=new BlockReinforcedBricks();
        itemancienttechnology=new  ItemAncientTechnology();
        itemcrystalreed= new ItemCrystalReed();
        itemclimbingboots= new ItemClimbingBoots();
    }

    private void addRecipes() {
        GameRegistry.addSmelting(Items.diamond, new ItemStack(blocksingularity), 9001);
        GameRegistry.addShapelessRecipe(new ItemStack(itemcrystalreed), new ItemStack(Items.potionitem, 1, 0), Items.reeds);
        GameRegistry.addRecipe(new ItemStack(blocksingularity), " X ", " X ","BBB",'X',Items.apple,'B', Items.book);
        GameRegistry.addRecipe(new ItemStack(blockreinforcedbricks, 2), " X ", "XBX"," X ",'X',Items.brick,'B', Blocks.stonebrick);
        GameRegistry.addRecipe(new ItemStack(itemclimbingboots),"   "," XY"," Z ",'X',Items.leather_boots,'Y',Items.iron_ingot,'Z',Items.gold_ingot);
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
        return itemcrystalreed;
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
