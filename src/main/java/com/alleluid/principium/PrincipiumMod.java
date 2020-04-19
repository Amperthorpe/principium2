package com.alleluid.principium;

import com.alleluid.principium.client.render.ChestElytraRenderLayer;
import com.alleluid.principium.client.event.EventHandlerClient;
import com.alleluid.principium.network.ClickableHandler;
import com.alleluid.principium.network.NetworkSync;
import com.alleluid.principium.setup.*;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.*;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import top.theillusivec4.curios.api.CuriosAPI;
import top.theillusivec4.curios.api.imc.CurioIMCMessage;

import java.util.stream.Collectors;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("principium")
public class PrincipiumMod
{
    public static final String MODID = "principium";

    // `proxy` will be either server or client side, DistExecutor will execute the correct proxy code for the correct side.
    // https://youtu.be/DgY6kKf5rGU?list=PLmaTwVFUUXiBKYYSyrv_uPPoPZtEsCBVJ&t=1005
    @SuppressWarnings("Convert2MethodRef")
    public static IProxy proxy = DistExecutor.runForDist(() -> () -> new ClientProxy(), () -> () -> new ServerProxy());

    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();

    public PrincipiumMod() {
        // Register the setup method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        // Register the enqueueIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        // Register the processIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);
        // Register the doClientStuff method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(new EventHandlerClient());
        NetworkSync.registerPackets();
        ClickableHandler.registerPackets();

        Registration.init();
    }

    private void setup(final FMLCommonSetupEvent event)
    {
        // some preinit code
        LOGGER.info("HELLO FROM PREINIT");
        LOGGER.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());
        MinecraftForge.EVENT_BUS.register(new EventHandlerCommon());
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        // do something that can only be done on the client
        LOGGER.info("Got game settings {}", event.getMinecraftSupplier().get().gameSettings);
    }

    private void enqueueIMC(final InterModEnqueueEvent event)
    {
        // some example code to dispatch IMC to another mod
        InterModComms.sendTo("principium", "helloworld", () -> { LOGGER.info("Hello world from principium."); return "Hello world"; });
        InterModComms.sendTo("curios", CuriosAPI.IMC.REGISTER_TYPE, () -> new CurioIMCMessage("ring").setSize(2));
        InterModComms.sendTo("curios", CuriosAPI.IMC.REGISTER_TYPE, () -> new CurioIMCMessage("trinket").setSize(2));
    }

    private void processIMC(final InterModProcessEvent event)
    {
        // some example code to receive and process InterModComms from other mods
        LOGGER.info("Got IMC {}", event.getIMCStream().
                map(m->m.getMessageSupplier().get()).
                collect(Collectors.toList()));
    }
    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {
        // do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

    @Mod.EventBusSubscriber(modid = MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientSetup {

        @SubscribeEvent
        public static void postSetup(FMLLoadCompleteEvent evt) {

            EntityRendererManager rendererManager = Minecraft.getInstance().getRenderManager();
            rendererManager.getSkinMap().values()
                    .forEach(renderer -> renderer.addLayer(new ChestElytraRenderLayer<>(renderer)));
            rendererManager.renderers.values().forEach(renderer -> {
                if (renderer instanceof LivingRenderer) {
                    LivingRenderer<? extends LivingEntity, ? extends EntityModel> livingRenderer =
                            (LivingRenderer<? extends LivingEntity, ? extends EntityModel>) renderer;
                    livingRenderer.addLayer(new ChestElytraRenderLayer(livingRenderer));
                }
            });
        }
    }

}
