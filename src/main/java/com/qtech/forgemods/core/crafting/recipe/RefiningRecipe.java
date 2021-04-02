package com.qtech.forgemods.core.crafting.recipe;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.qsoftware.modlib.api.crafting.recipe.fluid.FluidIngredient;
import com.qsoftware.modlib.api.crafting.recipe.fluid.IFluidInventory;
import com.qsoftware.modlib.api.crafting.recipe.fluid.IFluidRecipe;
import com.qtech.forgemods.core.init.ModRecipes;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.registries.ForgeRegistryEntry;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
public class RefiningRecipe implements IFluidRecipe<IFluidInventory> {
    private final ResourceLocation recipeId;
    private final List<FluidStack> outputs = NonNullList.create();
    @Getter private int processTime;
    @Getter private FluidIngredient ingredient;

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
        List<FluidStack> results = NonNullList.create();
        outputs.forEach(s -> results.add(s.copy()));
        return results;
    }

    @Override
    public List<FluidIngredient> getFluidIngredients() {
        return Collections.singletonList(ingredient);
    }

    @Override
    public ResourceLocation getId() {
        return recipeId;
    }

    @Override
    public IRecipeSerializer<?> getSerializer() {
        return ModRecipes.REFINING.get();
    }

    @Override
    public IRecipeType<?> getType() {
        return ModRecipes.Types.REFINING;
    }

    @Override
    public boolean isDynamic() {
        return true;
    }

    public static class Serializer extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<RefiningRecipe> {
        @Override
        public RefiningRecipe read(ResourceLocation recipeId, JsonObject json) {
            RefiningRecipe recipe = new RefiningRecipe(recipeId);
            recipe.processTime = JSONUtils.getInt(json, "process_time");
            recipe.ingredient = FluidIngredient.deserialize(json.getAsJsonObject("ingredient"));
            for (JsonElement je : JSONUtils.getJsonArray(json, "results")) {
                FluidStack stack = IFluidRecipe.deserializeFluid(je.getAsJsonObject());
                if (!stack.isEmpty()) {
                    recipe.outputs.add(stack);
                }
            }
            return recipe;
        }

        @Nullable
        @Override
        public RefiningRecipe read(ResourceLocation recipeId, PacketBuffer buffer) {
            RefiningRecipe recipe = new RefiningRecipe(recipeId);
            recipe.processTime = buffer.readVarInt();
            recipe.ingredient = FluidIngredient.read(buffer);
            int count = buffer.readByte();
            for (int i = 0; i < count; ++i) {
                FluidStack stack = IFluidRecipe.readFluid(buffer);
                if (!stack.isEmpty()) {
                    recipe.outputs.add(stack);
                }
            }
            return recipe;
        }

        @Override
        public void write(PacketBuffer buffer, RefiningRecipe recipe) {
            buffer.writeVarInt(recipe.processTime);
            recipe.ingredient.write(buffer);
            buffer.writeByte(recipe.outputs.size());
            recipe.outputs.forEach(s -> IFluidRecipe.writeFluid(buffer, s));
        }
    }
}
