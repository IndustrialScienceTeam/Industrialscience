package industrialscience.modules.research;

import industrialscience.IndustrialScience;
import industrialscience.modules.ISAbstractModule;

import java.util.Random;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class ResearchDesk extends BlockContainer {
    private Icon side;
    private Icon bottom;
    private Icon top;

    public ResearchDesk(int id) {
        super(id, Material.wood);
        setUnlocalizedName("Research Desk");
        LanguageRegistry.addName(this, "Research Desk");
        setCreativeTab(ISAbstractModule.getCreativeTab());
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public void registerIcons(IconRegister par1IconRegister) {
        side = par1IconRegister
                .registerIcon("industrialscience:vannila_researchtable_sides");
        bottom = par1IconRegister
                .registerIcon("industrialscience:vannila_researchtable_bottom");
        top = par1IconRegister.registerIcon("wood");
    }
    @Override
    public Icon getIcon(int i, int j) {
        switch (i) {
            case 0:
                return bottom;
            case 1:
                return top;
            default:
                return side;
        }
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z,
            EntityPlayer player, int i, float a, float b, float c) {
        TileEntity tilee = world.getBlockTileEntity(x, y, z);
        if (tilee == null || player.isSneaking())
            return false;

        player.openGui(IndustrialScience.instance, 0, world, x, y, z);
        return true;
    }

    @Override
    public boolean hasTileEntity(int metadata) {
        return true;
    }

    @Override
    public void breakBlock(World world, int x, int y, int z, int i, int j) {
        dropItems(world, x, y, z);
        super.breakBlock(world, x, y, z, i, j);
    }

    private void dropItems(World world, int x, int y, int z) {
        Random rand = new Random();

        TileEntity tile_entity = world.getBlockTileEntity(x, y, z);

        if (!(tile_entity instanceof IInventory))
            return;

        IInventory inventory = (IInventory) tile_entity;

        for (int i = 0; i < inventory.getSizeInventory(); i++) {
            ItemStack item = inventory.getStackInSlot(i);

            if (item != null && item.stackSize > 0) {
                float rx = rand.nextFloat() * 0.3F + 0.1F;
                float ry = rand.nextFloat() * 0.3F + 0.1F;
                float rz = rand.nextFloat() * 0.3F + 0.1F;

                EntityItem entity_item = new EntityItem(world, x + rx, y + ry,
                        z + rz, new ItemStack(item.itemID, item.stackSize,
                                item.getItemDamage()));

                if (item.hasTagCompound()) {
                    entity_item.writeToNBT((NBTTagCompound) item
                            .getTagCompound().copy());
                }

                float factor = 0.5F;

                entity_item.motionX = rand.nextGaussian() * factor;
                entity_item.motionY = rand.nextGaussian() * factor;
                entity_item.motionZ = rand.nextGaussian() * factor;
                world.spawnEntityInWorld(entity_item);
                item.stackSize = 0;
            }
        }
    }

    @Override
    public TileEntity createNewTileEntity(World world) {
        return new ResearchDeskTile();
    }

}
