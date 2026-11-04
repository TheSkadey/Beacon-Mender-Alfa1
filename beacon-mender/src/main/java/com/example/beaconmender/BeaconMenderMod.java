package com.example.beaconmender;

import net.fabricmc.api.ModInitializer;

public class BeaconMenderMod implements ModInitializer {
    public static final String MOD_ID = "beaconmender";

    @Override
    public void onInitialize() {
        // Wymusza załadowanie klasy i rejestrację efektu (jeśli mixin nie zrobił tego wcześniej)
        ModEffects.init();
    }
}
