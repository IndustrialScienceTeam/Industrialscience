package industrialscience.modules.mining.frontend.items;

import industrialscience.IndustrialScience;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemMinerPickaxe extends ItemPickaxe {

    public ItemMinerPickaxe(int par1, EnumToolMaterial par2EnumToolMaterial) {
        super(par1, par2EnumToolMaterial);
        this.setUnlocalizedName(IndustrialScience.modules[2].getPrefix()
                + ".MinerPickaxe." + this.toolMaterial.toString());
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
}
