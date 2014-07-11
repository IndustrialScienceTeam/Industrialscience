package de.zsgn.industrialscience.factory.item;

import java.text.DecimalFormat;

import de.zsgn.industrialscience.IndustrialScience;
import de.zsgn.industrialscience.factory.tileentity.controllers.IThermometerSupport;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

public class ItemThermometer extends Item {

    public ItemThermometer() {
        this.setCreativeTab(IndustrialScience.getInstance().getCreativetab());
        this.setUnlocalizedName("thermometer");
    }

    @Override
    public boolean onItemUse(ItemStack itemStack, EntityPlayer player,
            World world, int x, int y, int z,
            int side, float xOffset, float yOffset,
            float zOffset) {
        if(world.getTileEntity(x, y, z) instanceof IThermometerSupport){
            if(!world.isRemote){
                DecimalFormat df = new DecimalFormat("#.0");
                String temp=df.format(((IThermometerSupport)world.getTileEntity(x, y, z)).getTemperature());
                player.addChatMessage(new ChatComponentText("Temperature: "+temp));
            }
            return true;
        }else{
            return false;
        }
    }

}
