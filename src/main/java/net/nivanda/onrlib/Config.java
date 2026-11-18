package net.nivanda.onrlib;

import net.neoforged.neoforge.common.ModConfigSpec;

public class Config {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    // Define config values here, before SPEC is built.

    static final ModConfigSpec SPEC = BUILDER.build();
}
