package mods.battlegear2.api;

import net.minecraft.entity.Entity;
import net.minecraftforge.event.entity.player.EntityInteractEvent;

public class OffhandAttackEvent extends PlayerEventChild {

    public EntityInteractEvent event;
    public boolean shouldAttack = true;
    public boolean swingOffhand = true;

    public OffhandAttackEvent(EntityInteractEvent parent) {
        super(parent);
        this.event = parent;
    }

	public Entity getTarget() {
        return event.target;
    }
}