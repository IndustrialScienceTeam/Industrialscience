package industrialscience.modules.fishing.TileEntities;

import industrialscience.ISIInventory;

import industrialscience.ISIInventory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Random;
import java.util.Set;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;


public abstract class AbstractFishTrapTileEntity extends ISIInventory{

    protected AbstractFishTrapTileEntity(int size, int stacklimit, String InvName) {
		super(size, stacklimit, InvName);
	}

	@Override
    public boolean isUseableByPlayer(EntityPlayer entityplayer) {
        return worldObj.getBlockTileEntity(xCoord, yCoord, zCoord) == this
                && entityplayer.getDistanceSq(xCoord + 0.5, yCoord + 0.5,
                        zCoord + 0.5) < 64;
    }

    protected class Cordinate {
        int x;
        int y;
        int z;

        Cordinate(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }

        @Override
        public boolean equals(Object e) {
            if (!(e instanceof Cordinate)) {
				return false;
			}
            Cordinate c = (Cordinate) e;
            if (x != c.getX() || y != c.getY() || z != c.getZ()) {
				return false;
			}
            return true;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public int getZ() {
            return z;
        }

    }

    protected class Coordinateset implements Set<Cordinate> {
        private ArrayList<Cordinate> list = new ArrayList<Cordinate>();

        @Override
        public boolean add(Cordinate e) {
            if (!isAlredayInList(e)) {
				return list.add(e);
			}
            return false;
        }

        private boolean isAlredayInList(Cordinate e) {
            for (Iterator<Cordinate> iterator = list.iterator(); iterator
                    .hasNext();) {
                Cordinate cordinate = iterator.next();
                if (cordinate.equals(e)) {
					return true;
				}
            }
            return false;
        }

        @Override
        public boolean addAll(Collection<? extends Cordinate> c) {
            return list.addAll(c);
        }

        @Override
        public void clear() {
            list.clear();
        }

        @Override
        public Object clone() {
            return list.clone();
        }

        @Override
        public boolean contains(Object o) {
            return list.contains(o);
        }

        @Override
        public boolean containsAll(Collection<?> arg0) {
            return list.containsAll(arg0);
        }

        public void ensureCapacity(int minCapacity) {
            list.ensureCapacity(minCapacity);
        }

        @Override
        public int hashCode() {
            return list.hashCode();
        }

        public int indexOf(Object o) {
            return list.indexOf(o);
        }

        @Override
        public boolean isEmpty() {
            return list.isEmpty();
        }

        @Override
        public Iterator<Cordinate> iterator() {
            return list.iterator();
        }

        public int lastIndexOf(Object o) {
            return list.lastIndexOf(o);
        }

        public ListIterator<Cordinate> listIterator() {
            return list.listIterator();
        }

        public ListIterator<Cordinate> listIterator(int arg0) {
            return list.listIterator(arg0);
        }

        public Cordinate remove(int index) {
            return list.remove(index);
        }

        @Override
        public boolean remove(Object o) {
            return list.remove(o);
        }

        @Override
        public boolean removeAll(Collection<?> arg0) {
            return list.removeAll(arg0);
        }

        @Override
        public boolean retainAll(Collection<?> arg0) {
            return list.retainAll(arg0);
        }

        @Override
        public int size() {
            return list.size();
        }

        @Override
        public Object[] toArray() {
            return list.toArray();
        }

        @Override
        public <T> T[] toArray(T[] a) {
            return list.toArray(a);
        }

        @Override
        public String toString() {
            return list.toString();
        }

        public void trimToSize() {
            list.trimToSize();
        }
    }

