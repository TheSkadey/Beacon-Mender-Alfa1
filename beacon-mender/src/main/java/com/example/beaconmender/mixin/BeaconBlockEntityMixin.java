package com.example.beaconmender.mixin;

import com.example.beaconmender.ModEffects;
import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.level.block.entity.BeaconBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Dodaje Mending Aura do listy efektów latarni (poziom piramidy III, obok Strength)
 * oraz do zbioru dozwolonych efektów (inaczej serwer odrzuci wybór).
 */
@Mixin(BeaconBlockEntity.class)
public abstract class BeaconBlockEntityMixin {

    @Mutable @Shadow
    public static List<List<Holder<MobEffect>>> BEACON_EFFECTS;

    @Mutable @Shadow
    private static Set<Holder<MobEffect>> VALID_EFFECTS;

    @Inject(method = "<clinit>", at = @At("TAIL"))
    private static void beaconmender$addMendingAura(CallbackInfo ci) {
        // Kopie modyfikowalne (oryginały są niemodyfikowalne)
        List<List<Holder<MobEffect>>> tiers = new ArrayList<>();
        for (List<Holder<MobEffect>> tier : BEACON_EFFECTS) {
            tiers.add(new ArrayList<>(tier));
        }
        // indeks 2 = trzeci poziom (Strength); indeks 3 to efekt dodatkowy (Regeneration)
        tiers.get(2).add(ModEffects.MENDING_AURA);

        List<List<Holder<MobEffect>>> immutable = new ArrayList<>();
        for (List<Holder<MobEffect>> tier : tiers) {
            immutable.add(List.copyOf(tier));
        }
        BEACON_EFFECTS = List.copyOf(immutable);

        Set<Holder<MobEffect>> valid = new HashSet<>(VALID_EFFECTS);
        valid.add(ModEffects.MENDING_AURA);
        VALID_EFFECTS = valid;
    }
}
