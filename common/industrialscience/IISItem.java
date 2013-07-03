package industrialscience;

import java.util.List;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public abstract class IISItem{

    protected Icon itemIcon;
    private int maxStackSize;
    private String readableName;
    private String name;
    protected IISItem(String name, String readableName, int maxStackSize) {
        super();
        this.name = name;
        this.readableName = readableName;
        this.maxStackSize = maxStackSize;
    }
    public String getReadableName() {
        return readableName;
    }
    public Icon getItemIcon() {
        return itemIcon;
    }
    public int getMaxStackSize() {
        return maxStackSize;
    }
    public abstract void registerIcons(IconRegister iconRegister);
    public void onCreated(ItemStack par1ItemStack, World par2World,
            EntityPlayer par3EntityPlayer) {}
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World,
            EntityPlayer par3EntityPlayer) {
        return par1ItemStack;
    }
    @SuppressWarnings("rawtypes")
    public void addInformation(ItemStack par1ItemStack,
            EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
        
    }
    public String getUnlocalizedName() {
        return name;
    }

}
