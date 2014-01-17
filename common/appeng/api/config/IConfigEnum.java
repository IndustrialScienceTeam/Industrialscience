package appeng.api.config;

public interface IConfigEnum <E> {
	
	String getName();
	
	IConfigEnum[] getValues();

	int ordinal();
	
}
