package me.xpenguinx;

import me.xpenguinx.layer.LayerChain;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.client.model.IModel;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.client.model.obj.OBJModel;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

@Mod(name = Constants.MOD_ID, modid = Constants.MOD_ID, version = Constants.VERSION, clientSideOnly = true)
public class Cosmetics {

    private final Logger log = LogManager.getLogger(Cosmetics.class);

    private static IBakedModel chainModel, bandanaModel;

    private final Minecraft mc = Minecraft.getMinecraft();

    @Mod.EventHandler
    public void onPreInit(FMLPreInitializationEvent event) {
        OBJLoader.instance.addDomain(Constants.MOD_ID);
        MinecraftForge.EVENT_BUS.register(this);
        log.debug("Registered obj models");
    }

    @Mod.EventHandler
    public void onPostInit(FMLPostInitializationEvent event)  {
        addLayer(new LayerChain());
        //addLayer(new LayerHelmet());
        log.debug("Added custom layers");
    }

    @SubscribeEvent
    public void onModelBakeEvent(ModelBakeEvent event) {
        try {
            log.debug("Attempting to bake model");

            IModel model = OBJLoader.instance.loadModel(Constants.CHAIN_LOCATION);

            if (model instanceof OBJModel) {
                log.debug("Found chain obj model baking");
                chainModel = model.bake(
                        model.getDefaultState(),
                         DefaultVertexFormats.ITEM,
                         ModelLoader.defaultTextureGetter()
                 );
            }

            /*

            BANDANAS NOT FINISHED

            model = OBJLoader.instance.loadModel(Constants.BANDANA_LOCATION);

            if (model instanceof OBJModel) {
                log.info("Found bandana obj model baking");
                bandanaModel = model.bake(
                        model.getDefaultState(),
                        DefaultVertexFormats.ITEM,
                        ModelLoader.defaultTextureGetter()
                );
            }
            */

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addLayer(LayerRenderer<EntityLivingBase> layer) {
        Map<String, RenderPlayer> skinMap = mc.getRenderManager().getSkinMap();
        skinMap.get("default").addLayer(layer);
        skinMap.get("slim").addLayer(layer);
    }

    public static IBakedModel getChainBakedModel() {
        return chainModel;
    }

    public static IBakedModel getBandanaModel() {
        return bandanaModel;
    }

}
