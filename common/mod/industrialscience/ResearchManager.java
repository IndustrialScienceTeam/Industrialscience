package mod.industrialscience;

import java.util.ArrayList;
import mod.industrialscience.modules.research.backend.Research;

public class ResearchManager {
private ArrayList<Research> allResearches=null;
private ArrayList<Research> activatedResearches=null;
public void loadResearches(){
	for(Research r : allResearches){
		activatedResearches.add(r);
	}
}
public void registerResearch(Research r){
	
}
}
