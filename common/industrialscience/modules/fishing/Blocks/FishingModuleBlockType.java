package industrialscience.modules.fishing.Blocks;

import industrialscience.ISBlockInterface;
import industrialscience.TextureGenerator;
import industrialscience.modules.fishing.Items.FishingModuleBlockItem;
import industrialscience.modules.fishing.TileEntities.BasicFishTrapTileEntity;
import industrialscience.modules.fishing.TileEntities.BasicLobsterTrapTileEntity;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public enum FishingModuleBlockType {
    SIMPLELOBSTERTRAP("Lobster Trap", "simplelobstertrap.png",
            BasicLobsterTrapTileEntity.class, new TextureGenerator() {
                Icon texture = null;

                @Override
                public void registerIcons(IconRegister par1IconRegister) {
                    texture = par1IconRegister
                            .registerIcon("industrialscience:lobstertrap");
                }

                @Override
                public Icon getIcon(int i) {
                    return texture;
                }
            }, new ISBlockInterface() {

                @Override
                public boolean onBlockActivated(World world, int x, int y,
                        int z, EntityPlayer player, int par6, float par7,
                        float par8, float par9) {
                    // TODO Auto-generated method stub
                    return false;
                }

                @Override
                public void breakBlock(World world, int x, int y, int z,
                        int par5, int par6) {
                    // TODO Auto-generated method stub

                }

            }), SIMPLEFISHTRAP("Simple Fishtrap", "simplefishtrap.png",
            BasicFishTrapTileEntity.class, new TextureGenerator() {
                Icon texture = null;

                @Override
                public Icon getIcon(int i) {
                    return texture;
                }

                @Override
                public void registerIcons(IconRegister par1IconRegister) {
                    texture = par1IconRegister.registerIcon("trapdoor");

                }

            }, new ISBlockInterface() {

                @Override
                public boolean onBlockActivated(World world, int x, int y,
                        int z, EntityPlayer player, int par6, float par7,
                        float par8, float par9) {
                    BasicFishTrapTileEntity traptile = (BasicFishTrapTileEntity) world
                            .getBlockTileEntity(x, y, z);
                    if (traptile == null) {
                    	return false;
                    		}
                        if (!traptile.isStructureValid()&&traptile.isStructureProperlyFormed()) {
                                traptile.activate();
                                return true;
                        }
                    return false;
                }

                @Override
                public void breakBlock(World world, int x, int y, int z,
                        int par5, int par6) {

                }

            });
    private String friendlyname;
    private String modelfile;
    public TextureGenerator texturegen;
    private ISBlockInterface blockactivator;

    public String getReadableName() {
        return friendlyname;
    }

    public String getModelfile() {
        return modelfile;
    }

    public Class<? extends TileEntity> getTileentity() {
        return tileentity;
    }

    public Class<? extends TileEntity> tileentity;

    private FishingModuleBlockType(String name, String modelfile,
            Class<? extends TileEntity> tileentity,
            TextureGenerator texturegen, ISBlockInterface iSBlockInterface) {
        friendlyname = name;
        this.modelfile = modelfile;
        this.tileentity = tileentity;
        this.texturegen = texturegen;
        this.blockactivator = iSBlockInterface;
    }

    public static TileEntity getEntity(int metadata) {
        try {
            TileEntity te = values()[metadata].getTileentity().newInstance();
            return te;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Icon getIcon(int i, int metadata) {
        return values()[metadata].texturegen.getIcon(i);
    }

    public static void registerIcons(IconRegister par1IconRegister) {
        for (FishingModuleBlockType BlockTyp : FishingModuleBlockType.values()) {
            BlockTyp.texturegen.registerIcons(par1IconRegister);
        }

    }

    public static void register(Block fishingblock, String prefix) {
        GameRegistry.registerBlock(fishingblock, FishingModuleBlockItem.class, prefix
                + fishingblock.getUnlocalizedName());
        for (FishingModuleBlockType typ : FishingModuleBlockType.values()) {
            GameRegistry.registerTileEntityWithAlternatives(
                    typ.getTileentity(), prefix + typ.name(), typ.name());
            LanguageRegistry.addName(
                    new ItemStack(fishingblock, 1, typ.ordinal()),
                    typ.getReadableName());
        }

    }

    public boolean activate(World world, int x, int y, int z,
            EntityPlayer player, int par6, float par7, float par8, float par9) {
        return blockactivator.onBlockActivated(world, x, y, z, player, par6,
                par7, par8, par9);

    }

    public void breakBlock(World world, int x, int y, int z, int par5, int par6) {
        blockactivator.breakBlock(world, x, y, z, par5, par6);
    }
}
