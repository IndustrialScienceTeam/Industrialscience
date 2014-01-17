package appeng.api.config;


public enum FuzzyMode implements IConfigEnum<FuzzyMode> {
	IgnoreAll(-1.0F),
	Percent_25(0.25F),
	Percent_50(0.5F),
	Percent_75(0.75F),
	Percent_99(1.0F);
	
	public float breakPoint;
	
	private FuzzyMode( float p )
	{
		breakPoint = p;
	}

	@Override
	public String getName() {
		return "FuzzyMode";
	}

	@Override
	public IConfigEnum[] getValues() {
		return values();
	}
}