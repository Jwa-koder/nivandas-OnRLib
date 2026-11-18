package net.nivanda.onrlib;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import net.nivanda.onrlib.util.ModItemProperties;

@Mod(value = nivandasOnRLib.MODID, dist = Dist.CLIENT)
@EventBusSubscriber(modid = nivandasOnRLib.MODID, value = Dist.CLIENT)
public class nivandasOnRLibClient {
    public nivandasOnRLibClient(ModContainer container) {
        container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
    }

    @SubscribeEvent
    static void onClientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(ModItemProperties::addCustomItemProperties);
    }
}
