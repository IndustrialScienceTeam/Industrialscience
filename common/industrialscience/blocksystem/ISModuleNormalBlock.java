package industrialscience.blocksystem;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;

public abstract class ISModuleNormalBlock extends ISModuleBlock {
	public ISModuleNormalBlock(int par1, Material par2Material, String prefix) {
		super(par1, par2Material, prefix);
	}
@Override
public Icon getIcon(int side, int metadata){
	return ((NormalISBlock)blocks[metadata]).getIcon(side);
}
@Override
public void registerIcons(IconRegister par1IconRegister) {
	for(ISBlock b:blocks){
		((NormalISBlock)b).registerIcons(par1IconRegister);
	}
}
}
