package industrialscience;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public interface ISBlockInterface {
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9);
    public void breakBlock(World world, int x, int y, int z, int par5, int par6);
}