    protected int countWater(int range) {
        Set<Cordinate> waterblocks = new Coordinateset();
        for (int i = 1; i < range + 1; i++) {
            if (worldObj.getBlockId(xCoord + i, yCoord, zCoord) == 9) {
                waterblocks.add(new Cordinate(xCoord + i, yCoord, zCoord));
                if (worldObj.getBlockId(xCoord + i, yCoord + 1, zCoord) == 9) {
                    waterblocks.add(new Cordinate(xCoord + i, yCoord + 1,
                            zCoord));
                }
                if (worldObj.getBlockId(xCoord + i, yCoord - 1, zCoord) == 9) {
                    waterblocks.add(new Cordinate(xCoord + i, yCoord - 1,
                            zCoord));
                }
                if (worldObj.getBlockId(xCoord + i, yCoord, zCoord - 1) == 9) {
                    waterblocks.add(new Cordinate(xCoord + i, yCoord,
                            zCoord - 1));
                }
                if (worldObj.getBlockId(xCoord + i, yCoord, zCoord + 1) == 9) {
                    waterblocks.add(new Cordinate(xCoord + i, yCoord,
                            zCoord + 1));
                }

            } else {
                break;
            }

        }
        for (int i = 1; i < range + 1; i++) {
            if (worldObj.getBlockId(xCoord, yCoord + i, zCoord) == 9) {
                waterblocks.add(new Cordinate(xCoord, yCoord + i, zCoord));
                if (worldObj.getBlockId(xCoord + 1, yCoord + i, zCoord) == 9) {
                    waterblocks.add(new Cordinate(xCoord + 1, yCoord + i,
                            zCoord));
                }
                if (worldObj.getBlockId(xCoord - 1, yCoord + i, zCoord) == 9) {
                    waterblocks.add(new Cordinate(xCoord - 1, yCoord + i,
                            zCoord));
                }
                if (worldObj.getBlockId(xCoord, yCoord + i, zCoord - 1) == 9) {
                    waterblocks.add(new Cordinate(xCoord, yCoord + i,
                            zCoord - 1));
                }
                if (worldObj.getBlockId(xCoord, yCoord + i, zCoord + 1) == 9) {
                    waterblocks.add(new Cordinate(xCoord, yCoord + i,
                            zCoord + 1));
                }

            } else {
                break;
            }

        }
        for (int i = 1; i < range + 1; i++) {
            if (worldObj.getBlockId(xCoord - i, yCoord, zCoord) == 9) {
                waterblocks.add(new Cordinate(xCoord - i, yCoord, zCoord));
                if (worldObj.getBlockId(xCoord - i, yCoord + 1, zCoord) == 9) {
                    waterblocks.add(new Cordinate(xCoord - i, yCoord + 1,
                            zCoord));
                }
                if (worldObj.getBlockId(xCoord - i, yCoord - 1, zCoord) == 9) {
                    waterblocks.add(new Cordinate(xCoord - i, yCoord - 1,
                            zCoord));
                }
                if (worldObj.getBlockId(xCoord - i, yCoord, zCoord - 1) == 9) {
                    waterblocks.add(new Cordinate(xCoord - i, yCoord,
                            zCoord - 1));
                }
                if (worldObj.getBlockId(xCoord - i, yCoord, zCoord + 1) == 9) {
                    waterblocks.add(new Cordinate(xCoord - i, yCoord,
                            zCoord + 1));
                }

            } else {
                break;
            }

        }
        for (int i = 1; i < range + 1; i++) {
            if (worldObj.getBlockId(xCoord, yCoord - i, zCoord) == 9) {
                waterblocks.add(new Cordinate(xCoord, yCoord - i, zCoord));
                if (worldObj.getBlockId(xCoord + 1, yCoord - i, zCoord) == 9) {
                    waterblocks.add(new Cordinate(xCoord + 1, yCoord - i,
                            zCoord));
                }
                if (worldObj.getBlockId(xCoord - 1, yCoord - i, zCoord) == 9) {
                    waterblocks.add(new Cordinate(xCoord - 1, yCoord - i,
                            zCoord));
                }
                if (worldObj.getBlockId(xCoord, yCoord - i, zCoord - 1) == 9) {
                    waterblocks.add(new Cordinate(xCoord, yCoord - i, zCoord));
                }
                if (worldObj.getBlockId(xCoord, yCoord - i, zCoord + 1) == 9) {
                    waterblocks.add(new Cordinate(xCoord, yCoord - i,
                            zCoord + 1));
                }

            } else {
                break;
            }

        }
        for (int i = 1; i < range + 1; i++) {
            if (worldObj.getBlockId(xCoord, yCoord, zCoord - i) == 9) {
                waterblocks.add(new Cordinate(xCoord, yCoord, zCoord - i));
                if (worldObj.getBlockId(xCoord + 1, yCoord, zCoord - i) == 9) {
                    waterblocks.add(new Cordinate(xCoord + 1, yCoord, zCoord
                            - i));
                }
                if (worldObj.getBlockId(xCoord - 1, yCoord, zCoord - i) == 9) {
                    waterblocks.add(new Cordinate(xCoord - 1, yCoord, zCoord
                            - i));
                }
                if (worldObj.getBlockId(xCoord, yCoord + 1, zCoord - i) == 9) {
                    waterblocks.add(new Cordinate(xCoord, yCoord + 1, zCoord
                            - i));
                }
                if (worldObj.getBlockId(xCoord, yCoord - 1, zCoord - i) == 9) {
                    waterblocks.add(new Cordinate(xCoord, yCoord - 1, zCoord
                            - i));
                }

            } else {
                break;
            }

        }
        for (int i = 1; i < range + 1; i++) {
            if (worldObj.getBlockId(xCoord, yCoord, zCoord + i) == 9) {
                waterblocks.add(new Cordinate(xCoord, yCoord, zCoord + i));
                if (worldObj.getBlockId(xCoord + 1, yCoord, zCoord + i) == 9) {
                    waterblocks.add(new Cordinate(xCoord + 1, yCoord, zCoord
                            + i));
                }
                if (worldObj.getBlockId(xCoord - 1, yCoord, zCoord + i) == 9) {
                    waterblocks.add(new Cordinate(xCoord - 1, yCoord, zCoord
                            + i));
                }
                if (worldObj.getBlockId(xCoord, yCoord - 1, zCoord + i) == 9) {
                    waterblocks.add(new Cordinate(xCoord, yCoord - 1, zCoord
                            + i));
                }
                if (worldObj.getBlockId(xCoord, yCoord + 1, zCoord + i) == 9) {
                    waterblocks.add(new Cordinate(xCoord, yCoord + 1, zCoord
                            + i));
                }

            } else {
                break;
            }

        }
        return waterblocks.size();

    }

    protected void addFish(int fishamout, int neededwater, int range,
            int waterforextrafish, int fishslot) {
        int waterblocks = countWater(range);
        int fishes = fishamout;
        if (waterblocks >= neededwater) {
            waterblocks = waterblocks - neededwater;
            if (waterblocks > 0) {

            }
            ItemStack stack = null;
            if (Inventory[fishslot] != null) {
                stack = Inventory[fishslot];
                stack.stackSize = stack.stackSize + fishes;
            } else {
                stack = new ItemStack(Item.fishRaw, fishes);
            }
            Inventory[fishslot] = stack;
        }
    }

    public abstract void doUpdateTick(World world, int x, int y, int z,
            Random random);
}