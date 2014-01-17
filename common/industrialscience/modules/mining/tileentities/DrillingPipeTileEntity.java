package industrialscience.modules.mining.tileentities;

import industrialscience.ICDirection;
import industrialscience.modules.mining.borersystem.IBorer;
import industrialscience.modules.mining.borersystem.IBorerItemReceiver;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class DrillingPipeTileEntity extends TileEntity implements IBorerItemReceiver, IBorer{
private ICDirection movmentdir;
private ItemStack transportstack=null;
@Override
public boolean bore(ItemStack drill){
	if(movmentdir.applyToTileEntity(this) instanceof IBorer){
		((IBorer)movmentdir.applyToTileEntity(this)).bore(drill);
		return true;
	}
	return false;
}
private boolean consumePower() {
	return true;
}
public boolean giveStack(){
	if(transportstack==null)
		return false;
	TileEntity receiver=movmentdir.getInverse().applyToTileEntity(this);
	if(receiver instanceof IBorerItemReceiver){
		if(((IBorerItemReceiver)receiver).receiveStack(transportstack)){
			transportstack=null;
			return true;
		}
	}
	return false;
}
@Override
public void readFromNBT(NBTTagCompound par1nbtTagCompound) {
	movmentdir=ICDirection.values()[par1nbtTagCompound.getInteger("MOVMENTDIR")];
	if(par1nbtTagCompound.getCompoundTag("TRANSPORTSTACK").hasNoTags()){
		transportstack=null;
		return;
	}
	ItemStack nbtItemStack= new ItemStack(Block.dirt);
	nbtItemStack.readFromNBT(par1nbtTagCompound.getCompoundTag("TRANSPORTSTACK"));
	transportstack=nbtItemStack.copy();
}
@Override
public boolean receiveStack(ItemStack transported){
	if(transportstack!=null|transported==null)
		return false;
	if(consumePower()){
		transportstack=transported.copy();
		return true;
	}
	return false;
		
	
}
@Override
public void writeToNBT(NBTTagCompound par1nbtTagCompound) {
	par1nbtTagCompound.setInteger("MOVMENTDIR", movmentdir.ordinal());
	NBTTagCompound transportstackNbtTagCompound = new NBTTagCompound("TRANSPORTSTACK");
	if(transportstack!=null){
	transportstack.writeToNBT(transportstackNbtTagCompound);
	par1nbtTagCompound.setTag(transportstackNbtTagCompound.getName(), transportstackNbtTagCompound);
	}
}
}
