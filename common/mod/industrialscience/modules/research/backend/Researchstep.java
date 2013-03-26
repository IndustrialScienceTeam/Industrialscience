package mod.industrialscience.modules.research.backend;


public class Researchstep{
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
