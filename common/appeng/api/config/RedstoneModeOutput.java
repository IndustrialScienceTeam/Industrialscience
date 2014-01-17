package appeng.api.config;


// do not change names..
public enum RedstoneModeOutput implements IConfigEnum  {
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