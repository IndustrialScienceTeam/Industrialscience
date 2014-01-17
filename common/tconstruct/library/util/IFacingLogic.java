package tconstruct.library.util;

import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.common.ForgeDirection;

public interface IFacingLogic
{
    public ForgeDirection getForgeDirection ();

    public byte getRenderDirection ();

    @Deprecated
    public void setDirection (float yaw, float pitch, EntityLivingBase player);

    @Deprecated
    public void setDirection (int side);

    /** This will be added next version
    * public void setDirection(int side, float yaw, float pitch, EntityLivingBase player); */
}