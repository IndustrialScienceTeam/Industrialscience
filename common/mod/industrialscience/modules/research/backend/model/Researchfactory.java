package mod.industrialscience.modules.research.backend.model;

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
	public Research getResearch(String ProgrammingDate, String Name, ArrayList<Research> NeededResearches, String Category, Researchstep[] Steps, RecipeLocker Locker, Researchchecker Checker){
		return new Research(ProgrammingDate, Name, NeededResearches, Category, Steps, Locker, Checker);
	}
	public Research getResearch(String ProgrammingDate, String Name, ArrayList<Research> NeededResearches, Researchstep[] Steps){
		return new Research(ProgrammingDate ,Name, NeededResearches, Category, Steps, Locker, Checker);
	}
	public Research getResearch(String ProgrammingDate, String Name, ArrayList<Research> NeededResearches, Researchstep[] Steps, RecipeLocker Locker){
		return new Research(ProgrammingDate, Name, NeededResearches, Category, Steps, Locker, Checker);
	}

}
