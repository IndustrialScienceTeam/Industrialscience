package appeng.api.config;


// do not change names..
public enum OperationMode implements IConfigEnum {
	Empty,
	Fill;
	
	@Override
	public String getName() {
		return "OperationMode";
	}
	
	@Override
	public IConfigEnum[] getValues() {
		return values();
	}
}