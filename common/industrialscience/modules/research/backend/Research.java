package industrialscience.modules.research.backend;

import java.util.Arrays;
import java.util.Enumeration;
import java.util.Hashtable;

public class Research implements Comparable<Research> {
    public final static String RESEARCH_CATEGORY = "Researching";
    private String Category;
    private Researchchecker Checker;
    private Object Data;
    private Hashtable<Integer, Boolean> EnabledSteps;
    private RecipeLocker Locker;
    private String Name;
    private String[] NeededResearches;
    private Researchstep[] Steps;

    public Research(String name, String[] neededResearches, String category,
            Object data, Researchstep[] steps, RecipeLocker locker,
            Researchchecker checker) {
        Name = name;
        if(neededResearches==null){
        	this.NeededResearches=new String[0];
        }else{
        	this.NeededResearches= Arrays.copyOf(neededResearches, neededResearches.length);
        }
        Category = category;
        if(steps==null){
        	this.Steps=new Researchstep[0];
        }else{
        	this.Steps=Arrays.copyOf(steps, steps.length);
        }
        Arrays.sort(Steps);
        Locker = locker;
        Checker = checker;
        Data = data;
    }

    public boolean check() {
        return Checker.check();
    }

    @Override
    public int compareTo(Research o) {
        return Name.compareTo(o.getName());
    }

    public void enableSteps(Hashtable<Integer, Boolean> steps) {
        if (steps.size() != EnabledSteps.size())
            throw new RuntimeException();
        EnabledSteps = steps;

    }

    public String getCategory() {
        return Category;
    }

    public Object getData() {
        return Data;
    }

    public Hashtable<Integer, Boolean> getEnabledSteps() {
        return EnabledSteps;
    }

    public String getName() {
        return Name;
    }

    public String[] getNeededResearches() {
        return NeededResearches;
    }

    public synchronized Researchstep[] getSteps() {
        return Steps;
    }

    public boolean isResearched() {
        for (Enumeration<Boolean> list = EnabledSteps.elements(); list
                .hasMoreElements();) {
            if (!list.nextElement())
                return false;
        }
        return true;
    }

    public synchronized void lock() {
        Locker.lock();
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

    public void setSteps(Researchstep[] steps) {
        if(steps==null){
        	this.Steps= new Researchstep[0];
        }else{
        	this.Steps=Arrays.copyOf(steps, steps.length);
        }
    }

    @Override
    public String toString() {
        return "Researchname: " + Name + ", ResearchCategory: " + Category;

    }

    private synchronized void unlock() {
        Locker.unlock();
    }

}
