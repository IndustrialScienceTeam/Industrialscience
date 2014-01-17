package appeng.api.config;


public enum EnableDisable implements IConfigEnum {
	Disabled,
	Enabled;
	
	@Override
	public String getName() {
		return "CondenserOutput";
	}

	@Override
	public IConfigEnum[] getValues() {
		return values();
	}
	
}