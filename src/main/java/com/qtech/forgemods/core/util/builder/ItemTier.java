package com.qtech.forgemods.core.util.builder;

import lombok.Builder;
import net.minecraft.item.IItemTier;
import net.minecraft.item.crafting.Ingredient;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

@Builder
public class ItemTier implements IItemTier {
    protected int tier;
    protected int maxUses;
    protected float efficiency;
    protected float attackDamage;
    protected int enchantability;
    protected Supplier<Ingredient> repairMaterial;

    public ItemTier(int tier, int maxUses, float efficiency, float attackDamage, int enchantability, Supplier<Ingredient> repairMaterial) {
        this.tier = tier;
        this.maxUses = maxUses;
        this.efficiency = efficiency;
        this.attackDamage = attackDamage;
        this.enchantability = enchantability;
        this.repairMaterial = repairMaterial;
    }

    @Override
    public int getMaxUses() {
        return maxUses;
    }

    @Override
    public float getEfficiency() {
        return efficiency;
    }

    @Override
    public float getAttackDamage() {
        return attackDamage;
    }

    @Override
    public int getHarvestLevel() {
        return tier;
    }

    @Override
    public int getEnchantability() {
        return enchantability;
    }

    @Override
    public @NotNull Ingredient getRepairMaterial() {
        return repairMaterial.get();
    }
}
