package industrialscience.modules.research;

import industrialscience.modules.ResearchModule;
import industrialscience.modules.research.backend.model.Research;
import industrialscience.modules.research.backend.model.ResearchObject;
import industrialscience.modules.research.backend.model.Researchstep;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;

public enum ResearchBlockTyp {
    COPIER(
            "Research Copier",
            "copier.png",
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
                    top = par1IconRegister.registerIcon("wood_jungle");

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
                                    new ResearchObject(new ItemStack(
                                            Item.paper)),
                                    "You have looked at your Researchbook and thought, how cool it would be to share and save your knowledge. You want to think more about this idea"),
                            new Researchstep(
                                    1,
                                    new ResearchObject(new ItemStack(
                                            ResearchModule.researchNote)),
                                    "Yout think the best way to copy researches would be just read your written results and write them down again. So simple.") },
                    null, null)),
    RESEARCHDESK( "Research Desk",
            "researchdesk.png",
            ResearchDeskTile.class,
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
                    top = par1IconRegister.registerIcon("wood");

                }

            },null);
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

    private ResearchBlockTyp(String name, String modelfile,
            Class<? extends TileEntity> tileentity,
            TextureGenerator texturegen, Research research) {
        friendlyname = name;
        this.modelfile = modelfile;
        this.tileentity = tileentity;
        this.research = research;
        this.texturegen = texturegen;
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
        for (ResearchBlockTyp researchBlockTyp : ResearchBlockTyp.values()) {
            researchBlockTyp.texturegen.registerIcons(par1IconRegister);
        }

    }
}
