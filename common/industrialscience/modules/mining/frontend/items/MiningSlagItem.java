package industrialscience.modules.mining.frontend.items;

import java.util.List;

import industrialscience.IndustrialScience;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatMessageComponent;
import net.minecraft.world.World;

public class MiningSlagItem extends ItemFood {

    public MiningSlagItem(int par1) {
        super(par1,1,1.0F,true);
        setUnlocalizedName(IndustrialScience.modules[2].getPrefix()
                + "Mining_Slag");
        LanguageRegistry.addName(this, "Mining Slag");
    }

    @Override
    public void registerIcons(IconRegister iconRegister) {
        itemIcon = iconRegister.registerIcon("apple");
    }

    @Override
    public void addInformation(ItemStack par1ItemStack,
            EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
        par3List.add("YOU ASK ME 'WHY IS THAT AN APPLE?'");
        par3List.add("I let you guess.");
        par3List.add("...");
        par3List.add("...");
        par3List.add("IT'S WIP!!");
    }

    @Override
    public ItemStack onEaten(ItemStack par1ItemStack, World par2World,
            EntityPlayer par3EntityPlayer) {
        par3EntityPlayer.sendChatToPlayer(new ChatMessageComponent().setBold(true).addText("NOOOO! YOU CAN'T EAT THIS!"));
        ItemStack stack= super.onEaten(par1ItemStack, par2World, par3EntityPlayer);
        if(!par2World.isRemote){
        EntityTNTPrimed tnt = new EntityTNTPrimed(par2World);
        tnt.setPosition(par3EntityPlayer.posX, par3EntityPlayer.posY, par3EntityPlayer.posZ);
        par2World.spawnEntityInWorld(tnt);}
        return stack;
    }
    
}
