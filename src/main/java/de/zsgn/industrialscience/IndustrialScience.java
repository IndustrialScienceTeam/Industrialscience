package de.zsgn.industrialscience;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Properties;

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
import cpw.mods.fml.common.IWorldGenerator;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import de.zsgn.industrialscience.block.BlockIronBricks;
import de.zsgn.industrialscience.block.BlockMysteriousPortal;
import de.zsgn.industrialscience.block.BlockReinforcedBricks;
import de.zsgn.industrialscience.block.BlockSingularity;
import de.zsgn.industrialscience.block.MultiBlockHullStoneHull;
import de.zsgn.industrialscience.block.multiblock.MultiBlockControllerTier1StoneFurnace;
import de.zsgn.industrialscience.block.multiblock.MultiBlockHullIronHull;
import de.zsgn.industrialscience.command.CommandGenerateRoom;
import de.zsgn.industrialscience.command.IndustrialScienceMainCommand;
import de.zsgn.industrialscience.item.ItemAncientTechnology;
import de.zsgn.industrialscience.item.ItemClimbingBoots;
import de.zsgn.industrialscience.item.ItemCrystalReed;
import de.zsgn.industrialscience.tileentity.TileEntityMysteriousPortal;
import de.zsgn.industrialscience.tileentity.multiblock.TileEntityTier1StoneFurnace;
import de.zsgn.industrialscience.world.WorldGeneratorPortalRoom;
import de.zsgn.industrialsciencedungeonsystem.DungeonRoom;
import de.zsgn.industrialsciencedungeonsystem.RouteType;

@Mod(modid = IndustrialScience.MODID, version = IndustrialScience.VERSION)
public class IndustrialScience{
    public static final String MODID = "industrialscience";
    public static final String VERSION = "@VERSION@";
    @Instance
    private static IndustrialScience instance;
    @SidedProxy(serverSide="de.zsgn.industrialscience.CommonProxy", clientSide="de.zsgn.industrialscience.ClientProxy")
    public static CommonProxy proxy;

    private Hashtable<String, DungeonRoom> dungeonroomlist = new Hashtable<String, DungeonRoom>();

    private Block blocksingularity;
    private Block blockmysteriousportal;
    private Block blockreinforcedbricks;
    private Block blockcontrollertier1stonefurnace;
    private Block blockironbricks;
    private Block blockstonehull;
    private Block blockironhull;

    private Item itemancienttechnology;
    private Item itemcrystalreed;
    private Item itemclimbingboots;

    private CreativeTabs creativetab;

    private IWorldGenerator worldgeneratorportalroom;

    private Properties props= new Properties();

    private IndustrialScienceMainCommand industrialScienceMainCommand=new IndustrialScienceMainCommand();
    private CommandGenerateRoom industrialScienceGenerateRoom= new CommandGenerateRoom();
    @EventHandler
    public void init(FMLInitializationEvent event){
        FMLLog.log(Level.INFO, "This is IndustrialScience version: "+IndustrialScience.VERSION);
        instance=this;
        loadProps();
        applyProps();
        initFields();
        GameRegistry.registerBlock(blocksingularity, blocksingularity.getUnlocalizedName().substring(5));
        GameRegistry.registerBlock(blockmysteriousportal, blockmysteriousportal.getUnlocalizedName().substring(5));
        GameRegistry.registerBlock(blockreinforcedbricks, blockreinforcedbricks.getUnlocalizedName().substring(5));
        GameRegistry.registerBlock(blockcontrollertier1stonefurnace, blockcontrollertier1stonefurnace.getUnlocalizedName().substring(5));
        GameRegistry.registerBlock(blockironbricks, blockironbricks.getUnlocalizedName().substring(5));
        GameRegistry.registerBlock(blockstonehull, blockstonehull.getUnlocalizedName().substring(5));
        GameRegistry.registerBlock(blockironhull, blockironhull.getUnlocalizedName().substring(5));

        GameRegistry.registerItem(itemancienttechnology, itemancienttechnology.getUnlocalizedName().substring(5));

        //registers the item so the game can actually use it while playing
        GameRegistry.registerItem(itemcrystalreed, itemcrystalreed.getUnlocalizedName().substring(5));
        GameRegistry.registerItem(itemclimbingboots , itemclimbingboots.getUnlocalizedName().substring(5));

        GameRegistry.registerTileEntity(TileEntityMysteriousPortal.class, blockmysteriousportal.getUnlocalizedName());
        GameRegistry.registerTileEntity(TileEntityTier1StoneFurnace.class, blockcontrollertier1stonefurnace.getUnlocalizedName());

        GameRegistry.registerWorldGenerator(worldgeneratorportalroom, 5);

        addRecipes();
    }

    private void applyProps() {
        String[] ids = props.getProperty("BadEffectsIDs").split(",");
        ArrayList<Integer> idList= new ArrayList<Integer>();
        for (String string : ids) {
            Integer id=0;
            try {
                id=Integer.decode(string);
            } catch (NumberFormatException e) {
                FMLLog.log(Level.INFO, "Invalid potion ID number! :"+string);
            }finally{
                if(id!=0){
                    idList.add(id);
                }
            }
        }
        TileEntityMysteriousPortal.setEffectlist(idList.toArray(new Integer[1]));


    }

    private void loadProps() {
        props.setProperty("BadEffectsIDs", "2,4,15,18,19");
        loadDungeonRooms();
    }

    private void loadDungeonRooms() {
        DungeonRoom def = new DungeonRoom(RouteType.STRAIGHT, DungeonRoom.getEmptyContent(), "DefaultStraight");
        dungeonroomlist.put(def.getName(), def);

    }

    private void initFields() {
        creativetab = new IndustrialScienceCreativeTab();
        blockmysteriousportal= new BlockMysteriousPortal();
        blocksingularity= new BlockSingularity(Material.rock);
        blockreinforcedbricks=new BlockReinforcedBricks();
        blockironhull=new MultiBlockHullIronHull();
        blockstonehull=new MultiBlockHullStoneHull();
        blockcontrollertier1stonefurnace=new MultiBlockControllerTier1StoneFurnace();
        itemancienttechnology=new  ItemAncientTechnology();
        itemcrystalreed= new ItemCrystalReed();
        itemclimbingboots= new ItemClimbingBoots();
        worldgeneratorportalroom=new WorldGeneratorPortalRoom();
        blockironbricks= new BlockIronBricks(Material.iron);
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
        manager.registerCommand(industrialScienceGenerateRoom);


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

    public Block getBlockreinforcedbricks() {
        return blockreinforcedbricks;
    }

    public Item getItemcrystalreed() {
        return itemcrystalreed;
    }

    public IWorldGenerator getWorldgeneratorportalroom() {
        return worldgeneratorportalroom;
    }
    public DungeonRoom getDungeonRoom(String key) {
        return dungeonroomlist.get(key);
    }
    public Block getBlockironbricks() {
        return blockironbricks;

    }
    public Block getBlockstonehull() {
        return blockstonehull;
    }

}
