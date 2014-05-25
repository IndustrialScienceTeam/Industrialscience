package de.zsgn.industrialscience.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import de.zsgn.industrialscience.IndustrialScience;

public class TileEntityMysteriousPortal extends TileEntity {
    private boolean activated=false;
    public boolean onBlockActivated(World world, int x, int y, int z,
            EntityPlayer player, int side, float xOffset, float yOffset,
            float zOffset) {
        if(!activated&&(player.inventory.getStackInSlot(player.inventory.currentItem)!=null&&player.inventory.getStackInSlot(player.inventory.currentItem).getItem()==IndustrialScience.getInstance().getItemancienttechnology())&&side==1){
            --player.inventory.getStackInSlot(player.inventory.currentItem).stackSize;
            activated=true;
            player.addChatMessage(new ChatComponentText(StatCollector.translateToLocal("mysteriousportal_activated")));
            return true;

        }
        return false;
    }
    @Override
    public void readFromNBT(NBTTagCompound tagCompound) {
        activated=tagCompound.getBoolean("activated");
    }
    @Override
    public void writeToNBT(NBTTagCompound tagCompound) {
        tagCompound.setBoolean("activated", activated);
    }

}
