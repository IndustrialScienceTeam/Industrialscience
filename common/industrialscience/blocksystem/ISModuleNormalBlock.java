package industrialscience.blocksystem;

import java.util.List;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.Mod.Metadata;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public abstract class ISModuleNormalBlock extends ISModuleBlock {
	public ISModuleNormalBlock(int par1, Material par2Material, String prefix) {
		super(par1, par2Material, prefix);
	}
public void registerIcons(IconRegister par1IconRegister) {
	for(ISBlock b:blocks){
		((NormalISBlock)b).registerIcons(par1IconRegister);
	}
}
public Icon getIcon(int side, int metadata){
	return ((NormalISBlock)blocks[metadata]).getIcon(side);
}
}
