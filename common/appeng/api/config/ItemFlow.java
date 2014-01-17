package appeng.api.config;


public enum ItemFlow implements IConfigEnum<ItemFlow> {
	
	READ, READ_WRITE, WRITE;
	
	@Override
	public String getName() {
		return "ItemFlow";
	}

	@Override
	public IConfigEnum[] getValues() {
		return values();
	}
	
}