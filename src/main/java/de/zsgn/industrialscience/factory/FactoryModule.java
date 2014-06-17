package de.zsgn.industrialscience.factory;

import net.minecraft.block.Block;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import de.zsgn.industrialscience.factory.block.BlockStoneHatch;
import de.zsgn.industrialscience.factory.block.MultiBlockControllerTier1StoneFurnace;
import de.zsgn.industrialscience.factory.block.MultiBlockHullIronHull;
import de.zsgn.industrialscience.factory.block.MultiBlockHullStoneHull;
import de.zsgn.industrialscience.factory.tileentity.TileEntityHatch;
import de.zsgn.industrialscience.factory.tileentity.TileEntityMultiBlock;
import de.zsgn.industrialscience.factory.tileentity.TileEntityMultiBlockController;
import de.zsgn.industrialscience.factory.tileentity.TileEntityTier1StoneFurnace;

public class FactoryModule{
    private Block blockStonehull;
    private Block blockIronhull;
    private Block blockStonehatch;
    private Block blockControllertier1stonefurnace;
    public void init(FMLInitializationEvent event) {
        blockIronhull=new MultiBlockHullIronHull();
        blockStonehull=new MultiBlockHullStoneHull();
        blockStonehatch=new BlockStoneHatch();
        blockControllertier1stonefurnace=new MultiBlockControllerTier1StoneFurnace();
        
        GameRegistry.registerBlock(blockControllertier1stonefurnace, blockControllertier1stonefurnace.getUnlocalizedName().substring(5));
        GameRegistry.registerBlock(blockStonehull, blockStonehull.getUnlocalizedName().substring(5));
        GameRegistry.registerBlock(blockIronhull, blockIronhull.getUnlocalizedName().substring(5));
        GameRegistry.registerBlock(blockStonehatch, blockStonehatch.getUnlocalizedName().substring(5));
        System.out.println(blockStonehull.getUnlocalizedName().substring(5));
        
        GameRegistry.registerTileEntity(TileEntityTier1StoneFurnace.class, blockControllertier1stonefurnace.getUnlocalizedName().substring(5));
        GameRegistry.registerTileEntity(TileEntityMultiBlock.class, "tile.multiblock");
        GameRegistry.registerTileEntity(TileEntityMultiBlockController.class, "tile.multiblockcontroller");
        GameRegistry.registerTileEntity(TileEntityHatch.class, "tile.hatch");

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
    public Block getBlockControllertier1stonefurnace() {
        return blockControllertier1stonefurnace;
    }

}
