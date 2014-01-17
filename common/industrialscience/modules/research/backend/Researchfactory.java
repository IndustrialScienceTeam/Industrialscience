package industrialscience.modules.research.backend;

import industrialscience.ResearchManager;

import java.util.Hashtable;

public class Researchfactory {
    private String Category;
    private Researchchecker Checker;
    private boolean defaultvalues = false;
    private RecipeLocker Locker;

    public Researchfactory() {
    }

    public Researchfactory(String category, RecipeLocker locker,
            Researchchecker checker) {
        Category = category;
        Locker = locker;
        Checker = checker;
        defaultvalues = true;
    }

    public Research getResearch(String name, Hashtable<Integer, Boolean> steps) {
        Research research = ResearchManager.getInstance().getAllResearches()
                .get(name);
        research.enableSteps(steps);
        return research;
    }

    public Research getResearch(String Name, String[] NeededResearches,
            Object Data, Researchstep[] Steps) {
        if (defaultvalues)
            return new Research(Name, NeededResearches, Category, Data, Steps,
                    Locker, Checker);
        return null;
    }

    public Research getResearch(String Name, String[] NeededResearches,
            Object Data, Researchstep[] Steps, RecipeLocker Locker) {
        if (defaultvalues)
            return new Research(Name, NeededResearches, Category, Data, Steps,
                    Locker, Checker);
        return null;
    }

    public Research getResearch(String Name, String[] NeededResearches,
            String Category, Object Data, Researchstep[] Steps,
            RecipeLocker Locker, Researchchecker Checker) {
        return new Research(Name, NeededResearches, Category, Data, Steps,
                Locker, Checker);
    }
}
