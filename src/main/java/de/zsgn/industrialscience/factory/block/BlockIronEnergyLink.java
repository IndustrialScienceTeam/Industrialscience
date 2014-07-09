package de.zsgn.industrialscience.factory.block;

import de.zsgn.industrialscience.IndustrialScience;
import de.zsgn.industrialscience.factory.tileentity.TileEntityEnergyLink;
import de.zsgn.industrialscience.factory.tileentity.TileEntityMultiBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import ic2.api.energy.tile.IEnergyAcceptor;
import ic2.api.energy.tile.IEnergySink;

public class BlockIronEnergyLink extends BlockIronHull{

    public BlockIronEnergyLink() {
        super();
        this.setBlockName("ironenergylink");
        this.setHardness(3.0F);
        textureName = IndustrialScience.MODID + ":"
                + this.getUnlocalizedName().substring(5);
    }
    @Override
    public TileEntity createNewTileEntity(World world, int blockMetadata) {
        return new TileEntityEnergyLink();
    }


}
