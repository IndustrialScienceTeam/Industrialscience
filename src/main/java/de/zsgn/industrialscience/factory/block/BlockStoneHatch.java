package de.zsgn.industrialscience.factory.block;

import de.zsgn.industrialscience.IndustrialScience;
import de.zsgn.industrialscience.factory.tileentity.TileEntityHatch;
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
        return new TileEntityHatch();
    }

}
