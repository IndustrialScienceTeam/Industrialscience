package industrialscience.modules.fishing.TileEntities;

import java.util.Random;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BasicFishTrapTileEntity extends AbstractFishTrapTileEntity implements IInventory {
    private static final int FISHSLOT = 1;
    private boolean StructureIsValid = false;
    private boolean isCore = false;
    private int coreX;
    private int coreY;
    private int coreZ;
    private Cordinate[] StructureBlocks = null;

    public BasicFishTrapTileEntity() {
        super(2, 64, "Basic Fishtrap");
    }

    public void destroyStructure() {

    }

    @Override
    public void doUpdateTick(World world, int x, int y, int z, Random random) {
        if (isCore)
            addFish(1, 4, 10, 5, FISHSLOT);

    }

    public boolean isStructureValid() {
        return StructureIsValid;
    }

    public boolean isStructureProperlyFormed() {
        // TODO Auto-generated method stub
        return false;
    }

    public void activate() {
        if (isStructureProperlyFormed()) {
            isCore = true;
            Cordinate[] structureBlocks = getStructureBlocks();
            for (int i = 0; i < structureBlocks.length; i++) {
                Cordinate c = StructureBlocks[i];
                TileEntity tileentity = worldObj.getBlockTileEntity(c.x, c.y,
                        c.z);
                if (tileentity != null) {
                    BasicFishTrapTileEntity structuretileentity = (BasicFishTrapTileEntity) tileentity;
                    structuretileentity.setCoreX(xCoord);
                    structuretileentity.setCoreY(yCoord);
                    structuretileentity.setCoreZ(zCoord);

                }
            }
        }
    }

    public Cordinate[] getStructureBlocks() {
        return StructureBlocks;
    }

    public void setStructureBlocks() {
    }

    public int getCoreX() {
        return coreX;
    }

    public void setCoreX(int coreX) {
        this.coreX = coreX;
    }

    public int getCoreY() {
        return coreY;
    }

    public void setCoreY(int coreY) {
        this.coreY = coreY;
    }

    public int getCoreZ() {
        return coreZ;
    }

    public void setCoreZ(int coreZ) {
        this.coreZ = coreZ;
    }

    @Override
    public boolean isItemValidForSlot(int i, ItemStack itemstack) {
        // TODO Auto-generated method stub
        return false;
    }

}
