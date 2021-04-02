package com.qtech.forgemods.core.crafting.recipe;

import com.google.gson.JsonObject;
import com.qsoftware.modlib.api.crafting.recipe.fluid.FluidIngredient;
import com.qsoftware.modlib.api.crafting.recipe.fluid.IFluidInventory;
import com.qsoftware.modlib.api.crafting.recipe.fluid.IFluidRecipe;
import com.qtech.forgemods.core.init.ModRecipes;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.ShapedRecipe;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.registries.ForgeRegistryEntry;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
public class SolidifyingRecipe implements IFluidRecipe<IFluidInventory> {
    private final ResourceLocation recipeId;
    @Getter private int processTime;
    @Getter private FluidIngredient ingredient;
    private ItemStack result;

    @Override
    public boolean matches(IFluidInventory inv, World worldIn) {
        return ingredient.test(inv.getFluidInTank(0));
    }

    @Override
    public List<FluidStack> getFluidResults(IFluidInventory inv) {
        return getFluidOutputs();
    }

    @Override
    public List<FluidStack> getFluidOutputs() {
        return Collections.emptyList();
    }

    @Override
    public List<FluidIngredient> getFluidIngredients() {
        return Collections.singletonList(ingredient);
    }

    @Override
    public ItemStack getCraftingResult(IFluidInventory inv) {
        return getRecipeOutput();
    }

    @Override
    public ItemStack getRecipeOutput() {
        return result.copy();
    }

    @Override
    public ResourceLocation getId() {
        return recipeId;
    }

    @Override
    public IRecipeSerializer<?> getSerializer() {
        return ModRecipes.SOLIDIFYING.get();
    }

    @Override
    public IRecipeType<?> getType() {
        return ModRecipes.Types.SOLIDIFYING;
    }

    @Override
    public boolean isDynamic() {
        return true;
    }

    public static class Serializer extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<SolidifyingRecipe> {
        @Override
        public SolidifyingRecipe read(ResourceLocation recipeId, JsonObject json) {
            SolidifyingRecipe recipe = new SolidifyingRecipe(recipeId);
            recipe.processTime = JSONUtils.getInt(json, "process_time");
            recipe.ingredient = FluidIngredient.deserialize(json.getAsJsonObject("ingredient"));
            recipe.result = ShapedRecipe.deserializeItem(JSONUtils.getJsonObject(json, "result"));
            return recipe;
        }

        @Nullable
        @Override
        public SolidifyingRecipe read(ResourceLocation recipeId, PacketBuffer buffer) {
            SolidifyingRecipe recipe = new SolidifyingRecipe(recipeId);
            recipe.processTime = buffer.readVarInt();
            recipe.ingredient = FluidIngredient.read(buffer);
            recipe.result = buffer.readItemStack();
            return recipe;
        }

        @Override
        public void write(PacketBuffer buffer, SolidifyingRecipe recipe) {
            buffer.writeVarInt(recipe.processTime);
            recipe.ingredient.write(buffer);
            buffer.writeItemStack(recipe.result);
        }
    }
}
