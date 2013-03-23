package mod.industrialscience.modules.research;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
public class ResearchDesk extends Block {
	public ResearchDesk(int id) {
		super(id, Material.wood);
	}
	public void func_94332_a(IconRegister iconRegister)
	{
	         this.field_94336_cN = iconRegister.func_94245_a("industrialscience:notexture");
	}
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int i, float a, float b, float c)
	{
	 //player.openGui(YourMod.instance, 0, world, x, y, z);
	 System.out.println(x);
	return true;
	}

}
