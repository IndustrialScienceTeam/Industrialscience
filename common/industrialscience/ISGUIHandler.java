package industrialscience;

import industrialscience.modules.ISAbstractModule;

import java.util.Collection;
import java.util.Iterator;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class ISGUIHandler implements IGuiHandler {
    Collection<ISAbstractModule> modules;

    public ISGUIHandler(Collection<ISAbstractModule> collection) {
        this.modules = collection;
    }

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world,
            int x, int y, int z) {
        if (ID == 0)
            for (Iterator<ISAbstractModule> iterator = modules.iterator(); iterator
                    .hasNext();) {
                ISAbstractModule module = iterator.next();
                if ((world.getBlockId(x, y, z) == module.getNormalBlockID())|world.getBlockId(x, y, z) == module.getModelBlockID()) {
                    return module
                            .getServerGUIElement(0, player, world, x, y, z);
                }
            }
        else {
            return IndustrialScience.MODULES[ID & 7].getServerGUIElement(
                    ID >> 3, player, world, x, y, z);

        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world,
            int x, int y, int z) {
        if (ID == 0)
            for (Iterator<ISAbstractModule> iterator = modules.iterator(); iterator
                    .hasNext();) {
                ISAbstractModule module = iterator.next();
                if ((world.getBlockId(x, y, z) == module.getNormalBlockID())|world.getBlockId(x, y, z) == module.getModelBlockID()) {
                    return module
                            .getClientGUIElement(0, player, world, x, y, z);
                }
            }
        else {
            return IndustrialScience.MODULES[ID & 7].getClientGUIElement(
                    ID >> 3, player, world, x, y, z);
        }
        return null;
    }

}
