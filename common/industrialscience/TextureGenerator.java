package industrialscience;
import industrialscience.TextureGenerator;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;

public abstract class TextureGenerator {
    
   public abstract Icon getIcon(int i);
   public abstract void registerIcons(IconRegister par1IconRegister);

}