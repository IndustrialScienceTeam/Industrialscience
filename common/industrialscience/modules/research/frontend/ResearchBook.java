package industrialscience.modules.research.frontend;

import industrialscience.IISItem;
import industrialscience.modules.research.frontend.GUI.ResearchBookGUI;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class ResearchBook extends IISItem {
    public static final String NBTNAME = "Researches";

    public ResearchBook() {
        super("researchbook","Research Book",1);

    }

    @Override
    public void registerIcons(IconRegister iconRegister) {
        itemIcon = iconRegister.registerIcon("industrialscience:research-book");
    }

    @Override
    public void onCreated(ItemStack par1ItemStack, World par2World,
            EntityPlayer par3EntityPlayer) {
        if (par1ItemStack.stackTagCompound == null) {
            par1ItemStack.setTagCompound(new NBTTagCompound());
        }
        int[] researchesid = {};
        par1ItemStack.stackTagCompound.setIntArray(NBTNAME, researchesid);

    }

    @Override
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World,
            EntityPlayer par3EntityPlayer) {
        Minecraft.getMinecraft().displayGuiScreen(
                new ResearchBookGUI(par3EntityPlayer, par1ItemStack));
        return super.onItemRightClick(par1ItemStack, par2World,
                par3EntityPlayer);
    }

    public int[] getResearchID() {
        return null;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void addInformation(ItemStack par1ItemStack,
            EntityPlayer par2EntityPlayer,
            @SuppressWarnings("rawtypes") List par3List, boolean par4) {
        if (par1ItemStack.stackTagCompound == null) {
            par1ItemStack.setTagCompound(new NBTTagCompound());
        }
        int[] researchesid = par1ItemStack.stackTagCompound
                .getIntArray(NBTNAME);
        if (researchesid != null) {
            par3List.add("Researches: " + researchesid.length);
        }
    }

}
