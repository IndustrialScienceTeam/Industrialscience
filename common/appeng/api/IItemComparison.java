package appeng.api;


public interface IItemComparison {
	
	public boolean sameAsFuzzy(IItemComparison comp);

	public boolean sameAsPrecise( IItemComparison comp);	
	
}
