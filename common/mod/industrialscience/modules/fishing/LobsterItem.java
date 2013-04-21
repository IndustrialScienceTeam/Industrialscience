package mod.industrialscience.modules.fishing;


import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.util.Icon;

public class LobsterItem extends Item {

	protected Icon texture;    
	public LobsterItem(int id) {
                super(id);
               
                // Constructor Configuration
                maxStackSize = 64;
                
                setUnlocalizedName("genericItem");
        }
       
	public void registerIcons(IconRegister par1IconRegister)
	{
	this.texture= par1IconRegister.registerIcon("industrialscience:lobstertrap");
	}
}