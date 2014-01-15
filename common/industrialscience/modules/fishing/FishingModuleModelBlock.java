package industrialscience.modules.fishing;

import industrialscience.BlockUtils;
import industrialscience.blocksystem.ModelISBlock;
import industrialscience.blocksystem.ISModuleModelBlock;
import industrialscience.modules.fishing.Blocks.TrapCraftingTableISBlock;
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

public class FishingModuleModelBlock extends ISModuleModelBlock {

    

    public static final int TRAPCRAFTINGBLOCKMETAID = 0;

	public FishingModuleModelBlock(int par1, String prefix) {
		super(par1, Material.iron, prefix);
		blocks=new ModelISBlock[1];
		blocks[TRAPCRAFTINGBLOCKMETAID]=new TrapCraftingTableISBlock();
	}

}
