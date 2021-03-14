package me.xpenguinx.layer;

import me.xpenguinx.Cosmetics;
import me.xpenguinx.util.ModelUtil;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.entity.EntityLivingBase;

import java.awt.*;

public class LayerChain implements LayerRenderer<EntityLivingBase> {

    IBakedModel model = Cosmetics.getChainBakedModel();

    public LayerChain(){

    }

    @Override
    public void doRenderLayer(EntityLivingBase entitylivingbaseIn, float p_177141_2_, float p_177141_3_, float partialTicks, float p_177141_5_, float p_177141_6_, float p_177141_7_, float scale) {
        if(model != null && !entitylivingbaseIn.isInvisible())
        {
            GlStateManager.pushMatrix();
            GlStateManager.scale(0.1F, 0.1F,0.1F);
            GlStateManager.enableBlend();
            float height = entitylivingbaseIn.height;
            if(entitylivingbaseIn.isSneaking()) {
                height += 1.925;
            }
            GlStateManager.translate(0.0F, height, -1.885F);
            GlStateManager.disableLighting();
            GlStateManager.rotate(90.0F, 1, 0, 0);
            GlStateManager.rotate(180.0F, 0, 0, 1);
            ModelUtil.renderModelWithColor(model, new Color(32,218,165, 255).getRGB());
            GlStateManager.enableLighting();
            GlStateManager.disableBlend();
            GlStateManager.popMatrix();
        }
    }

    @Override
    public boolean shouldCombineTextures() {
        return true;
    }
}
