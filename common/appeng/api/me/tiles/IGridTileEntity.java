package appeng.api.me.tiles;

import net.minecraft.world.World;
import appeng.api.WorldCoord;
import appeng.api.me.util.IGridInterface;

/**
 * Basic ME Grid Interface, informs you of the Grid power status, and other details.
 */
public abstract interface IGridTileEntity
{
    /**
     * Return your last grid you got via setGrid.
     */
    public IGridInterface getGrid();
    
    /**
     *  Do this:
     *  	return new WorldCoord( TileEntity.xCoord, TileEntity.yCoord, TileEntity.zCoord );
     */
    public abstract WorldCoord getLocation();
    
    /**
     * Return worldObj
     */
	public World getWorld();
    
    /**
     * Yes if the device has a powered status.
     */
    public abstract boolean isPowered();
    
    /**
     * If your tile entity is valid return true.
     */
    public boolean isValid();
    
    /**
     * Informs you of your new grid, YOU MUST return this via getGrid.
     * Store for later.
     */
    public void setGrid(IGridInterface gi);
    
    /**
     * Informs you of either true or false.
     * if the power feed has stopped, or been continued, only called when changes happens, or when grid updates happen.
     */
    public abstract void setPowerStatus(boolean hasPower);
}
