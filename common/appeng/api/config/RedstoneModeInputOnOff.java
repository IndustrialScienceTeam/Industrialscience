package appeng.api.config;


// do not change names..
public enum RedstoneModeInputOnOff implements IConfigEnum  {
	Ignore,
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