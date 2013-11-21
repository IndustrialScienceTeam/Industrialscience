package industrialscience.modules.fishing.Blocks;

import industrialscience.BlockUtils;
import industrialscience.modules.fishing.TileEntities.AbstractFishTrapTileEntity;

import java.util.List;
import java.util.Random;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public class FishingModuleBlock extends BlockContainer {

    public FishingModuleBlock(int id) {
        super(id, Material.wood);
        setUnlocalizedName("FishingBlock");
        setTickRandomly(true);
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z,
            EntityPlayer player, int par6, float par7, float par8, float par9) {
        if (player.isSneaking())
            return false;
        return FishingModuleBlockType.values()[world.getBlockMetadata(x, y, z)]
                .activate(world, x, y, z, player, par6, par7, par8, par9);
    }

    @Override
    public void registerIcons(IconRegister par1IconRegister) {
        FishingModuleBlockType.registerIcons(par1IconRegister);
    }

    @Override
    public TileEntity createNewTileEntity(World world) {
        return null;
    }

    @Override
    public TileEntity createTileEntity(World world, int metadata) {
        return FishingModuleBlockType.getEntity(metadata);
    }

    @Override
    public boolean hasTileEntity(int metadata) {
        return true;
    }

    @Override
    public Icon getIcon(int i, int j) {
        return FishingModuleBlockType.getIcon(i, j);
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs,
            List par3List) {
        for (int i = 0; i < FishingModuleBlockType.values().length; i++) {
            par3List.add(new ItemStack(par1, 1, i));
        }

    }

    @Override
    public void breakBlock(World world, int x, int y, int z, int i, int j) {
        FishingModuleBlockType.values()[world.getBlockMetadata(x, y, z)].breakBlock(
                world, x, y, z, i, j);
        BlockUtils.dropItems(world, x, y, z);
        super.breakBlock(world, x, y, z, i, j);
    }

    @Override
    public void updateTick(World world, int x, int y, int z, Random random) {
        TileEntity tile = world.getBlockTileEntity(x, y, z);
        if (!(tile instanceof AbstractFishTrapTileEntity))
            return;
        AbstractFishTrapTileEntity fishtile = (AbstractFishTrapTileEntity) tile;
        fishtile.doUpdateTick(world, x, y, z, random);

    }

}
