package de.zsgn.industrialscience.factory;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import de.zsgn.industrialscience.factory.block.BlockChimney;
import de.zsgn.industrialscience.factory.block.BlockIronEnergyLink;
import de.zsgn.industrialscience.factory.block.BlockIronFurnaceController;
import de.zsgn.industrialscience.factory.block.BlockIronHatch;
import de.zsgn.industrialscience.factory.block.BlockIronHull;
import de.zsgn.industrialscience.factory.block.BlockStoneFurnaceController;
import de.zsgn.industrialscience.factory.block.BlockStoneHatch;
import de.zsgn.industrialscience.factory.block.BlockStoneHull;
import de.zsgn.industrialscience.factory.item.ItemThermometer;
import de.zsgn.industrialscience.factory.item.ItemTong;
import de.zsgn.industrialscience.factory.tileentity.TileEntityChimney;
import de.zsgn.industrialscience.factory.tileentity.TileEntityEnergyLink;
import de.zsgn.industrialscience.factory.tileentity.TileEntityHatch;
import de.zsgn.industrialscience.factory.tileentity.TileEntityMultiBlock;
import de.zsgn.industrialscience.factory.tileentity.controllers.ITileEntityMultiBlockController;
import de.zsgn.industrialscience.factory.tileentity.controllers.TileEntityMultiBlockFurnace;

public class FactoryModule {
    private Block blockStonehull;
    private Block blockIronhull;
    private Block blockStonehatch;
    private Block blockChimney;
    private Block blockControllerStonefurnace;
    private Block blockControllerIronfurnace;
    private Block blockironhatch;
    private Block blockironEnergyLink;
    private Item itemThermometer;
    private Item itemTong;

    public void init(FMLInitializationEvent event) {
        blockIronhull = new BlockIronHull();
        blockStonehull = new BlockStoneHull();
        blockStonehatch = new BlockStoneHatch();
        blockChimney = new BlockChimney();
        blockControllerStonefurnace = new BlockStoneFurnaceController();
        blockControllerIronfurnace = new BlockIronFurnaceController();
        blockironhatch = new BlockIronHatch();
        blockironEnergyLink=new BlockIronEnergyLink();
        itemThermometer=new ItemThermometer();
        itemTong=new ItemTong();

        GameRegistry.registerBlock(blockControllerStonefurnace,
                blockControllerStonefurnace.getUnlocalizedName().substring(5));
        GameRegistry.registerBlock(blockStonehull, blockStonehull
                .getUnlocalizedName().substring(5));
        GameRegistry.registerBlock(blockIronhull, blockIronhull
                .getUnlocalizedName().substring(5));
        GameRegistry.registerBlock(blockChimney, blockChimney
                .getUnlocalizedName().substring(5));
        GameRegistry.registerBlock(blockStonehatch, blockStonehatch
                .getUnlocalizedName().substring(5));
        GameRegistry.registerBlock(blockControllerIronfurnace,
                blockControllerIronfurnace.getUnlocalizedName().substring(5));
        GameRegistry.registerBlock(blockironhatch, blockironhatch
                .getUnlocalizedName().substring(5));
        GameRegistry.registerBlock(blockironEnergyLink, blockironEnergyLink
                .getUnlocalizedName().substring(5));
        
        GameRegistry.registerItem(itemThermometer, itemThermometer
                .getUnlocalizedName().substring(5));
        GameRegistry.registerItem(itemTong, itemTong
                .getUnlocalizedName().substring(5));

        GameRegistry.registerTileEntity(TileEntityMultiBlockFurnace.class,
                "tile.multiblockfurnace");
        GameRegistry.registerTileEntity(TileEntityMultiBlock.class,
                "tile.multiblock");
        GameRegistry.registerTileEntity(ITileEntityMultiBlockController.class,
                "tile.multiblockcontroller");
        GameRegistry.registerTileEntity(TileEntityHatch.class, "tile.hatch");
        GameRegistry
                .registerTileEntity(TileEntityChimney.class, "tile.chimney");
        GameRegistry.registerTileEntity(TileEntityEnergyLink.class, "tile.energylink");
    }

    public Block getBlockStonehull() {
        return blockStonehull;
    }

    public Block getBlockIronhull() {
        return blockIronhull;
    }

    public Block getBlockStonehatch() {
        return blockStonehatch;
    }

    public Block getBlockChimney() {
        return blockChimney;
    }

    public Block getBlockControllerStonefurnace() {
        return blockControllerStonefurnace;
    }

    public Block getBlockControllerIronfurnace() {
        return blockControllerIronfurnace;
    }

    public Block getBlockironhatch() {
        return blockironhatch;
    }

    public Item getItemThermometer() {
        return itemThermometer;
    }

    public Item getItemTong() {
        return itemTong;
    }

}
