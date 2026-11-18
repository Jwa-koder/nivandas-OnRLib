package net.nivanda.onrlib.item;

import java.util.List;

import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.nivanda.onrlib.component.ModDataComponents;

import static net.nivanda.onrlib.nivandasOnRLib.MODID;

public class Ingot extends Item {
    public static final float MIN_METAL = 0f;
    public static final float MAX_METAL = 100f;

    public static final List<String> METALS = List.of(
            "iron",
            "mithril", "adamantite", "orichalcum", "starsteel", "moonsilver",
            "sunbronze", "voidiron", "dragonsteel", "aetherium", "shadowsteel",
            "bloodiron", "frostsilver", "emberite", "stormsteel", "duskgold",
            "dawnbronze", "verdanite", "azurite", "crimsonite", "nightsilver",
            "runesteel", "soulsilver", "netherbrass", "skyiron", "deepiron",
            "thornsteel", "venomite", "glimmerite", "arcanium", "celestium",
            "infernium", "glacium", "terranite", "tidesteel", "luminite",
            "obscurium", "pyrosteel", "cryosteel", "galvanite", "phantomsilver",
            "wraithiron", "spiritbronze", "elderbrass", "ancientite", "primordium",
            "zephyrite", "quicksteel", "heartgold", "rosesteel", "violium",
            "indigite", "ceruleum", "viridium", "amberite", "scarletite",
            "ivorite", "onyxium", "pearlsteel", "coralite", "jadesteel",
            "opaline", "garnetite", "topazium", "sapphirium", "emeraldine",
            "rubium", "amethine", "citrinium", "turquoisite", "magmasteel",
            "lavabronze", "ashiron", "cinderite", "smokesteel", "mistsilver",
            "cloudsteel", "thunderite", "lightningsteel", "galesilver", "tempestite",
            "oceanite", "abyssium", "reefbronze", "swampiron", "mossteel",
            "bloomsilver", "petalite", "honeygold", "wyrmbrass", "griffonsteel",
            "phoenixite", "hydrasteel", "krakenite", "titanbronze", "giantsteel",
            "fairysilver", "pixiegold", "demonite", "angelite", "chaosteel"
    );

    public Ingot(Properties properties) {
        super(properties);
    }

    public static float getMetal(ItemStack stack) {
        return Mth.clamp(stack.getOrDefault(ModDataComponents.METAL.get(), MIN_METAL), MIN_METAL, MAX_METAL);
    }

    public static int getMeltingPoint(ItemStack stack) {
        return getInt(stack, ModDataComponents.MELTING_POINT.get(), ModDataComponents.MIN_MELTING_POINT, ModDataComponents.MAX_MELTING_POINT);
    }

    public static boolean isMagnetic(ItemStack stack) {
        return stack.getOrDefault(ModDataComponents.IS_MAGNETIC.get(), false);
    }

    public static boolean hasArcanePowers(ItemStack stack) {
        return stack.getOrDefault(ModDataComponents.HAS_ARCANE_POWERS.get(), false);
    }

    public static int getResistance(ItemStack stack) {
        return getInt(stack, ModDataComponents.RESISTANCE.get(), ModDataComponents.MIN_RESISTANCE, ModDataComponents.MAX_RESISTANCE);
    }

    public static boolean isConductive(ItemStack stack) {
        return stack.getOrDefault(ModDataComponents.IS_CONDUCTIVE.get(), false);
    }

    public static int getHeatTransferRate(ItemStack stack) {
        return getInt(stack, ModDataComponents.HEAT_TRANSFER_RATE.get(), ModDataComponents.MIN_HEAT_TRANSFER_RATE, ModDataComponents.MAX_HEAT_TRANSFER_RATE);
    }

    public static int getWeight(ItemStack stack) {
        return getInt(stack, ModDataComponents.WEIGHT.get(), ModDataComponents.MIN_WEIGHT, ModDataComponents.MAX_WEIGHT);
    }

