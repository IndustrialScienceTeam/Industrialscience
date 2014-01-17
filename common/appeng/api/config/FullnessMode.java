package appeng.api.config;


// do not change names..
public enum FullnessMode implements IConfigEnum {
	Empty,
	Full,
	Half;
	
	@Override
	public String getName() {
		return "FullnessMode";
	}
	
	@Override
	public IConfigEnum[] getValues() {
		return values();
	}
}