package appeng.api.config;


public enum PowerUnits implements IConfigEnum<PowerUnits> {
	AE,
	EU,
	MJ,
	UE;

	@Override
	public String getName() {
		return "PowerUnits";
	}

	@Override
	public IConfigEnum[] getValues() {
		return values();
	}
}