package me.xpenguinx.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

/**



 */
public class ModelUtil {

    /**
     * Renders a baked model
     *
     * @param model the model to render
     */
    public static void renderModel(final IBakedModel model) {
        renderModelWithColor(model, -1);
    }

    /**
     * Renders a baked model with the specified color
     *
     * @param model the model to render
     * @param color the color to render the model with
     */
    @SideOnly(Side.CLIENT)
    public static void renderModelWithColor(final IBakedModel model, final int color) {
        GlStateManager.pushMatrix();
        //GlStateManager.translate(-0.5F, -0.5F, -0.5F);

        Minecraft.getMinecraft().getTextureManager().bindTexture(TextureMap.locationBlocksTexture);

        final Tessellator tessellator = Tessellator.getInstance();
        final WorldRenderer bufferbuilder = tessellator.getWorldRenderer();
        bufferbuilder.begin(7, DefaultVertexFormats.ITEM);

        for (final EnumFacing enumfacing : EnumFacing.values()) {
            renderQuadsColor(bufferbuilder, model.getFaceQuads(enumfacing), color);
        }

        renderQuadsColor(bufferbuilder, model.getGeneralQuads(), color);
        tessellator.draw();

        GlStateManager.popMatrix();
    }

    /**
     * Renders a list of quads with the specified color
     *
     * @param bufferbuilder the bufferbuilder to render the quads with
     * @param quads         a list with all the quads in it
     * @param color         the color to render the quads with
     */
    @SideOnly(Side.CLIENT)
    private static void renderQuadsColor(final WorldRenderer bufferbuilder, final List<BakedQuad> quads, int color) {

        int i = 0;
        for (final int j = quads.size(); i < j; ++i) {
            final BakedQuad bakedquad = quads.get(i);

            if ((color == -1) && bakedquad.hasTintIndex()) {
                if (EntityRenderer.anaglyphEnable) {
                    color = TextureUtil.anaglyphColor(color);
                }

                color = color | -0x1000000;
            }
            net.minecraftforge.client.model.pipeline.LightUtil.renderQuadColor(bufferbuilder, bakedquad, color);
        }
    }


}
