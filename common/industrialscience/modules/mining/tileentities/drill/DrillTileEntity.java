package industrialscience.modules.mining.tileentities.drill;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import industrialscience.ICDirection;
import industrialscience.modules.mining.borersystem.IBorer;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public abstract class DrillTileEntity extends TileEntity implements IBorer{
protected int speed=1;
protected ICDirection movmentdir=ICDirection.YN;
private int waitticks=0;

@Override
public boolean bore(ItemStack borer) {
	if(!worldObj.isRemote){
	int[] goalcoords=movmentdir.applyToCoords(new int[]{xCoord,yCoord,zCoord});
	if(isDrillAbleToMineBlock(worldObj.getBlockMaterial(goalcoords[0],goalcoords[1],goalcoords[2]))){
        int blockID = worldObj.getBlockId(goalcoords[0],goalcoords[1],goalcoords[2]);
        int meta = worldObj.getBlockMetadata(goalcoords[0],goalcoords[1],goalcoords[2]);
        Block block = Block.blocksList[blockID];
        if (block == null || blockID < 1)
            return false;
        Random rnd= new Random();
        ItemStack orestack = new ItemStack(block.idDropped(meta, rnd,
                0), block.quantityDropped(meta, 0, rnd),
                block.damageDropped(meta));
        worldObj.setBlockToAir(goalcoords[0],goalcoords[1],goalcoords[2]);
        worldObj.playAuxSFX(2001, goalcoords[0],goalcoords[1],goalcoords[2], blockID + (meta << 12));
        System.out.println("bla:"+orestack.toString());
	}
	}
	return false;
}

@Override
public void updateEntity() {
	if(waitticks==40){
		bore(null);
		waitticks=0;
	}
	waitticks++;
}

protected abstract boolean isDrillAbleToMineBlock(Material material);

public int getSpeed() {
	return speed;
}

public void setSpeed(int speed) {
	this.speed = speed;
}

}
