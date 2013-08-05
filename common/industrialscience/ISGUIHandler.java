package industrialscience;

import industrialscience.modules.ISAbstractModule;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class ISGUIHandler implements IGuiHandler {
	Collection<ISAbstractModule> modules;
	public ISGUIHandler(Collection<ISAbstractModule> collection) {
		this.modules=collection;
	}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		for (Iterator iterator = modules.iterator(); iterator.hasNext();) {
			ISAbstractModule module = (ISAbstractModule) iterator.next();
			if(world.getBlockId(x, y, z)==module.getBlockID()){
				return module.getServerGUIElement(world.getBlockMetadata(x, y, z),player,world,x,y,z);
			}
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		for (Iterator iterator = modules.iterator(); iterator.hasNext();) {
			ISAbstractModule module = (ISAbstractModule) iterator.next();
			if(world.getBlockId(x, y, z)==module.getBlockID()){
				return module.getClientGUIElement(world.getBlockMetadata(x, y, z),player,world,x,y,z);
			}
		}
		return null;
	}

}
