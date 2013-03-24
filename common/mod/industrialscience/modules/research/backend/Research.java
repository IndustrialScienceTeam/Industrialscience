package mod.industrialscience.modules.research.backend;

import java.util.ArrayList;

public class Research implements Comparable<Research>{
private String Name;
private ArrayList<Research> NeededResearches;
private String Category;
private Researchstep[] Steps;
private RecipeLocker Locker;

public Research(String name, ArrayList<Research> neededResearches,
		String category, Researchstep[] steps, RecipeLocker locker) {
	Name = name;
	NeededResearches = neededResearches;
	Category = category;
	Steps = steps;
	Locker = locker;
}



	public int compareTo(Research o) {
		return Name.compareTo(o.getName());
	}
	
	public synchronized void unlock(){
		Locker.unlock();
	}
	public synchronized void lock(){
		Locker.lock();
	}
	

	public String getName() {
		return Name;
	}

	public ArrayList<Research> getNeededResearches() {
		return NeededResearches;
	}

	public String getCategory() {
		return Category;
	}

	public synchronized Researchstep[] getSteps() {
		return Steps;
	}

}
