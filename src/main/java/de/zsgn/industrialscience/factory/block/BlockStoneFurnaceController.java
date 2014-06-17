package de.zsgn.industrialscience.factory.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import de.zsgn.industrialscience.IndustrialScience;
import de.zsgn.industrialscience.MultiBlockStructure;
import de.zsgn.industrialscience.factory.tileentity.TileEntityStoneFurnace;


public class BlockStoneFurnaceController extends IBlockMultiBlockController {
    public BlockStoneFurnaceController() {
        super(Material.rock, MultiBlockStructure.FURNACE_TIER1,IndustrialScience.MODID + ":" + IndustrialScience.getInstance().getFactoryModule().getBlockStonehull().getUnlocalizedName().substring(5));
        ValidBlocks=new Block[]{this,IndustrialScience.getInstance().getFactoryModule().getBlockStonehull()};
        this.setCreativeTab(IndustrialScience.getInstance().getCreativetab());
        setBlockName("tier1stonefurnace");
        setHardness(3.0F);
    }

    @Override
    public TileEntity createNewTileEntity(World var1, int var2) {
        return new TileEntityStoneFurnace();
    }

}
