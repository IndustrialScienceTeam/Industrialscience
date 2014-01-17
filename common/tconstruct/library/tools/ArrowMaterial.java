package tconstruct.library.tools;

public class ArrowMaterial
{
    public final float accuracy;
    public final float breakChance;
    public final float mass;

    public ArrowMaterial(float weight, float breakChance, float accuracy)
    {
        this.mass = weight;
        this.breakChance = breakChance;
        this.accuracy = accuracy;
    }
}
