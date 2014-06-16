package de.zsgn.industrialscience.block;


import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import de.zsgn.industrialscience.IndustrialScience;
import de.zsgn.industrialscience.tileentity.TileEntityMysteriousPortal;

public class BlockMysteriousPortal extends BlockContainer {
    @SideOnly(Side.CLIENT)
    private IIcon bottomtexture=null;
    @SideOnly(Side.CLIENT)
    private IIcon toptexture=null;
    @SideOnly(Side.CLIENT)
    private IIcon sidetexture=null;
    public BlockMysteriousPortal() {
        super(Material.iron);
        setCreativeTab(IndustrialScience.getInstance().getCreativetab());
        setBlockName("mysteriousportal");
        setHardness(3.0F);
    }

    @Override
    public TileEntity createNewTileEntity(World var1, int var2) {
        return new TileEntityMysteriousPortal();
    }

    @Override
    public boolean onBlockActivated(World world, int x,
            int y, int z, EntityPlayer player,
            int side, float xOffset, float yOffset,
            float zOffset) {
        if(!world.isRemote&&world.getTileEntity(x, y, z) instanceof TileEntityMysteriousPortal){
            TileEntityMysteriousPortal tileentity=(TileEntityMysteriousPortal)world.getTileEntity(x, y, z);
            if(!tileentity.isUseable()&&(player.inventory.getStackInSlot(player.inventory.currentItem)!=null&&player.inventory.getStackInSlot(player.inventory.currentItem).getItem()==IndustrialScience.getInstance().getItemancienttechnology())&&side==1){
                tileentity.setTechnologycompund(player.inventory.getStackInSlot(player.inventory.currentItem).getTagCompound().copy());
                --player.inventory.getStackInSlot(player.inventory.currentItem).stackSize;
                tileentity.setUseable(true);
                player.addChatMessage(new ChatComponentText(StatCollector.translateToLocal("mysteriousportal_activated")));
                return true;
            }
            return false;
        }
        return false;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int metadata) {
        switch (side) {
        case 0:
            return bottomtexture;
        case 1:
            return toptexture;
        default:
            return sidetexture;
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister) {
        bottomtexture=iconRegister.registerIcon(IndustrialScience.MODID + ":" + this.getUnlocalizedName().substring(5)+"_bottom");
        toptexture=iconRegister.registerIcon(IndustrialScience.MODID + ":" + this.getUnlocalizedName().substring(5)+"_top");
        sidetexture=iconRegister.registerIcon(IndustrialScience.MODID + ":" + this.getUnlocalizedName().substring(5)+"_side");
    }

    @Override
    public void breakBlock(World world, int x, int y,
            int z, Block block, int metadata) {
        if(!world.isRemote&&world.getTileEntity(x, y, z) instanceof TileEntityMysteriousPortal){
            TileEntityMysteriousPortal tileentity=(TileEntityMysteriousPortal)world.getTileEntity(x, y, z);
            if(tileentity.isUseable()){
                ItemStack technologyItemStack=new ItemStack(IndustrialScience.getInstance().getItemancienttechnology());
                if(tileentity.getTechnologycompund()!=null){
                    technologyItemStack.stackTagCompound=tileentity.getTechnologycompund();
                }
                EntityItem entityItem =new EntityItem(world, x, y+1, z, technologyItemStack);
                world.spawnEntityInWorld(entityItem);
            }
        }
        super.breakBlock(world, x, y, z,
                block, metadata);
    }

}
