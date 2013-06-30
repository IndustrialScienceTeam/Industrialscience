package mod.industrialscience;

import mod.industrialscience.modules.research.ResearchDeskGUI;
import mod.industrialscience.modules.research.ResearchDeskTile;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class ClientProxy extends CommonProxy {
    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world,
            int x, int y, int z) {
        switch (ID) {
            case RESEARCHDESK_ID:
                return getResearchDeskGUI(player, world, x, y, z);
            case RESEARCHBOOK_ID:
                return getResearchBookGUI(player, world, x, y, z);
            default:
                break;
        }
        return null;
    }

    private Object getResearchBookGUI(EntityPlayer player, World world, int x,
            int y, int z) {
        // return new ResearchBookGUI(player.inventory,
        // player.inventory.mainInventory[player.inventory.currentItem].stackTagCompound.getIntArray(ResearchBook.NBTNAME));
        return null;
    }

    private Object getResearchDeskGUI(EntityPlayer player, World world, int x,
            int y, int z) {
        TileEntity tile_entity = world.getBlockTileEntity(x, y, z);
        if (tile_entity instanceof ResearchDeskTile)
            return new ResearchDeskGUI(player.inventory,
                    (ResearchDeskTile) tile_entity);
        return null;
    }
}
