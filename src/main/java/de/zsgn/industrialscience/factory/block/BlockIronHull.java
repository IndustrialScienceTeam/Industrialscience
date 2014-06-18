package de.zsgn.industrialscience.factory.block;

import net.minecraft.block.material.Material;
import de.zsgn.industrialscience.IndustrialScience;
import de.zsgn.industrialscience.factory.block.IBlockMultiblockHull;

public class BlockIronHull extends IBlockMultiblockHull {

    public BlockIronHull() {
        super(Material.iron);
        this.setBlockName("ironhull");
        this.setHardness(3.0F);
        this.textureName=IndustrialScience.MODID + ":" + this.getUnlocalizedName().substring(5);
    }

}
