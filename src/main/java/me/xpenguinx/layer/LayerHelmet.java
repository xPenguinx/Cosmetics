package me.xpenguinx.layer;

import me.xpenguinx.Constants;
import me.xpenguinx.Cosmetics;
import me.xpenguinx.util.ModelUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.entity.EntityLivingBase;

import java.awt.*;

public class LayerHelmet implements LayerRenderer<EntityLivingBase> {

    IBakedModel model = Cosmetics.getBandanaModel();

    public LayerHelmet(){

    }

    @Override
    public void doRenderLayer(EntityLivingBase entitylivingbaseIn, float p_177141_2_, float p_177141_3_, float partialTicks, float p_177141_5_, float p_177141_6_, float p_177141_7_, float scale) {
        if(model != null)
        {
            GlStateManager.pushMatrix();
            GlStateManager.color(1.0F,1.0F,1.0F);
            GlStateManager.scale(0.061F, 0.061F,0.061F);
            float height = -entitylivingbaseIn.height - 4.5F;
            if(entitylivingbaseIn.isSneaking()) {
                height -= 1.925;
            }
            Minecraft.getMinecraft().getTextureManager().bindTexture(Constants.BANDANA_TEXTURE);
            GlStateManager.translate(0, height, 0.0F);
            GlStateManager.rotate(180.0F, 1, 0, 0);
            GlStateManager.disableLighting();
            ModelUtil.renderModelWithColor(model, new Color(255,255,255,255).getRGB());
            GlStateManager.enableLighting();
            GlStateManager.popMatrix();
        }
    }

    @Override
    public boolean shouldCombineTextures() {
        return true;
    }
}
