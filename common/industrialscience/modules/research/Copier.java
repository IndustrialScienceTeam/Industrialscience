package industrialscience.modules.research;

import industrialscience.modules.ISAbstractModule;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class Copier extends BlockContainer {
    private Icon side;
    private Icon bottom;
    private Icon top;

    public Copier(int id) {
        super(id, Material.wood);
        setUnlocalizedName("Research Copier");
        LanguageRegistry.addName(this, "Research Copier");
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
        top = par1IconRegister.registerIcon("wood_jungle");
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
    public TileEntity createNewTileEntity(World world) {
        return new CopierTile();
    }

    @Override
    public boolean hasTileEntity(int metadata) {
        return true;
    }

}
