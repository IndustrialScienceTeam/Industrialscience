package de.zsgn.industrialscience.block.multiblock;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import de.zsgn.industrialscience.IndustrialScience;
import de.zsgn.industrialscience.tileentity.multiblock.TileEntityMultiBlock;

public class BlockChimney extends BlockContainer {
       
        public BlockChimney() {
            super(Material.iron);
            this.setCreativeTab(IndustrialScience.getInstance().getCreativetab());
            setBlockName("chimney");
            setHardness(2.0F);
        }
        
        @Override
        public TileEntity createNewTileEntity(World world,
                int blockMetadata) {
            return new TileEntityMultiBlock(){};
        }
        public boolean onBlockActivated(World world, int x,
                int y, int z, EntityPlayer player,
                int side, float xOffset, float yOffset,
                float zOffset) {
            if(!world.isRemote&&world.getTileEntity(x, y, z)instanceof TileEntityMultiBlock && (((TileEntityMultiBlock)world.getTileEntity(x, y, z)).isActivePart())){
               player.addChatComponentMessage(new ChatComponentText(Integer.toString(((TileEntityMultiBlock)world.getTileEntity(x, y, z)).getMasterx())));
               return true;
            }
            return false;
        }
        @Override
        public void breakBlock(World world, int x, int y,
                int z, Block block, int meta) {
            if(!world.isRemote&&world.getTileEntity(x, y, z)instanceof TileEntityMultiBlock && (((TileEntityMultiBlock)world.getTileEntity(x, y, z)).isActivePart())){
                ((TileEntityMultiBlock)world.getTileEntity(x, y, z)).destroyStructure();
             }
        }
}
