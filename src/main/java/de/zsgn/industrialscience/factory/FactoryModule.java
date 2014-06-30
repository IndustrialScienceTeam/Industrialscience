package de.zsgn.industrialscience.factory;

import net.minecraft.block.Block;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import de.zsgn.industrialscience.factory.block.BlockChimney;
import de.zsgn.industrialscience.factory.block.BlockIronHull;
import de.zsgn.industrialscience.factory.block.BlockStoneFurnaceController;
import de.zsgn.industrialscience.factory.block.BlockStoneHatch;
import de.zsgn.industrialscience.factory.block.BlockStoneHull;
import de.zsgn.industrialscience.factory.tileentity.ITileEntityMultiBlockController;
import de.zsgn.industrialscience.factory.tileentity.TileEntityChimney;
import de.zsgn.industrialscience.factory.tileentity.TileEntityHatch;
import de.zsgn.industrialscience.factory.tileentity.TileEntityMultiBlock;
import de.zsgn.industrialscience.factory.tileentity.TileEntityMultiBlockFurnace;

public class FactoryModule{
    private Block blockStonehull;
    private Block blockIronhull;
    private Block blockStonehatch;
    private Block blockChimney;
    private Block blockControllerStonefurnace;
    public void init(FMLInitializationEvent event) {
        blockIronhull=new BlockIronHull();
        blockStonehull=new BlockStoneHull();
        blockStonehatch=new BlockStoneHatch();
        blockChimney = new BlockChimney();
        blockControllerStonefurnace=new BlockStoneFurnaceController();
        
        GameRegistry.registerBlock(blockControllerStonefurnace, blockControllerStonefurnace.getUnlocalizedName().substring(5));
        GameRegistry.registerBlock(blockStonehull, blockStonehull.getUnlocalizedName().substring(5));
        GameRegistry.registerBlock(blockIronhull, blockIronhull.getUnlocalizedName().substring(5));
        GameRegistry.registerBlock(blockChimney, blockChimney.getUnlocalizedName().substring(5));
        GameRegistry.registerBlock(blockStonehatch, blockStonehatch.getUnlocalizedName().substring(5));
        
        GameRegistry.registerTileEntity(TileEntityMultiBlockFurnace.class, "tile.multiblockfurnace");
        GameRegistry.registerTileEntity(TileEntityMultiBlock.class, "tile.multiblock");
        GameRegistry.registerTileEntity(ITileEntityMultiBlockController.class, "tile.multiblockcontroller");
        GameRegistry.registerTileEntity(TileEntityHatch.class, "tile.hatch");
        GameRegistry.registerTileEntity(TileEntityChimney.class,"tile.chimney");
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

}
