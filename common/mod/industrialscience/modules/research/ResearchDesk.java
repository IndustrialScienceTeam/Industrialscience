package mod.industrialscience.modules.research;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
public class ResearchDesk extends Block {
	public ResearchDesk(int id) {
		super(id, Material.wood);
	}
	public void func_94332_a(IconRegister iconRegister)
	{
	         this.field_94336_cN = iconRegister.func_94245_a("industrialscience:notexture");
	}

}
