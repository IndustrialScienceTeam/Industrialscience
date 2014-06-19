package de.zsgn.industrialscience.factory.block;

import de.zsgn.industrialscience.IndustrialScience;
import de.zsgn.industrialscience.factory.tileentity.TileEntityHatch;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockStoneHatch extends BlockStoneHull {

    public BlockStoneHatch() {
        super();
        this.setBlockName("cobblestonehatch");
        this.textureName=IndustrialScience.MODID + ":" + this.getUnlocalizedName().substring(5);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int blockMetadata) {
        return new TileEntityHatch(true);
    }
    @Override
    public boolean onBlockActivated(World world, int x,
            int y, int z, EntityPlayer player,
            int side, float xOffset, float yOffset,
            float zOffset) {
        if(world.getTileEntity(x, y, z) instanceof TileEntityHatch){
            TileEntityHatch hatch=(TileEntityHatch)world.getTileEntity(x, y, z);
            if(hatch.isItemInterface()){
                if(player.inventory.getCurrentItem() != null){
                    if(!world.isRemote){
                        
                    }
                    return true;
                }else {
                    return false;
                }
            }
        }
        return false;
    }

}
