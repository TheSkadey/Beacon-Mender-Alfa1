package com.example.beaconmender;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.effect.MobEffect;

public final class ModEffects {
    /** Rejestracja następuje przy pierwszym załadowaniu tej klasy (tylko raz). */
    public static final Holder<MobEffect> MENDING_AURA = Registry.registerForHolder(
            BuiltInRegistries.MOB_EFFECT,
            Identifier.fromNamespaceAndPath(BeaconMenderMod.MOD_ID, "mending_aura"),
            new MendingAuraEffect());

    private ModEffects() {}

    public static void init() {
        // pusta metoda - samo wywołanie ładuje klasę
    }
}
