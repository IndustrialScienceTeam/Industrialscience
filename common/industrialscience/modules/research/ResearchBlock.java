package industrialscience.modules.research;
import industrialscience.modules.ISAbstractModule;

import java.util.List;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

// Thx to iron chest(cpw) for the idea of this system. You rock.
public class ResearchBlock extends BlockContainer {

    public ResearchBlock(int id) {
        super(id,Material.wood);
        setCreativeTab(ISAbstractModule.getCreativeTab());
        setUnlocalizedName("Research_module_block");
    }
    @Override
    public boolean isOpaqueCube(){
        return false;
    }
    @Override
    public void registerIcons(IconRegister par1IconRegister) {
       ResearchBlockTyp.registerIcons(par1IconRegister);
    }
    @Override
    public TileEntity createNewTileEntity(World world) {
     return null;
    }
    @Override
    public TileEntity createTileEntity(World world, int metadata)
    {
        return ResearchBlockTyp.getEntity(metadata);
    }
    @Override
    public boolean hasTileEntity(int metadata) {
        return true;
    }
    @Override
    public Icon getIcon(int i, int j) {
        return ResearchBlockTyp.getIcon(i,j);
        }
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List)
    {
        for (int i = 0; i < ResearchBlockTyp.values().length; i++) {
            par3List.add(new ItemStack(par1, 1, i));
        }
        
    }
    
    }
