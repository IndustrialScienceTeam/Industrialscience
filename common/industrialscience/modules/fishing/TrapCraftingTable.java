package industrialscience.modules.fishing;

import industrialscience.BlockUtils;

import java.util.Random;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class TrapCraftingTable extends BlockContainer {
    private Icon side;
    private Icon bottom;
    private Icon top;

    public TrapCraftingTable(int id) {
        super(id, Material.wood);
        setUnlocalizedName("Trap Workbench");
        LanguageRegistry.addName(this, "Trap Workbench");
        // setCreativeTab(FishingModule.getCreativeTab());
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
    public void breakBlock(World world, int x, int y, int z, int i, int j) {
        BlockUtils.dropItems(world, x, y, z);
        super.breakBlock(world, x, y, z, i, j);
    }


    @Override
    public TileEntity createNewTileEntity(World world) {
        // TODO Auto-generated method stub
        return null;
    }

}
