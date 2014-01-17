package ic2.api.energy.prefab;

import ic2.api.energy.EnergyNet;
import ic2.api.energy.event.EnergyTileLoadEvent;
import ic2.api.energy.event.EnergyTileUnloadEvent;
import ic2.api.energy.tile.IEnergySource;
import ic2.api.info.Info;
import ic2.api.item.ElectricItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.FMLCommonHandler;

/**
 * BasicSource is a simple adapter to provide an ic2 energy source.
 * 
 * It's designed to be attached to a tile entity as a delegate. Functionally BasicSource acts as a
 * one-time configurable output energy buffer, thus providing a common use case for generators.
 * 
 * Sub-classing BasicSource instead of using it as a delegate works as well, but isn't recommended.
 * The delegate can be extended with additional functionality through a sub class though.
 * 
 * The constraints set by BasicSource like the strict tank-like energy buffering should provide a
 * more easy to use and stable interface than using IEnergySource directly while aiming for
 * optimal performance.
 * 
 * Using BasicSource involves the following steps:
 * - create a BasicSource instance in your TileEntity, typically in a field
 * - forward invalidate, onChunkUnload, readFromNBT, writeToNBT and updateEntity to the BasicSource
 *   instance.
 *   If you have other means of determining when the tile entity is fully loaded, notify onLoaded
 *   that way instead of forwarding updateEntity.
 * - call addEnergy whenever appropriate, using getFreeCapacity may determine if e.g. the generator
 *   should run
 * - optionally use getEnergyStored to display the output buffer charge level
 * - optionally use setEnergyStored to sync the stored energy to the client (e.g. in the Container)
 *
 * Example implementation code:
 * @code{.java}
 * public class SomeTileEntity extends TileEntity {
 *     // new basic energy source, 1000 EU buffer, tier 1 (32 EU/t, LV)
 *     private BasicSource ic2EnergySource = new BasicSource(this, 1000, 1);
 * 
 *     @Override
 *     public void invalidate() {
 *         ic2EnergySource.invalidate(); // notify the energy source
 *         ...
 *         super.invalidate(); // this is important for mc!
 *     }
 * 
 *     @Override
 *     public void onChunkUnload() {
 *         ic2EnergySource.onChunkUnload(); // notify the energy source
 *         ...
 *     }
 * 
 *     @Override
 *     public void readFromNBT(NBTTagCompound tag) {
 *         super.readFromNBT(tag);
 * 
 *         ic2EnergySource.readFromNBT(tag);
 *         ...
 *     }
 * 
 *     @Override
 *     public void writeToNBT(NBTTagCompound tag) {
 *         super.writeToNBT(tag);
 * 
 *         ic2EnergySource.writeToNBT(tag);
 *         ...
 *     }
 * 
 *     @Override
 *     public void updateEntity() {
 *         ic2EnergySource.updateEntity(); // notify the energy source
 *         ...
 *         ic2EnergySource.addEnergy(5); // add 5 eu to the source's buffer this tick
 *         ...
 *     }
 * 
 *     ...
 * }
 * @endcode
 */
public class BasicSource extends TileEntity implements IEnergySource {

	// **********************************
	// *** Methods for use by the mod ***
	// **********************************

	protected boolean addedToEnet;

	// in-world te forwards	>>

	protected int capacity;

	protected double energyStored;

	public final TileEntity parent;

	protected int tier;

	/**
	 * Constructor for a new BasicSource delegate.
	 * 
	 * @param parent1 Base TileEntity represented by this energy source.
	 * @param capacity1 Maximum amount of eu to store.
	 * @param tier1 IC2 tier, 1=LV, 2=MV, ...
	 */
	public BasicSource(TileEntity parent1, int capacity1, int tier1) {
		int power = EnergyNet.instance.getPowerFromTier(tier1);

		this.parent = parent1;
		this.capacity = capacity1 < power ? power : capacity1;
		this.tier = tier1;
	}

	/**
	 * Add some energy to the output buffer.
	 * 
	 * @param amount maximum amount of energy to add
	 * @return amount added/used, NOT remaining
	 */
	public double addEnergy(double amount) {
		if (FMLCommonHandler.instance().getEffectiveSide().isClient()) return 0;
		if (amount > capacity - energyStored) amount = capacity - energyStored;

		energyStored += amount;

		return amount;
	}

	// << in-world te forwards
	// methods for using this adapter >>

	/**
	 * Charge the supplied ItemStack from this source's energy buffer.
	 * 
	 * @param stack ItemStack to charge (null is ignored)
	 * @return true if energy was transferred
	 */
	public boolean charge(ItemStack stack) {
		if (stack == null || !Info.isIc2Available()) return false;

		int amount = ElectricItem.manager.charge(stack, (int) energyStored, tier, false, false);

		energyStored -= amount;

		return amount > 0;
	}

	@Override
	public void drawEnergy(double amount) {
		energyStored -= amount;
	}

	@Override
	public boolean emitsEnergyTo(TileEntity receiver, ForgeDirection direction) {
		return true;
	}

