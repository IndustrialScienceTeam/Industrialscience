package appeng.api.config;


public enum ActionItems implements IConfigEnum<ActionItems> {
	Close,
	Wrench;

	@Override
	public String getName() {
		return "FuzzyMode";
	}

	@Override
	public IConfigEnum[] getValues() {
		return values();
	}
}