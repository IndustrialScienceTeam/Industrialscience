package appeng.api.config;

public enum InterfaceCraftingMode implements IConfigEnum {
	Craft,
	DontCraft;
	
	@Override
	public String getName() {
		return "InterfaceCraftingMode";
	}

	@Override
	public IConfigEnum[] getValues() {
		return values();
	}
	
}
