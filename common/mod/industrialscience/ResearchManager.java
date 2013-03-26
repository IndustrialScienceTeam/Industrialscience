package mod.industrialscience;

import java.util.ArrayList;

import mod.industrialscience.modules.research.backend.model.Research;

public class ResearchManager {
private static ResearchManager instance=null;
private ArrayList<Research> allResearches=null;
private ArrayList<Research> activatedResearches=null;
public void loadResearches(){
	for(Research r : allResearches){
		activatedResearches.add(r);
	}
}
public static ResearchManager getInstance(){
	if(instance==null){
		instance= new ResearchManager();
	}
	return instance;
}
public void registerResearch(Research r){
	
}
}
