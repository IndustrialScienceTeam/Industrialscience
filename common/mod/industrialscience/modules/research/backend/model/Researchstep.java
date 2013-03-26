package mod.industrialscience.modules.research.backend.model;


public class Researchstep{
	private int ID=0;
	private boolean enabled= false;
	private ResearchObject neededobject;
	private String ResearchStepText;

	public boolean isEnabled(){
		return enabled;
	}
	public boolean research(ResearchObject ro){
		if(ro.equals(neededobject)){
			enabled=true;
			return true;
		}
		return false;
	}
	public String getResearchStepText() {
		return ResearchStepText;
	}

}
