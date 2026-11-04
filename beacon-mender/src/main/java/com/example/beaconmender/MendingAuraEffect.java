package com.example.beaconmender;

import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.enchantment.ItemEnchantments;

/**
 * Co INTERVAL_TICKS naprawia przedmioty z Mendingiem w całym ekwipunku gracza
 * (hotbar, plecak, zbroja, druga ręka).
 */
public class MendingAuraEffect extends MobEffect {
    /** Co ile ticków następuje naprawa (20 ticków = 1 sekunda). */
    private static final int INTERVAL_TICKS = 20;
    /** Ile wytrzymałości naprawiamy na przedmiot przy każdym "pulsie". */
    private static final int BASE_REPAIR = 2;
    /** Dodatkowa naprawa za każdy poziom wzmocnienia efektu (beacon poz. II). */
    private static final int REPAIR_PER_AMPLIFIER = 2;

    public MendingAuraEffect() {
        super(MobEffectCategory.BENEFICIAL, 0x55FFAA);
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return duration % INTERVAL_TICKS == 0;
    }

    @Override
    public boolean applyEffectTick(ServerLevel level, LivingEntity entity, int amplifier) {
        if (entity instanceof ServerPlayer player) {
            int amount = BASE_REPAIR + amplifier * REPAIR_PER_AMPLIFIER;
            Inventory inventory = player.getInventory();
            for (int i = 0; i < inventory.getContainerSize(); i++) {
                repair(inventory.getItem(i), amount);
            }
        }
        return true; // efekt trwa dalej
    }

    private static void repair(ItemStack stack, int amount) {
        if (stack.isEmpty() || !stack.isDamaged() || !hasMending(stack)) {
            return;
        }
        stack.setDamageValue(Math.max(0, stack.getDamageValue() - amount));
    }

    private static boolean hasMending(ItemStack stack) {
        ItemEnchantments enchantments = stack.getOrDefault(DataComponents.ENCHANTMENTS, ItemEnchantments.EMPTY);
        for (Holder<Enchantment> enchantment : enchantments.keySet()) {
            if (enchantment.is(Enchantments.MENDING)) {
                return true;
            }
        }
        return false;
    }
}
