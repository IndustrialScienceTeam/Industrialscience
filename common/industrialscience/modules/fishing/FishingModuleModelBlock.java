package industrialscience.modules.fishing;

import industrialscience.blocksystem.ISModuleModelBlock;
import industrialscience.blocksystem.ModelISBlock;
import industrialscience.modules.fishing.Blocks.TrapCraftingTableISBlock;
import net.minecraft.block.material.Material;

public class FishingModuleModelBlock extends ISModuleModelBlock {

    

    public static final int TRAPCRAFTINGBLOCKMETAID = 0;

	public FishingModuleModelBlock(int par1, String prefix) {
		super(par1, Material.iron, prefix);
		blocks=new ModelISBlock[1];
		blocks[TRAPCRAFTINGBLOCKMETAID]=new TrapCraftingTableISBlock();
	}

}
