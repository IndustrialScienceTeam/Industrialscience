package appeng.api.config;


public enum ViewItems implements IConfigEnum
{
	ALL,
	CRAFTABLE,
	STORED;
	
	@Override
	public String getName() {
		return "ViewItems";
	}

	@Override
	public IConfigEnum[] getValues() {
		return values();
	}
}