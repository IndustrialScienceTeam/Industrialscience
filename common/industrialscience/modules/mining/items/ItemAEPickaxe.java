package industrialscience.modules.mining.items;

import industrialscience.IndustrialScience;
import industrialscience.modules.ISAbstractModule;

import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;

import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import appeng.api.IAEItemStack;
import appeng.api.Util;
import appeng.api.me.items.IStorageCell;

public class ItemAEPickaxe extends ItemPickaxe implements IStorageCell {
    public static int getStorageAmount(ItemStack item){
    	return item.stackTagCompound.getCompoundTag("IndustrialScience.AEPickaxe").getInteger("size");
    }

    public static void setStorageAmount(int bytes, ItemStack itemstack){
    	NBTTagCompound pickaxecompound= new NBTTagCompound();
    	pickaxecompound.setInteger("size", bytes);
    	if(itemstack.stackTagCompound==null){
    		itemstack.stackTagCompound=new NBTTagCompound();
    	}
    	itemstack.stackTagCompound.setCompoundTag("IndustrialScience.AEPickaxe", pickaxecompound);
    }

    public ItemAEPickaxe(int par1, EnumToolMaterial par2EnumToolMaterial, String prefix) {
        super(par1, par2EnumToolMaterial);
        this.setUnlocalizedName(prefix
                + ".AEPickaxe." + this.toolMaterial.toString());
    }

    @Override
	public void addInformation(ItemStack par1ItemStack,
			EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
		String storinfo="";
		String nextblockinfo="";
		String usedbytes="";
		try{
		Object cell=Class.forName("appeng.api.Util").getMethod("getCell", ItemStack.class).invoke(null, par1ItemStack);
		usedbytes=cell.getClass().getMethod("usedBytes", new Class[0]).invoke(cell, new Object[0]).toString();
		}
		catch(Exception e){
			ISAbstractModule.getLogger().log(Level.WARNING, "Unable to get cell object!", e);
			return;
		}
		
		storinfo=usedbytes+"/"+getStorageBytes(par1ItemStack)+" Bytes";
		par3List.add(storinfo);
		
		if(getPlaceableItem(par1ItemStack)!=null){
		nextblockinfo=getPlaceableItem(par1ItemStack).getItem().getItemDisplayName(getPlaceableItem(par1ItemStack).getItemStack());
		par3List.add(nextblockinfo);
		}
		super.addInformation(par1ItemStack, par2EntityPlayer, par3List, par4);
	}

    @Override
    public int BytePerType(ItemStack iscellItem) {
        return getStorageBytes(iscellItem) / 128;
    }

    @Override
    public int getBytes(ItemStack cellItem) {
        return getStorageBytes(cellItem);
    }

    @Override
    public String getItemDisplayName(ItemStack par1ItemStack) {
        if (IndustrialScience.isAeinstalled()) {
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
        return getStorageAmount(item);
    }

    @Override
    public float getStrVsBlock(ItemStack par1ItemStack, Block par2Block) {
        float str = super.getStrVsBlock(par1ItemStack, par2Block);
        if (str == 1.0F)
            return 0F;
        return str;
    }

    public EnumToolMaterial getToolMaterial() {
        return toolMaterial;
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
    public boolean onBlockStartBreak(ItemStack itemstack, int x, int y, int z,
            EntityPlayer player) {
        if (IndustrialScience.isAeinstalled()) {
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
    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world,
            int x, int y, int z, int side, float clickX, float clickY,
            float clickZ) {
        if (IndustrialScience.isAeinstalled()) {
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
	@Override
    public boolean storableInStorageCell() {
        return true;
    }
    
}
