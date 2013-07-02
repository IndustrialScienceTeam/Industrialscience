package industrialscience.modules.fishing;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import industrialscience.TextureGenerator;

public enum FishingBlockTyp {
    SIMPLEFISHTRAP("Simple Fishtrap","simplefishtrap.png",Fishtraptile.class, new TextureGenerator(){
        Icon texture=null;
        @Override
        public Icon getIcon(int i) {
            return texture;
        }

        @Override
        public void registerIcons(IconRegister par1IconRegister) {
           texture=par1IconRegister.registerIcon("trapdoor");
            
        }
        
    });
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


    public Class<? extends TileEntity> tileentity;

    private FishingBlockTyp(String name, String modelfile,
            Class<? extends TileEntity> tileentity,
            TextureGenerator texturegen) {
        friendlyname = name;
        this.modelfile = modelfile;
        this.tileentity = tileentity;
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
        for (FishingBlockTyp BlockTyp : FishingBlockTyp.values()) {
            BlockTyp.texturegen.registerIcons(par1IconRegister);
        }

    }
}
