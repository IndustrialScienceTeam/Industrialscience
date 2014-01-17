package industrialscience.modules.fishing.TileEntities;

import java.util.Random;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BasicFishTrapTileEntity extends AbstractFishTrapTileEntity implements IInventory {
    private static final int FISHSLOT = 1;
    private int coreX;
    private int coreY;
    private int coreZ;
    private boolean isCore = false;
    private Cordinate[] StructureBlocks = null;
    private boolean StructureIsValid = false;
    public BasicFishTrapTileEntity() {
        super(2, 64, "Basic Fishtrap");
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

    public void destroyStructure() {

    }

    @Override
    public void doUpdateTick(World world, int x, int y, int z, Random random) {
        if (isCore)
            addFish(1, 4, 10, 5, FISHSLOT);

    }

    public int getCoreX() {
        return coreX;
    }

    public int getCoreY() {
        return coreY;
    }

    public int getCoreZ() {
        return coreZ;
    }

    public Cordinate[] getStructureBlocks() {
        return StructureBlocks;
    }

    @Override
    public boolean isItemValidForSlot(int i, ItemStack itemstack) {
        // TODO Auto-generated method stub
        return false;
    }

    public boolean isStructureProperlyFormed() {
        // TODO Auto-generated method stub
        return false;
    }

    public boolean isStructureValid() {
        return StructureIsValid;
    }

    public void setCoreX(int coreX) {
        this.coreX = coreX;
    }

    public void setCoreY(int coreY) {
        this.coreY = coreY;
    }

    public void setCoreZ(int coreZ) {
        this.coreZ = coreZ;
    }

    public void setStructureBlocks() {
    }

}
