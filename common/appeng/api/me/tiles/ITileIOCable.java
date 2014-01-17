package appeng.api.me.tiles;

import net.minecraft.inventory.IInventory;

/**
 * used to allow you to inspect, and configure a Export / Import / Storage Bus
 * Export and Import buses also implement IConfigureableTile, which may be of interest.
 */
public interface ITileIOCable {
	
	public enum Version {
		Basic, Fuzzy, Precision
	};
	
	/**
	 * Returns an IInventory that represents the configuration.
	 */
	IInventory getConfiguration();
	
	/**
	 * Returns the name of the bus..
	 */
	String getName();
	
	/**
	 * returns one of the above, to indicate which version of the bus it is.
	 */
	Version getVersion();
	
}
