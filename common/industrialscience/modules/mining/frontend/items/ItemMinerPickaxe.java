package industrialscience.modules.mining.frontend.items;

import industrialscience.IndustrialScience;
import industrialscience.modules.ISAbstractModule;

import java.util.Iterator;

import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import appeng.api.IAEItemStack;
import appeng.api.Util;
import appeng.api.me.items.IStorageCell;

public class ItemMinerPickaxe extends ItemPickaxe implements IStorageCell {
    public ItemMinerPickaxe(int par1, EnumToolMaterial par2EnumToolMaterial) {
        super(par1, par2EnumToolMaterial);
        this.setUnlocalizedName(IndustrialScience.modules[2].getPrefix()
                + ".MinerPickaxe." + this.toolMaterial.toString());
    }

    public EnumToolMaterial getToolMaterial() {
        return toolMaterial;
    }

    @Override
    public boolean onBlockStartBreak(ItemStack itemstack, int x, int y, int z,
            EntityPlayer player) {
        if (ISAbstractModule.isAEinstalled()) {
            World world = player.worldObj;
            int blockID = player.worldObj.getBlockId(x, y, z);
            int meta = world.getBlockMetadata(x, y, z);
            Block block = Block.blocksList[blockID];
            if (block == null || blockID < 1)
                return false;
            ItemStack orestack = new ItemStack(block.idDropped(meta, itemRand,
                    0), block.quantityDropped(meta, 0, itemRand),
                    block.damageDropped(meta));
            world.setBlockToAir(x, y, z);
            if (!player.capabilities.isCreativeMode)
                onBlockDestroyed(itemstack, world, blockID, x, y, z, player);
            if (!world.isRemote) {
                ItemStack savestack = orestack.copy();
                if (orestack.getItem() instanceof ItemBlock) {
                    int loot = EnchantmentHelper.getEnchantmentLevel(
                            Enchantment.fortune.effectId, itemstack);
                    if (loot > 0)
                        savestack.stackSize *= (itemRand.nextInt(loot + 1) + 1);
                }
                Util.getCellRegistry().getHandlerForCell(itemstack)
                        .addItems(Util.createItemStack(savestack));
                world.playAuxSFX(2001, x, y, z, blockID + (meta << 12));

                return true;
            }
        }
        return false;
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
        if (ISAbstractModule.isAEinstalled()) {
            Boolean hasName = !Util.getCellRegistry()
                    .getHandlerForCell(par1ItemStack).getName().isEmpty();
            String partName = Util.getCellRegistry()
                    .getHandlerForCell(par1ItemStack).getName();
            if (hasName)
                return super.getItemDisplayName(par1ItemStack) + " - "
                        + partName;
        }
        return super.getItemDisplayName(par1ItemStack);
    }

    @Override
    public int BytePerType(ItemStack iscellItem) {
        return getStorageBytes(iscellItem) / 128;
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

    @Override
    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world,
            int x, int y, int z, int side, float clickX, float clickY,
            float clickZ) {
        if (ISAbstractModule.isAEinstalled()) {
            int posX = x;
            int posY = y;
            int posZ = z;
            int playerPosX = (int) Math.floor(player.posX);
            int playerPosY = (int) Math.floor(player.posY);
            int playerPosZ = (int) Math.floor(player.posZ);
            switch (side) {
                case 0:
                    --posY;
                    break;
                case 1:
                    ++posY;
                    break;
                case 2:
                    --posZ;
                    break;
                case 3:
                    ++posZ;
                    break;
                case 4:
                    --posX;
                    break;
                case 5:
                    ++posX;
                    break;
                default:
                    return false;
            }
            if (posX == playerPosX
                    && (posY == playerPosY || posY == playerPosY + 1 || posY == playerPosY - 1)
                    && posZ == playerPosZ)
                return false;
            IAEItemStack blocktoplace=getPlaceableItem(stack);
            if (blocktoplace != null){
                blocktoplace.getItem().onItemUse(getPlaceableItem(stack).getItemStack(), player, world, x, y, z, side, clickX,clickY,clickZ);
                blocktoplace.setStackSize(1);
                Util.getCellRegistry().getHandlerForCell(stack).extractItems(blocktoplace);
                return true;
                }
        }
        return false;
    }

    protected IAEItemStack getPlaceableItem(ItemStack stack) {
        IAEItemStack toReturn = null;
        Iterator<IAEItemStack> iter = Util.getCell(stack).getAvailableItems()
                .iterator();
        while (iter.hasNext()) {
            IAEItemStack contentstack = iter.next();
            if (toReturn == null)
                toReturn = contentstack;
            else if (contentstack.getStackSize() > toReturn.getStackSize()
                    && contentstack.getItem() instanceof ItemBlock)
                toReturn = contentstack;
        }
        return toReturn;
    }

    protected int getStorageBytes(ItemStack item) {
        if (item.getItem() instanceof ItemMinerPickaxe) {
            EnumToolMaterial material = ((ItemMinerPickaxe) item.getItem())
                    .getToolMaterial();
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
