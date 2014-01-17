package industrialscience.modules.research.frontend;

import industrialscience.IndustrialScience;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.FMLNetworkHandler;

public class ResearchBook extends Item {
    public static final String NBTNAME = "Researches";

    public ResearchBook(int id, String prefix) {
        super(id);
        setMaxStackSize(1);
        setUnlocalizedName(prefix+".Research_book");

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

    public int[] getResearchID() {
        return null;
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
        FMLNetworkHandler.openGui(par3EntityPlayer, IndustrialScience.instance,
                IndustrialScience.MODULES[0].formGUIID(1), par2World,
                par3EntityPlayer.chunkCoordX, par3EntityPlayer.chunkCoordY,
                par3EntityPlayer.chunkCoordZ);
        return super.onItemRightClick(par1ItemStack, par2World,
                par3EntityPlayer);
    }

    @Override
    public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player,
            Entity entity) {
        if (stack.stackTagCompound == null) {
            stack.setTagCompound(new NBTTagCompound());
        }
        ArrayList<Integer> ids = new ArrayList<Integer>();
        int[] researchesid = stack.stackTagCompound.getIntArray(NBTNAME);
        for (int i : researchesid) {
            ids.add(new Integer(i));
        }
        ids.add(new Integer(27));
        researchesid = new int[ids.size()];
        for (int i = 0; i < ids.size(); i++) {
            researchesid[i] = ids.get(i).intValue();
        }
        stack.stackTagCompound.setIntArray(NBTNAME, researchesid);
        return false;
    }

    @Override
    public void registerIcons(IconRegister iconRegister) {
        itemIcon = iconRegister.registerIcon("industrialscience:research-book");
    }

}
