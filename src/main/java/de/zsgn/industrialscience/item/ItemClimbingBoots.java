package de.zsgn.industrialscience.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import de.zsgn.industrialscience.IndustrialScience;

public class ItemClimbingBoots extends ItemArmor{
    
    public ItemClimbingBoots(){
        
        //first argument is the material of this armor , the second one is unknown , according to ItemArmor.class it is the renderIndex , I recommend using 0 , because it is working, the last one is the slot (0 = helmet , 1 = chestplate , 2 = leggings , 3 = boots)	
        super(ArmorMaterial.IRON,0,3);
        
        setCreativeTab(IndustrialScience.getInstance().getCreativetab());
        
        setUnlocalizedName("climbingboots");
        
        iconString =IndustrialScience.MODID + ":" + this.getUnlocalizedName().substring(5);
    }
    
    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
            return IndustrialScience.MODID + ":" + "textures/items/" + this.getUnlocalizedName().substring(5)+"_model.png";
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public ModelBiped getArmorModel(EntityLivingBase entityLiving,
    ItemStack itemStack, int armorSlot) {
        
        ModelBiped armorModel = null;
        if(itemStack != null){
            if(itemStack.getItem() instanceof ItemClimbingBoots){
                 climbingboots_model climbingboots = new climbingboots_model(1.0f);
                armorModel = climbingboots;
            }
            
            
            
        }
        if(armorModel != null){
            armorModel.bipedHead.showModel = armorSlot == 0;
            armorModel.bipedHeadwear.showModel = armorSlot == 0;
            armorModel.bipedBody.showModel = armorSlot == 1 || armorSlot == 2;
            armorModel.bipedRightArm.showModel = armorSlot == 1;
            armorModel.bipedLeftArm.showModel = armorSlot == 1;
            armorModel.bipedRightLeg.showModel = armorSlot == 2 || armorSlot == 3;
            armorModel.bipedLeftLeg.showModel = armorSlot == 2 || armorSlot == 3;

            armorModel.isSneak = entityLiving.isSneaking();
            armorModel.isRiding = entityLiving.isRiding();
            armorModel.isChild = entityLiving.isChild();
            //armorModel.heldItemRight = entityLiving.getCurrentItemOrArmor(0) != null ? 1 :0;
            if(entityLiving instanceof EntityPlayer){
            armorModel.aimedBow =((EntityPlayer)entityLiving).getItemInUseDuration() > 2;
            }
            return armorModel;
            }
            

            return null;
            }
            
    
    }
    
    

