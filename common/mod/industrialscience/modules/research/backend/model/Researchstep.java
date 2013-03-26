package mod.industrialscience.modules.research.backend.model;


public class Researchstep implements Comparable<Researchstep>{
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
	@Override
	public int compareTo(Researchstep o) {
		Integer id = new Integer(ID);
		return id.compareTo(new Integer(o.getID()));
	}
	public ResearchObject getNeededobject() {
		return neededobject;
	}
	public int getID() {
		return ID;
	}

}
