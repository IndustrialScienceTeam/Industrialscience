package appeng.api.me.util;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import appeng.api.IAEItemStack;
import appeng.api.TileRef;
import appeng.api.exceptions.AppEngTileMissingException;
import appeng.api.me.tiles.IGridMachine;
import appeng.api.me.tiles.IGridTileEntity;
import appeng.api.me.tiles.IPushable;

/**
 * Lets you access network related features. You will mostly care about "getCellArray()" which returns the IMEInventory for the entire network...
 */
public interface IGridInterface
{
	/**
	 *  add which users should be notified of crafting queue updates.
	 * @param p
	 */
	void addCraftingPlayer(EntityPlayer p);
	
	/**
	 *  add which users should be notified of terminal updates.
	 * @param p
	 */
	void addViewingPlayer(EntityPlayer p);
	
	/**
	 * opens the crafting gui.
	 * @param pmp
	 * @param gte
	 * @param s
	 * @throws AppEngTileMissingException
	 */
	public void craftGui( EntityPlayerMP pmp, IGridTileEntity gte, ItemStack s ) throws AppEngTileMissingException;
	
    /**
	 * deprecated version of creaftingRequest.
	 * @param what
	 * @return
	 * @throws AppEngTileMissingException
	 */
	public ICraftRequest craftingRequest( ItemStack what ) throws AppEngTileMissingException;
    
    /**
	 * Issue a new crafting request.
	 * @param whatToCraft
	 * @param showInMonitor
	 * @param enableRecursion
	 * @return the ICraftRequest
	 * @throws AppEngTileMissingException
	 */
	public ICraftRequest craftingRequest( ItemStack whatToCraft, boolean showInMonitor, boolean enableRecursion ) throws AppEngTileMissingException;
    
    @Override
	public boolean equals(Object obj);
	
	/**
	 * return a cache by its identifier ( the number you get when you register your cache with AE )
	 * @param id
	 * @return
	 */
	public IGridCache getCacheByID( int id );
	
	/**
     *  returns a single IMEInventory that represents the entire networks.
     * @return
     */
    public IMEInventoryHandler getCellArray();
	
    /**
	 * aquire the controller tile entity.
	 * @return
	 */
	public TileEntity getController();
    
    /**
	 * craftable items
	 * @return
	 */
	IMEInventoryHandler getCraftableArray();
    
    /**
     *  returns a single IMEInventory that represents the entire network, and all crafting available.
     * @return
     */
    public IMEInventoryHandler getFullCellArray();
    
	/**
	 * get a numeric id for this grid, used internally for cable animations, and not much else.
	 * @return
	 */
	public int getGridIndex();
	
	/**
     * returns a list of all machines on the grid.
     * @return
     */
    List< TileRef<IGridMachine> > getMachines();
	
	/**
	 * Potential future use, for now just returns the network encryption key.
	 * @return
	 */
	public String getName();
	
	/**
	 * Try to find the pattern for the item in question.
	 * @param req
	 * @return
	 */
	public IAssemblerPattern getPatternFor(ItemStack req);
	
	/**
	 *  Reports previous 20 ticks avg of energy usage.
	 * @return
	 */
	public float getPowerUsageAvg();
	
	/**
	 * is the grid valid?
	 * @return
	 */
	public boolean isValid();
	
	/**
	 * Inform the network that these items were added to storage ( don't do this for items added via the grid, it already knows about those. )
	 * @param vo
	 */
	public void notifyAddItems(IAEItemStack vo);
	
	/**
	 * Inform the netowrk that these items were removed from storage ( don't do this for items remove though the grid, it already knows about those. )
	 * @param removed
	 */
	public void notifyExtractItems(IAEItemStack removed);
	
	/**
	 * creates a crafting job to push the item out of IPushable, and to enable crafting to acocomplish this goal.
	 * @param willAdd
	 * @param out
	 * @param allowCrafting
	 * @return
	 */
	ICraftRequest pushRequest( ItemStack willAdd, IPushable out, boolean allowCrafting );

	/**
	 * returns energy to the system to prevent endless energy sinks.
	 */
	void refundMEEnergy( float use, String for_what );

	/**
     * Updates the interface requested, used internally
     * @param te
     */
    public void requestUpdate( IGridTileEntity te );

	/**
	 * tell AE to re-examin items in the waiting list.
	 */
	void resetWaitingQueue();

	/**
	 *  remove which users should be notified of crafting queue updates.
	 * @param p
	 */
	void rmvCraftingPlayer(EntityPlayer p);
	
	/**
	 *  remove which users should be notified of terminal updates.
	 * @param p
	 */
	void rmvViewingPlayer(EntityPlayer p);

	/**
	 * inform the network that energy was transfered between two nodes on the network,
	 * this will be used to calculates future information, but also causes the network
	 * to flash presently.
	 * 
	 * this will automatically be handled by the standard extractItem / addItem API in the future, only
	 * Processes that skip the vanilla AE storage system should use this.
	 * 
	 * @param a
	 * @param b
	 * @param amt
	 */
	void signalEnergyTransfer(IGridTileEntity a, IGridTileEntity b, float amt);
	
	/**
	 * Inform the network that power costs have changed.
	 */
	public void triggerPowerUpdate();
	
	/**
     * Labeled version for debugging...
     */
	boolean useMEEnergy(float use, String for_what);
	
	/**
     *  this is used for standard items, anything else just use useMEEnergy.
     * @param items
     * @param multipler
     * @return
     */
	int usePowerForAddition(int items, int multipler);

	/**
	 * create a waiting job.
	 * @param what
	 * @return
	 */
	ICraftRequest waitingRequest(ItemStack what);

}
