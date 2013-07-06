package industrialscience.modules;

import java.util.Hashtable;
import java.util.logging.Level;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class MiningModule extends ISAbstractModule {

    public MiningModule(int blockID) {
        super(NeededItemIDs(), blockID, "mining", "IndustrialScience Mining",
                GUIHandler());
    }

    @Override
    public void load() {
        logger.log(Level.INFO, "LOADING");

    }

    @Override
    public void init() {
        initCreativeTab(new ItemStack(Item.pickaxeDiamond));
        logger.log(Level.INFO, "INIT");

    }

    public static Hashtable<String, Integer> NeededItemIDs() {
        return new Hashtable<String, Integer>();
    }

    @Override
    public void postinit() {
        logger.log(Level.INFO, "POST-INIT");

    }

    public static IGuiHandler GUIHandler() {
        return new IGuiHandler() {

            @Override
            public Object getServerGuiElement(int ID, EntityPlayer player,
                    World world, int x, int y, int z) {
                // TODO Auto-generated method stub

                return null;
            }

            @Override
            public Object getClientGuiElement(int ID, EntityPlayer player,
                    World world, int x, int y, int z) {
                // TODO Auto-generated method stub
                return null;
            }
        };
    }

}
