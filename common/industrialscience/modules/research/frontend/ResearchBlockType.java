package industrialscience.modules.research.frontend;

import industrialscience.BlockUtils;
import industrialscience.ISBlockInterface;
import industrialscience.ISModel;
import industrialscience.IndustrialScience;
import industrialscience.TextureGenerator;
import industrialscience.modules.ResearchModule;
import industrialscience.modules.research.backend.Research;
import industrialscience.modules.research.backend.ResearchObject;
import industrialscience.modules.research.backend.Researchstep;
import industrialscience.modules.research.frontend.TileEntities.CopierTile;
import industrialscience.modules.research.frontend.TileEntities.ResearchDeskTile;
import industrialscience.modules.research.frontend.models.ResearchCopierModel;
import industrialscience.modules.research.frontend.models.ResearchDeskModel;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.FMLNetworkHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public enum ResearchBlockType {
    COPIER(
            "Research Copier",
            "copier.png",
            ResearchCopierModel.class,
            CopierTile.class,
            new TextureGenerator() {
                private Icon bottom;
                private Icon top;
                private Icon side;

                @Override
                public Icon getIcon(int i) {
                    switch (i) {
                        case 0:
                            return bottom;
                        case 1:
                            return top;
                        default:
                            return side;
                    }

                }

                @Override
                public void registerIcons(IconRegister par1IconRegister) {
                    side = par1IconRegister
                            .registerIcon("industrialscience:vannila_researchtable_sides");
                    bottom = par1IconRegister
                            .registerIcon("industrialscience:vannila_researchtable_bottom");
                    top = par1IconRegister.registerIcon("planks_jungle");

                }

            },
            new Research(
                    "Copying",
                    new String[] { "Writing", "Wooden things" },
                    Research.RESEARCH_CATEGORY + "_blocks",
                    null,
                    new Researchstep[] {
                            new Researchstep(
                                    0,
                                    new ResearchObject(
                                            new ItemStack(Item.paper)),
                                    "You have looked at your Researchbook and thought, how cool it would be to share and save your knowledge. You want to think more about this idea."),
                            new Researchstep(
                                    1,
                                    new ResearchObject(new ItemStack(
                                            ResearchModule.researchNote)),
                                    "You think the best way to copy researches is just to read your written results and write them down again. So simple.") },
                    null, null), new ISBlockInterface(){

						@Override
						public boolean onBlockActivated(World world, int x,
								int y, int z, EntityPlayer player, int par6,
								float par7, float par8, float par9) {
								FMLNetworkHandler.instance().openGui(player, IndustrialScience.instance, 0, world, x, y, z);
							return true;
						}

						@Override
						public void breakBlock(World world, int x, int y,
								int z, int par5, int par6) {
							BlockUtils.dropItems(world, x, y, z);
							
						}
            	
            }),
 RESEARCHDESK("Research Desk",
            "researchdesk.png", ResearchDeskModel.class,
            ResearchDeskTile.class, new TextureGenerator() {
                private Icon bottom;
                private Icon top;
                private Icon side;

                @Override
                public Icon getIcon(int i) {
                    switch (i) {
                        case 0:
                            return bottom;
                        case 1:
                            return top;
                        default:
                            return side;
                    }

                }

                @Override
                public void registerIcons(IconRegister par1IconRegister) {
                    side = par1IconRegister
                            .registerIcon("industrialscience:vannila_researchtable_sides");
                    bottom = par1IconRegister
                            .registerIcon("industrialscience:vannila_researchtable_bottom");
                    top = par1IconRegister.registerIcon("planks_oak");

                }

            }, null, new ISBlockInterface(){

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
					
				}});
    private Class<? extends ISModel> model;
    private String friendlyname;
    private String modelfile;
    public TextureGenerator texturegen;

    public String getReadableName() {
        return friendlyname;
    }

    public String getModelfile() {
        return modelfile;
    }

    public Class<? extends TileEntity> getTileentity() {
        return tileentity;
    }

    public Research getResearch() {
        return research;
    }

    public Class<? extends TileEntity> tileentity;
    private Research research;
	private ISBlockInterface blockinterface;

    private ResearchBlockType(String name, String modelfile,
            Class<? extends ISModel> model,
            Class<? extends TileEntity> tileentity,
            TextureGenerator texturegen, Research research,ISBlockInterface blockinterface) {
        friendlyname = name;
        this.modelfile = modelfile;
        this.tileentity = tileentity;
        this.research = research;
        this.texturegen = texturegen;
        this.model = model;
        this.blockinterface=blockinterface;
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
        for (ResearchBlockType researchBlockType : ResearchBlockType.values()) {
            researchBlockType.texturegen.registerIcons(par1IconRegister);
        }

    }

    public Class<? extends ISModel> getModel() {
        return model;
    }

    public static void register(Block researchBlock, String Prefix) {
        GameRegistry.registerBlock(researchBlock, ItemResearchBlock.class,
                Prefix + researchBlock.getUnlocalizedName());
        for (ResearchBlockType typ : ResearchBlockType.values()) {
            GameRegistry.registerTileEntityWithAlternatives(
                    typ.getTileentity(), Prefix + typ.name(), typ.name());
            LanguageRegistry.addName(
                    new ItemStack(researchBlock, 1, typ.ordinal()),
                    typ.getReadableName());
        }
        
    }
    public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
    {
    	return blockinterface.onBlockActivated(par1World, par2, par3, par4, par5EntityPlayer, par6, par7, par8, par9);
    }
    public void breakBlock(World world, int x, int y, int z, int i, int j) {
    	blockinterface.breakBlock(world, x, y,z, i, j);
    }
}
