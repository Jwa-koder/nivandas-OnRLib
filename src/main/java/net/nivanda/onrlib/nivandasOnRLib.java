package net.nivanda.onrlib;

import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.nivanda.onrlib.component.ModDataComponents;
import net.nivanda.onrlib.item.ModItems;

@Mod(nivandasOnRLib.MODID)
public class nivandasOnRLib {
    public static final String MODID = "nv_onrlib";
    public static final Logger LOGGER = LogUtils.getLogger();

    public nivandasOnRLib(IEventBus modEventBus, ModContainer modContainer) {
        ModItems.register(modEventBus);
        ModDataComponents.register(modEventBus);

        modEventBus.addListener(this::commonSetup);

        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(FMLCommonSetupEvent event) {
    }
}
