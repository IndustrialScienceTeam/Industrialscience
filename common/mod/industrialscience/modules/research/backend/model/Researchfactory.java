package mod.industrialscience.modules.research.backend.model;

import java.util.ArrayList;

import mod.industrialscience.ResearchManager;
import net.minecraft.nbt.NBTTagCompound;

public class Researchfactory{
	private boolean defaultvalues=false;
	private String Category;
	private RecipeLocker Locker;
	private Researchchecker Checker;
	public Researchfactory(String category, RecipeLocker locker, Researchchecker checker) {
		Category = category;
		Locker = locker;
		Checker=checker;
		defaultvalues=true;
	}
	public Researchfactory() {
	}
	public Research getResearch(String ProgrammingDate, String Name, ArrayList<Research> NeededResearches, String Category, Researchstep[] Steps, RecipeLocker Locker, Researchchecker Checker){
		return new Research(ProgrammingDate, Name, NeededResearches, Category, Steps, Locker, Checker);
	}
	public Research getResearch(String ProgrammingDate, String Name, ArrayList<Research> NeededResearches, Researchstep[] Steps){
		if(defaultvalues)
		return new Research(ProgrammingDate ,Name, NeededResearches, Category, Steps, Locker, Checker);
		return null;
	}
	public Research getResearch(String ProgrammingDate, String Name, ArrayList<Research> NeededResearches, Researchstep[] Steps, RecipeLocker Locker){
		if(defaultvalues)
		return new Research(ProgrammingDate, Name, NeededResearches, Category, Steps, Locker, Checker);
		return null;
	}
	public Research getResearch(NBTTagCompound nbttc, String name){
		Research research = ResearchManager.getInstance().getAllResearches().get(name);
		Researchstep[] cleansteps= research.getSteps();
		Researchstep[] steps = new Researchstep[cleansteps.length];
		Researchstep modedresearchstep;
		for (Researchstep researchstep : cleansteps) {
		modedresearchstep=researchstep;
		modedresearchstep.setEnabled(nbttc.getBoolean(String.valueOf(researchstep.getID())));
		steps[researchstep.getID()]=modedresearchstep;
		}
		research.setSteps(steps);
		return research;
	}
}
