package industrialscience.modules.research.backend;

public class Researchstep implements Comparable<Researchstep> {
    private int ID = 0;
    private ResearchObject neededobject;
    private String ResearchStepText;

    public Researchstep(int iD, ResearchObject neededobject,
            String researchStepText) {
        super();
        ID = iD;
        this.neededobject = neededobject;
        ResearchStepText = researchStepText;
    }

    @Override
    public int compareTo(Researchstep o) {
        Integer id = new Integer(ID);
        return id.compareTo(new Integer(o.getID()));
    }

    public int getID() {
        return ID;
    }

    public ResearchObject getNeededobject() {
        return neededobject;
    }

    public String getResearchStepText() {
        return ResearchStepText;
    }

    public boolean research(ResearchObject ro) {
        if (ro.isOkay(neededobject))
            return true;
        return false;
    }

}
