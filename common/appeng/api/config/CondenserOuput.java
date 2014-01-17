package appeng.api.config;


public enum CondenserOuput implements IConfigEnum {
	MatterBalls,
	Singularity,
	Trash;
	
	@Override
	public String getName() {
		return "CondenserOutput";
	}

	@Override
	public IConfigEnum[] getValues() {
		return values();
	}
	
}