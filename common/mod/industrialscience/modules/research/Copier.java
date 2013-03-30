package mod.industrialscience.modules.research;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public class Copier extends BlockContainer {
	private Icon side;
	private Icon bottom;
	private Icon top;
	protected Copier(int id) {
		super(id, Material.wood);
	}
    public boolean isOpaqueCube()
    {
        return false;
    }
	@Override 
	public void func_94332_a(IconRegister par1IconRegister)
	{
	this.side= par1IconRegister.func_94245_a("industrialscience:vannila_researchtable_sides");
	this.bottom = par1IconRegister.func_94245_a("industrialscience:vannila_researchtable_bottom"); 
	this.top = par1IconRegister.func_94245_a("wood");
	}

	public Icon getBlockTextureFromSideAndMetadata(int i, int j){
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
		return null;
	}
	public boolean hasTileEntity(int metadata)
	{
	    return true;
	}

}
