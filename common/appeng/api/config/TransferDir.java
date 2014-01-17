package appeng.api.config;


public enum TransferDir implements IConfigEnum
{
	LEFT,
	RIGHT;
	
	@Override
	public String getName() {
		return "TransferDir";
	}

	@Override
	public IConfigEnum[] getValues() {
		return values();
	}
}