package industrialscience.modules.research.frontend.GUI;

import industrialscience.modules.research.frontend.ResearchBook;
import industrialscience.modules.research.frontend.GUI.containers.ResearchBookContainer;
import industrialscience.modules.research.frontend.GUI.containers.ResearchDeskContainer;
import industrialscience.modules.research.frontend.TileEntities.ResearchDeskTile;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class GUIHandler implements IGuiHandler {
        protected final static int RESEARCHDESK_ID = 0;
        protected final static int RESEARCHBOOK_ID = 1;

        @Override
        public Object getServerGuiElement(int ID, EntityPlayer player, World world,
                int x, int y, int z) {
            switch (ID) {
                case RESEARCHDESK_ID:
                    return getResearchDeskContainer(player, world, x, y, z);
                case RESEARCHBOOK_ID:
                    return getResearchBookContainer(player, world, x, y, z);
                default:
                    break;
            }
            return null;
        }

        private Object getResearchBookContainer(EntityPlayer player, World world,
                int x, int y, int z) {
            return new ResearchBookContainer(
                    player.inventory,
                    player.inventory.mainInventory[player.inventory.currentItem].stackTagCompound
                            .getIntArray(ResearchBook.NBTNAME));
        }

        private Object getResearchDeskContainer(EntityPlayer player, World world,
                int x, int y, int z) {
            TileEntity tile_entity = world.getBlockTileEntity(x, y, z);
            if (tile_entity instanceof ResearchDeskTile)
                return new ResearchDeskContainer((ResearchDeskTile) tile_entity,
                        player.inventory);
            return null;
        }

        @Override
        public Object getClientGuiElement(int ID, EntityPlayer player, World world,
                int x, int y, int z) {
            return null;

        }
    }


