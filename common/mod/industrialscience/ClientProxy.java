package mod.industrialscience;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class ClientProxy extends CommonProxy {
	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
	TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
	if (tileEntity != null)
	{
	switch(ID)
	{
	case 0: return null;
	}
	}
	return null;

	}
}
