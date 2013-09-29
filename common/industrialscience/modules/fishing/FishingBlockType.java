package industrialscience.modules.fishing;

import industrialscience.ISBlockInterface;
import industrialscience.TextureGenerator;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public enum FishingBlockType {
    SIMPLELOBSTERTRAP("Lobster Trap", "simplelobstertrap.png",
            Lobster_traptile.class, new TextureGenerator() {
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
            Fishtraptile.class, new TextureGenerator() {
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
                    Fishtraptile traptile = (Fishtraptile) world
                            .getBlockTileEntity(x, y, z);
                    if (traptile != null) {
                        if (!traptile.isStructureValid()) {
                            if (traptile.isStructureProperlyFormed()) {
                                traptile.activate();
                            }
                        }
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

    private FishingBlockType(String name, String modelfile,
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
        for (FishingBlockType BlockTyp : FishingBlockType.values()) {
            BlockTyp.texturegen.registerIcons(par1IconRegister);
        }

    }

    public static void register(Block fishingblock, String prefix) {
        GameRegistry.registerBlock(fishingblock, ItemFishingBlock.class, prefix
                + fishingblock.getUnlocalizedName());
        for (FishingBlockType typ : FishingBlockType.values()) {
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
