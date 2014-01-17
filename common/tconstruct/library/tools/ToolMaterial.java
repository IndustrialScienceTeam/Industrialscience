package tconstruct.library.tools;

/*
 * Dynamic substitute for an enum. It carries a lot of information
 */
public class ToolMaterial
{
    //mining level, durability, mining speed, baseDamage, handle modifier, Reinforced level, shoddy/spiny level, color/style on name

    public final String ability;
    public final int attack;
    public final String displayName;
    public final int durability;
    public final float handleModifier;
    public final int harvestLevel;
    public final String materialName;
    public final int miningspeed; // <-- divided by 100
    public final int reinforced;
    public final float stonebound;
    public final String tipStyle;

    public ToolMaterial(String name, int level, int durability, int speed, int damage, float handle, int reinforced, float stonebound, String style, String ability)
    {
        this(name, name + " ", level, durability, speed, damage, handle, reinforced, stonebound, style, ability);
    }

    public ToolMaterial(String name, String displayName, int level, int durability, int speed, int damage, float handle, int reinforced, float stonebound, String style, String ability)
    {
        this.materialName = name;
        this.displayName = displayName;
        this.harvestLevel = level;
        this.durability = durability;
        this.miningspeed = speed;
        this.attack = damage;
        this.handleModifier = handle;
        this.reinforced = reinforced;
        this.stonebound = stonebound;
        this.tipStyle = style;
        this.ability = ability;
    }

    public String ability ()
    {
        return this.ability;
    }

    public int attack ()
    {
        return this.attack;
    }

    public int durability ()
    {
        return this.durability;
    }

    public float handleDurability ()
    {
        return this.handleModifier;
    }

    public int harvestLevel ()
    {
        return this.harvestLevel;
    }

    public String name ()
    {
        return materialName;
    }

    public int reinforced ()
    {
        return this.reinforced;
    }

    public float shoddy ()
    {
        return this.stonebound;
    }

    public String style ()
    {
        return this.tipStyle;
    }

    public int toolSpeed ()
    {
        return this.miningspeed;
    }
}
