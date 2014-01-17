package appeng.api.config;


public enum SortDir implements IConfigEnum
{
	ASC,
	DESC;
	
	@Override
	public String getName() {
		return "SortDir";
	}

	@Override
	public IConfigEnum[] getValues() {
		return values();
	}
}