package appeng.api.config;


// do not change names..
public enum RedstoneModeInput implements IConfigEnum  {
	Ignore,
	OnPulse,
	WhenOff,
	WhenOn;
	
	@Override
	public String getName() {
		return "RedstoneMode";
	}

	@Override
	public IConfigEnum[] getValues() {
		return values();
	}
}