package mod.industrialscience.modules.research.backend;

import java.util.ArrayList;

public class Researchfactory{
	private String Category;
	private RecipeLocker Locker;
	private Researchchecker Checker;
	public Researchfactory(String category, RecipeLocker locker, Researchchecker checker) {
		super();
		Category = category;
		Locker = locker;
		Checker=checker;
	}
	public Research getResearch(String Name, ArrayList<Research> NeededResearches, String Category, Researchstep[] Steps, RecipeLocker Locker){
		return new Research(Name, NeededResearches, Category, Steps, Locker);
	}
	public Research getResearch(String Name, ArrayList<Research> NeededResearches, Researchstep[] Steps){
		return new Research(Name, NeededResearches, Category, Steps, Locker);
	}
	public Research getResearch(String Name, ArrayList<Research> NeededResearches, Researchstep[] Steps, RecipeLocker Locker){
		return new Research(Name, NeededResearches, Category, Steps, Locker);
	}

}
