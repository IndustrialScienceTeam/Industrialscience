package ic2.api.recipe;

import java.util.Map;

import net.minecraftforge.fluids.Fluid;


public interface ISemiFluidFuelManager extends ILiquidAcceptManager {
	public static class BurnProperty {
		public final int amount;

		public final double power;
		public BurnProperty(int amount1, double power1) {
			this.amount = amount1;
			this.power = power1;
		}
	}

	/**
	 * Add a new fluid to the semi fluid generator.
	 * 
	 * @param fluidName the fluid to burn
	 * @param amount amount of fluid to consume per tick
	 * @param power amount of energy generated per tick
	 */
	void addFluid(String fluidName, int amount, double power);

	Map<String, BurnProperty> getBurnProperties();


	BurnProperty getBurnProperty(Fluid fluid);
}