    public static boolean isRadioactive(ItemStack stack) {
        return stack.getOrDefault(ModDataComponents.IS_RADIOACTIVE.get(), false);
    }

    public static boolean isStable(ItemStack stack) {
        return stack.getOrDefault(ModDataComponents.IS_STABLE.get(), false);
    }

    private static int getInt(ItemStack stack, DataComponentType<Integer> type, int min, int max) {
        return Mth.clamp(stack.getOrDefault(type, min), min, max);
    }

    private static final String TOOLTIP = "tooltip." + MODID + ".ingot.";

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
        int meltingPoint = getMeltingPoint(stack);
        int[] meltingColors = {0xFFE066, 0xFF9933, 0xFF4433};
        tooltip.add(line("melting_point", 0xFFAA00,
                Component.translatable(TOOLTIP + "melting_point." + meltingPoint)
                        .withColor(meltingColors[meltingPoint - ModDataComponents.MIN_MELTING_POINT])));

        tooltip.add(line("resistance", 0x55FFFF, scaled(getResistance(stack),
                ModDataComponents.MIN_RESISTANCE, ModDataComponents.MAX_RESISTANCE, 0xFF5555, 0x55FF55)));
        tooltip.add(line("heat_transfer_rate", 0xFF7744, scaled(getHeatTransferRate(stack),
                ModDataComponents.MIN_HEAT_TRANSFER_RATE, ModDataComponents.MAX_HEAT_TRANSFER_RATE, 0x5599FF, 0xFF5533)));
        tooltip.add(line("weight", 0xC8A27A, scaled(getWeight(stack),
                ModDataComponents.MIN_WEIGHT, ModDataComponents.MAX_WEIGHT, 0xDDEEFF, 0x9A6A3A)));

        tooltip.add(line("is_magnetic", 0x7799FF, yesNo(isMagnetic(stack), 0x7799FF)));
        tooltip.add(line("is_conductive", 0xFFFF55, yesNo(isConductive(stack), 0xFFFF55)));
        tooltip.add(line("has_arcane_powers", 0xDD77FF, yesNo(hasArcanePowers(stack), 0xDD77FF)));
        tooltip.add(line("is_radioactive", 0x88FF33, yesNo(isRadioactive(stack), 0x88FF33)));
        tooltip.add(line("is_stable", 0x55FF99, yesNo(isStable(stack), 0x55FF99)));
    }

    private static Component line(String key, int labelColor, Component value) {
        return Component.translatable(TOOLTIP + key).withColor(labelColor)
                .append(Component.literal(": ").withStyle(ChatFormatting.DARK_GRAY))
                .append(value);
    }

    private static Component scaled(int value, int min, int max, int lowColor, int highColor) {
        float t = max == min ? 1f : (float) (value - min) / (max - min);
        return Component.literal(value + "/" + max).withColor(lerpColor(t, lowColor, highColor));
    }

    private static Component yesNo(boolean value, int yesColor) {
        return value
                ? Component.translatable(TOOLTIP + "yes").withColor(yesColor)
                : Component.translatable(TOOLTIP + "no").withStyle(ChatFormatting.DARK_GRAY);
    }

    private static int lerpColor(float t, int from, int to) {
        int r = Mth.lerpInt(t, (from >> 16) & 0xFF, (to >> 16) & 0xFF);
        int g = Mth.lerpInt(t, (from >> 8) & 0xFF, (to >> 8) & 0xFF);
        int b = Mth.lerpInt(t, from & 0xFF, to & 0xFF);
        return (r << 16) | (g << 8) | b;
    }

    public static String getMetalName(ItemStack stack) {
        return METALS.get(Mth.clamp(Mth.floor(getMetal(stack)), 0, METALS.size() - 1));
    }

    @Override
    public String getDescriptionId(ItemStack stack) {
        return "item." + MODID + "." + getMetalName(stack) + "_ingot";
    }
}