	/**
	 * Get the maximum amount of energy this source can hold in its buffer.
	 * 
	 * @return Capacity in EU.
	 */
	public int getCapacity() {
		return capacity;
	}


	/**
	 * Determine the energy stored in the source's output buffer.
	 * 
	 * @return amount in EU
	 */
	public double getEnergyStored() {
		return energyStored;
	}

	/**
	 * Determine the free capacity in the source's output buffer.
	 * 
	 * @return amount in EU
	 */
	public double getFreeCapacity() {
		return capacity - energyStored;
	}

	@Override
	public double getOfferedEnergy() {
		int power = EnergyNet.instance.getPowerFromTier(tier);

		if (energyStored >= power) {
			return power;
		}
		return 0;
	}

	/**
	 * Get the IC2 energy tier for this source.
	 * 
	 * @return IC2 Tier.
	 */
	public int getTier() {
		return tier;
	}

	/**
	 * Forward for the base TileEntity's invalidate(), used for destroying the energy net link.
	 * Both invalidate and onChunkUnload have to be used.
	 */
	@Override
	public void invalidate() {
		super.invalidate();

		onChunkUnload();
	}

	// << methods for using this adapter

	// backwards compatibility (ignore these) >>

	/**
	 * Forward for the base TileEntity's onChunkUnload(), used for destroying the energy net link.
	 * Both invalidate and onChunkUnload have to be used.
	 */
	@Override
	public void onChunkUnload() {
		if (addedToEnet &&
				Info.isIc2Available()) {
			MinecraftForge.EVENT_BUS.post(new EnergyTileUnloadEvent(this));

			addedToEnet = false;
		}
	}

	@Deprecated
	public void onInvalidate() {
		invalidate();
	}

	/**
	 * Notification that the base TileEntity finished loading, for advanced uses.
	 * Either updateEntity or onLoaded have to be used.
	 */
	public void onLoaded() {
		if (!addedToEnet &&
				!FMLCommonHandler.instance().getEffectiveSide().isClient() &&
				Info.isIc2Available()) {
			worldObj = parent.worldObj;
			xCoord = parent.xCoord;
			yCoord = parent.yCoord;
			zCoord = parent.zCoord;

			MinecraftForge.EVENT_BUS.post(new EnergyTileLoadEvent(this));

			addedToEnet = true;
		}
	}

	@Deprecated
	public void onOnChunkUnload() {
		onChunkUnload();
	}

	@Deprecated
	public void onReadFromNbt(NBTTagCompound tag) {
		readFromNBT(tag);
	}

	// << backwards compatibility

	// ******************************
	// *** Methods for use by ic2 ***
	// ******************************

	// energy net interface >>

	@Deprecated
	public void onUpdateEntity() {
		updateEntity();
	}

	@Deprecated
	public void onWriteToNbt(NBTTagCompound tag) {
		writeToNBT(tag);
	}

	/**
	 * Forward for the base TileEntity's readFromNBT(), used for loading the state.
	 * 
	 * @param tag Compound tag as supplied by TileEntity.readFromNBT()
	 */
	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);

		NBTTagCompound data = tag.getCompoundTag("IC2BasicSource");

		energyStored = data.getDouble("energy");
	}

	// << energy net interface


	/**
	 * Set the maximum amount of energy this source can hold in its buffer.
	 * 
	 * @param capacity1 Capacity in EU.
	 */
	public void setCapacity(int capacity1) {
		int power = EnergyNet.instance.getPowerFromTier(tier);

		if (capacity1 < power) capacity1 = power;

		this.capacity = capacity1;
	}

	/**
	 * Set the stored energy to the specified amount.
	 * 
	 * This is intended for server -> client synchronization, e.g. to display the stored energy in
	 * a GUI through getEnergyStored().
	 * 
	 * @param amount
	 */
	public void setEnergyStored(double amount) {
		energyStored = amount;
	}
	/**
	 * Set the IC2 energy tier for this source.
	 * 
	 * @param tier1 IC2 Tier.
	 */
	public void setTier(int tier1) {
		int power = EnergyNet.instance.getPowerFromTier(tier1);

		if (capacity < power) capacity = power;

		this.tier = tier1;
	}
	/**
	 * Forward for the base TileEntity's updateEntity(), used for creating the energy net link.
	 * Either updateEntity or onLoaded have to be used.
	 */
	@Override
	public void updateEntity() {
		if (!addedToEnet) onLoaded();
	}
	/**
	 * Forward for the base TileEntity's writeToNBT(), used for saving the state.
	 * 
	 * @param tag Compound tag as supplied by TileEntity.writeToNBT()
	 */
	@Override
	public void writeToNBT(NBTTagCompound tag) {
		try {
			super.writeToNBT(tag);
		} catch (RuntimeException e) {
			// happens if this is a delegate, ignore
		}

		NBTTagCompound data = new NBTTagCompound();

		data.setDouble("energy", energyStored);

		tag.setTag("IC2BasicSource", data);
	}
}
