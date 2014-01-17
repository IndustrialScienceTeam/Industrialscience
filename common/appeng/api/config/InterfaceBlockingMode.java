package appeng.api.config;

public enum InterfaceBlockingMode implements IConfigEnum {
	Blocking,
	NonBlocking;
	
	@Override
	public String getName() {
		return "InterfaceBlockingMode";
	}

	@Override
	public IConfigEnum[] getValues() {
		return values();
	}
	
}