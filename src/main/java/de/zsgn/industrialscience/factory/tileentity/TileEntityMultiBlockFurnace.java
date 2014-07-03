package de.zsgn.industrialscience.factory.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntityFurnace;
import de.zsgn.industrialscience.RelativeCoordinate;
import de.zsgn.industrialscience.factory.SmeltingRegristry;

public class TileEntityMultiBlockFurnace extends
ITileEntityMultiBlockController implements IHatchSupport{
    public static final int INPUTSLOT=0;
    public static final int FUELSLOT=1;
    public static final int OUTPUTSLOT=2;
    public ItemStack[] furnaceslots=new ItemStack[3];
    protected final RelativeCoordinate[] itemhatchcoords;
    protected final RelativeCoordinate[] interfacehatchcoords;
    protected int deftemperature=20;
    protected float temperature=20;
    protected int currenfuelburntime=0;
    protected int cookedticks=0;

    public TileEntityMultiBlockFurnace(int deftemperature, RelativeCoordinate[] itemhatchcoords, RelativeCoordinate[] interfacehatchcoords) {
        super();
        this.deftemperature=deftemperature;
        temperature=deftemperature;
        this.itemhatchcoords=itemhatchcoords;
        this.interfacehatchcoords=interfacehatchcoords;
    }

    @Override
    public int getSizeInventory() {
        return furnaceslots.length;
    }

    @Override
    public void updateEntity() {
        super.updateEntity();
        if(currenfuelburntime>0){
            currenfuelburntime--;
            temperature=temperature+0.5F;
        }else if(temperature>deftemperature){
            temperature=temperature-(activepart? 0.05F : 0.1F);
        }
        if(currenfuelburntime==0&&furnaceslots[FUELSLOT]!=null){
            currenfuelburntime=getItemBurnTime(furnaceslots[FUELSLOT]);
            furnaceslots[FUELSLOT].stackSize--;
            if(furnaceslots[FUELSLOT].stackSize==0){
                furnaceslots[FUELSLOT]=null;
            }
        }
        if(canSmelt()){
            cookedticks++;
            if(cookedticks==getCookTime()){
                smeltItem();
            }
        }else if (cookedticks>0) {
            cookedticks=0;
        }

    }

    protected void smeltItem() {
       if (canSmelt()) {
           ItemStack result = SmeltingRegristry.getSmeltingResult(furnaceslots[INPUTSLOT]);
           if(furnaceslots[OUTPUTSLOT]==null){
               furnaceslots[OUTPUTSLOT]=result.copy();
           }else if (furnaceslots[OUTPUTSLOT].getItem()==result.getItem()) {
            furnaceslots[OUTPUTSLOT].stackSize+=result.stackSize;
        }
           furnaceslots[INPUTSLOT].stackSize--;
           if(furnaceslots[INPUTSLOT].stackSize==0){
               furnaceslots[INPUTSLOT]=null;
           }
    }
        
    }

    protected int getCookTime() {
        return 200;
    }

    protected boolean canSmelt() {
        if (this.furnaceslots[INPUTSLOT] == null)
        {
            return false;
        }
        else
        {
            ItemStack result = SmeltingRegristry.getSmeltingResult(furnaceslots[INPUTSLOT]);
            if (result == null) return false;
            if (temperature<SmeltingRegristry.getSmeltTemp(furnaceslots[INPUTSLOT])) return false;
            if (furnaceslots[OUTPUTSLOT] == null) return true;
            if (!furnaceslots[OUTPUTSLOT].isItemEqual(result)) return false;
            int resultstacksize = furnaceslots[OUTPUTSLOT].stackSize + result.stackSize;
            return resultstacksize <= getInventoryStackLimit() && resultstacksize <= 
                    furnaceslots[OUTPUTSLOT].getMaxStackSize(); 
        }
    }

    protected int getItemBurnTime(ItemStack itemStack) {
        if(itemStack!=null&&itemStack.getItem()!=Items.lava_bucket){
            return TileEntityFurnace.getItemBurnTime(itemStack);
        }else{
            return 0;
        }
    }

    @Override
    public ItemStack getStackInSlot(int var1) {
        return furnaceslots[var1];
    }

    @Override
    public ItemStack decrStackSize(int var1, int var2) {
        if(furnaceslots[var1]!=null){
            ItemStack result;
            if(furnaceslots[var1].stackSize<=var2){
                result=furnaceslots[var1];
                furnaceslots[var1]=null;
                return result;
            }else {
                result=furnaceslots[var1].splitStack(var2);
                if(furnaceslots[var1].stackSize==0){
                    furnaceslots[var1]=null;
                }
                return null;
            }
        }else {
            return null;
        }
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int var1) {
        if(furnaceslots[var1]!=null){
            ItemStack result=furnaceslots[var1];
            furnaceslots[var1]=null;
            return result;
        }
        return null;
    }

    @Override
    public void setInventorySlotContents(int var1, ItemStack var2) {
        furnaceslots[var1]=var2;
        if (var2!= null && var2.stackSize > this.getInventoryStackLimit()){
            var2.stackSize = this.getInventoryStackLimit();
        }

    }

    @Override
    public String getInventoryName() {
        return "";
    }

    @Override
    public boolean hasCustomInventoryName() {
        return false;
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer var1) {
        return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : var1.getDistanceSq(this.xCoord + 0.5D, this.yCoord + 0.5D, this.zCoord + 0.5D) <= 64.0D;
    }

    @Override
    public void openInventory() {
    }

    @Override
    public void closeInventory() {
    }

    @Override
    public boolean isItemValidForSlot(int var1, ItemStack var2) {
        if(var1==INPUTSLOT&&SmeltingRegristry.getSmeltingResult(var2)!=null){
            return true;
        }else if (var1==FUELSLOT&&getItemBurnTime(var2)>0) {
            return true;
        }else {
            return false;
        }
    }

    @Override
    public RelativeCoordinate[] getRelativeInterfaceHatchCoords() {
        return interfacehatchcoords;
    }


    @Override
    public boolean canExtractItem(int var1, ItemStack var2) {
        return var1==OUTPUTSLOT;
    }

    @Override
    public RelativeCoordinate[] getRelativeItemHatchCoords() {
        return itemhatchcoords;  
    }

    @Override
    public int[] getSlots() {
        return new int[]{FUELSLOT,INPUTSLOT,OUTPUTSLOT};
    }

    @Override
    public void readFromNBT(NBTTagCompound tagCompound) {
        super.readFromNBT(tagCompound);
        NBTTagList nbttaglist = tagCompound.getTagList("Items", 10);
        this.furnaceslots = new ItemStack[this.getSizeInventory()];

        for (int i = 0; i < nbttaglist.tagCount(); ++i)
        {
            NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
            byte b0 = nbttagcompound1.getByte("Slot");

            if (b0 >= 0 && b0 < this.furnaceslots.length)
            {
                this.furnaceslots[b0] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
            }
        }
        temperature=tagCompound.getFloat("temperature");
        currenfuelburntime=tagCompound.getInteger("currenfuelburntime");
        cookedticks=tagCompound.getInteger("cookedticks");
    }

    @Override
    public void writeToNBT(NBTTagCompound tagCompound) {
        super.writeToNBT(tagCompound);
        tagCompound.setFloat("temperature", temperature);
        tagCompound.setInteger("currenfuelburntime", currenfuelburntime);
        tagCompound.setInteger("cookedticks", cookedticks);
        NBTTagList nbttaglist = new NBTTagList();

        for (int i = 0; i < this.furnaceslots.length; ++i)
        {
            if (this.furnaceslots[i] != null)
            {
                NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                nbttagcompound1.setByte("Slot", (byte)i);
                this.furnaceslots[i].writeToNBT(nbttagcompound1);
                nbttaglist.appendTag(nbttagcompound1);
            }
        }

        tagCompound.setTag("Items", nbttaglist);
    }

    public float getTemperature() {
        return temperature;
    }


}
