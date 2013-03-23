package mod.industrialscience;

import mod.industrialscience.modules.research.ResearchDeskContainer;
import mod.industrialscience.modules.research.ResearchDeskTile;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class CommonProxy implements IGuiHandler {

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		TileEntity tile_entity = world.getBlockTileEntity(x, y, z);
        
        if(tile_entity instanceof ResearchDeskTile){
       
                return new ResearchDeskContainer((ResearchDeskTile)tile_entity, player.inventory);
        }
       
       
        return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		return null;

	}
}
