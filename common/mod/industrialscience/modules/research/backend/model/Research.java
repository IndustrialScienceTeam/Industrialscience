package mod.industrialscience.modules.research.backend.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Hashtable;

public class Research implements Comparable<Research> {
    private String Name;
    private ArrayList<Research> NeededResearches;
    private String Category;
    private Researchstep[] Steps;
    private Hashtable<Integer, Boolean> EnabledSteps;
    private RecipeLocker Locker;
    private Researchchecker Checker;
    private Object Data;

    public Research(String name, ArrayList<Research> neededResearches,
            String category, Object data, Researchstep[] steps,
            RecipeLocker locker, Researchchecker checker) {
        Name = name;
        NeededResearches = neededResearches;
        Category = category;
        Steps = steps;
        Arrays.sort(Steps);
        Locker = locker;
        Checker = checker;
        Data = data;
    }

    public boolean research(ResearchObject object) {
        if (!isResearched()) {
            int id = -1;
            for (Enumeration<Integer> iterator = EnabledSteps.keys(); iterator
                    .hasMoreElements();) {
                Integer element = iterator.nextElement();
                if (!EnabledSteps.get(element)) {
                    if (element.intValue() != Steps[element.intValue()].getID())
                        throw new RuntimeException();
                    id = element.intValue();
                }

            }
            boolean toreturn = false;
            if (id != -1) {
                toreturn = Steps[id].research(object);
                EnabledSteps.put(new Integer(id), toreturn);
                if (isResearched()) {
                    unlock();
                }
                return toreturn;
            }
        }
        return false;
    }

    public boolean check() {
        return Checker.check();
    }

    public boolean isResearched() {
        for (Enumeration<Boolean> list = EnabledSteps.elements(); list
                .hasMoreElements();) {
            if (!list.nextElement())
                return false;
        }
        return true;
    }

    @Override
    public int compareTo(Research o) {
        return Name.compareTo(o.getName());
    }

    private synchronized void unlock() {
        Locker.unlock();
    }

    public synchronized void lock() {
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

    public Hashtable<Integer, Boolean> getEnabledSteps() {
        return EnabledSteps;
    }

    @Override
    public String toString() {
        return "Researchname: " + Name + ", ResearchCategory: " + Category;

    }

    public void setSteps(Researchstep[] steps) {
        Steps = steps;
    }

    public void enableSteps(Hashtable<Integer, Boolean> steps) {
        if (steps.size() != EnabledSteps.size())
            throw new RuntimeException();
        EnabledSteps = steps;

    }

    public Object getData() {
        return Data;
    }

}
