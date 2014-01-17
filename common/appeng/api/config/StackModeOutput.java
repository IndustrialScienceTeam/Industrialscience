package appeng.api.config;


// do not change names..
public enum StackModeOutput implements IConfigEnum {
	Craft,
	CraftOnly,
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