package net.nivanda.onrlib.util;

import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.nivanda.onrlib.item.Ingot;
import net.nivanda.onrlib.item.ModItems;

import static net.nivanda.onrlib.nivandasOnRLib.MODID;

public class ModItemProperties {
    public static void addCustomItemProperties() {
        ItemProperties.register(ModItems.INGOT.get(), ResourceLocation.fromNamespaceAndPath(MODID, "name"),
            (stack, level, entity, seed) -> Ingot.getMetal(stack));
        ItemProperties.register(ModItems.INGOT.get(), ResourceLocation.fromNamespaceAndPath(MODID, "melting_point"),
                (stack, level, entity, seed) -> Ingot.getMeltingPoint(stack));
    }
}
