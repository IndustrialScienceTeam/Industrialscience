package appeng.api.config;


public enum MatchingMode implements IConfigEnum<MatchingMode> {
	Fuzzy,
	Precision;
	
	@Override
	public String getName() {
		return "MatchingMode";
	}

	@Override
	public IConfigEnum[] getValues() {
		return MatchingMode.values();
	}
}