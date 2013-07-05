package industrialscience;

import industrialscience.modules.research.backend.Research;

import java.util.Hashtable;

public class ResearchManager {
    private static ResearchManager instance = null;
    private Hashtable<String, Research> allResearches = null;
    private Hashtable<String, Research> activatedResearches = null;
    private boolean enabled = true;

    private ResearchManager() {
        allResearches = new Hashtable<String, Research>();
        activatedResearches = new Hashtable<String, Research>();
    }

    public static ResearchManager getInstance() {
        if (instance == null) {
            instance = new ResearchManager();
        }
        return instance;
    }

    public void registerResearch(Research r) throws Exception {
        if (enabled) {
            if (allResearches.get(r) != null)
                throw new Exception(
                        "There is already a research with the name: "
                                + r.getName());
            allResearches.put(r.getName(), r);
            if (r.check()) {
                activatedResearches.put(r.getName(), r);
            }
        }
    }

    public Hashtable<String, Research> getActivatedResearches() {
        return activatedResearches;
    }

    public Hashtable<String, Research> getAllResearches() {
        return allResearches;
    }
}