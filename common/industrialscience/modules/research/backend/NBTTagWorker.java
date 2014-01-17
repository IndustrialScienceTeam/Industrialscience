package industrialscience.modules.research.backend;

import java.util.Hashtable;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class NBTTagWorker {
    public static NBTTagCompound getResearchAsNBTTag(Research r) {
        NBTTagCompound rNBT = new NBTTagCompound();
        rNBT.setName(r.getName());
        rNBT.setTag("Steps", getStepsAsNBTTag(r.getEnabledSteps()));
        return rNBT;
    }

    public static NBTTagList getStepsAsNBTTag(
            Hashtable<Integer, Boolean> hashtable) {
        NBTTagList stepsNBT = new NBTTagList();
        for (Hashtable<Integer, Boolean> elements = hashtable; elements.keys()
                .hasMoreElements();) {
            NBTTagCompound compound = new NBTTagCompound();
            Integer key = elements.keys().nextElement();
            compound.setInteger("ID", key);
            compound.setBoolean("Enabled", elements.get(key));
            stepsNBT.appendTag(compound);
        }
        return stepsNBT;
    }

    public static Research[] readResearchesfromNBT(NBTTagCompound compund) {
        NBTTagList researchlist = compund.getTagList("Researches");
        Research[] researches = new Research[researchlist.tagCount()];
        for (int i = 0; i < researchlist.tagCount(); i++) {
            researches[i] = readResearchfromNBTTag(researchlist.tagAt(i));

        }
        return researches;
    }

    public static Research readResearchfromNBTTag(NBTBase nbt) {
        NBTTagCompound compound = (NBTTagCompound) nbt;
        Researchfactory rf = new Researchfactory();
        Research research = rf.getResearch(compound.getName(),
                readStepsfromNBTTag(compound.getTagList("Steps")));
        return research;
    }

    private static Hashtable<Integer, Boolean> readStepsfromNBTTag(
            NBTTagList tagList) {
        Hashtable<Integer, Boolean> steps = new Hashtable<Integer, Boolean>();
        for (int i = 0; i < tagList.tagCount(); i++) {
            if (i != ((NBTTagCompound) tagList.tagAt(i)).getInteger("ID"))
                throw new RuntimeException();
            steps.put(i,
                    ((NBTTagCompound) tagList.tagAt(i)).getBoolean("Enabled"));
        }
        return steps;
    }

    public static void writeResearchesToNBT(NBTTagCompound compund,
            Research[] researches) {
        NBTTagList researchlist = new NBTTagList();
        for (Research research : researches) {
            researchlist.appendTag(getResearchAsNBTTag(research));
        }
        compund.setTag("Researches", researchlist);

    }

}
