package appeng.api.me.tiles;

import appeng.api.WorldCoord;
import appeng.api.me.util.IAssemblerCluster;

public interface IAssemblerMB {

    void calculateMultiblock( long instanceCalc );
    public IAssemblerCluster getCluster();
    /**
     *  Do this:
     *  	return new WorldCoord( TileEntity.xCoord, TileEntity.yCoord, TileEntity.zCoord );
     */
    public WorldCoord getLocation();
	public boolean isComplete();
	
	public long markViewed(long inst);
	public void updateStatus( IAssemblerCluster ac );
    
}
