package industrialscience.modules;

import java.util.Hashtable;
import java.util.logging.Level;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class MiningModule extends ISAbstractModule {

    @Override
    public void load() {
        logger.log(Level.INFO, "LOADING");

    }

    @Override
    public void init() {
        logger.log(Level.INFO, "INIT");
        initCreativeTab();
        setPrefix("MINING_INDUSTRIALSCIENCE");

    }

    @Override
    public Hashtable<String, Integer> getNeededBlockIDs() {
        return new Hashtable<String, Integer>();
    }

    @Override
    public Hashtable<String, Integer> getNeededItemIDs() {
        return new Hashtable<String, Integer>();
    }

    @Override
    public void postinit() {
        logger.log(Level.INFO, "POST-INIT");

    }

    @Override
    public String getName() {
        return "Mining Module";
    }

    @Override
    public ItemStack getIconitemstack() {
        return new ItemStack(Item.pickaxeDiamond);
    }

    @Override
    public IGuiHandler getGUIHandler() {
        return new IGuiHandler() {
            
            @Override
            public Object getServerGuiElement(int ID, EntityPlayer player, World world,
                    int x, int y, int z) {
                // TODO Auto-generated method stub
                
                return null;
            }
            
            @Override
            public Object getClientGuiElement(int ID, EntityPlayer player, World world,
                    int x, int y, int z) {
                // TODO Auto-generated method stub
                return null;
            }
        };
    }

}
