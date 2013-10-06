package industrialscience.modules.mining.frontend.items;

import appeng.api.IAEItemStack;
import appeng.api.Util;
import appeng.api.me.items.IStorageCell;
import industrialscience.IndustrialScience;
import industrialscience.modules.ISAbstractModule;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class ItemMinerPickaxe extends ItemPickaxe implements IStorageCell {
    private final static int basebytes=0;
    public ItemMinerPickaxe(int par1, EnumToolMaterial par2EnumToolMaterial) {
        super(par1, par2EnumToolMaterial);
        this.setUnlocalizedName(IndustrialScience.modules[2].getPrefix()
                + ".MinerPickaxe." + this.toolMaterial.toString());
    }
    public EnumToolMaterial getToolMaterial(){
        return toolMaterial;
    }

    @Override
    public boolean onBlockDestroyed(ItemStack par1ItemStack, World par2World,
            int par3, int par4, int par5, int par6,
            EntityLivingBase par7EntityLivingBase) {
        return super.onBlockDestroyed(par1ItemStack, par2World, par3, par4,
                par5, par6, par7EntityLivingBase);
    }

    @Override
    public float getStrVsBlock(ItemStack par1ItemStack, Block par2Block) {
        float str = super.getStrVsBlock(par1ItemStack, par2Block);
        if (str == 1.0F)
            return 0F;
        return str;
    }

    @Override
    public int getBytes(ItemStack cellItem) {
        return getStorageBytes(cellItem);
    }
    

    @Override
    public String getItemDisplayName(ItemStack par1ItemStack) {
        if(ISAbstractModule.isAEinstalled()){
        Boolean hasName = !Util.getCellRegistry().getHandlerForCell(par1ItemStack).getName().isEmpty();
        String partName = Util.getCellRegistry().getHandlerForCell(par1ItemStack).getName();
        if(hasName){
            return super.getItemDisplayName(par1ItemStack) + " - " + partName;
        }
        }
            return super.getItemDisplayName(par1ItemStack);
    }
    @Override
    public int BytePerType(ItemStack iscellItem) {
            return getStorageBytes(iscellItem)/128;
    }

    @Override
    public int getTotalTypes(ItemStack cellItem) {
        return 63;
    }

    @Override
    public boolean isBlackListed(ItemStack cellItem,
            IAEItemStack requsetedAddition) {
       return false;
    }

    @Override
    public boolean storableInStorageCell() {
        return true;
    }
    protected int getStorageBytes(ItemStack item){
        if(item.getItem() instanceof ItemMinerPickaxe){
            EnumToolMaterial material=((ItemMinerPickaxe) item.getItem()).getToolMaterial();
            switch (material.getHarvestLevel()) {
                case 0:
                    return 1024;
                case 1:
                    return 4096;
                case 2:
                    return 16384;
                case 3:
                    return 65536;
                default:
                    break;
            }
        }
        return 0;
    }
}
