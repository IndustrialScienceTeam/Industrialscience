package appeng.api.config;


// do not change names..
public enum StackModeInput implements IConfigEnum {
	Single,
	Stack;
	
	@Override
	public String getName() {
		return "StackMode";
	}

	@Override
	public IConfigEnum[] getValues() {
		return values();
	}
}