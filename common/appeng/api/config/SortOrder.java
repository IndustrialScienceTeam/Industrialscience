package appeng.api.config;


public enum SortOrder implements IConfigEnum
{
	ItemID,
	Name,
	Priority,
	Size;
	
	@Override
	public String getName() {
		return "SortOrder";
	}

	@Override
	public IConfigEnum[] getValues() {
		return values();
	}
}