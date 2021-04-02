package com.qtech.forgemods.core.compat.jei;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.qtech.forgemods.core.crafting.recipe.ArcaneEscalatingRecipe;
import com.qtech.forgemods.core.modules.tiles.ModBlocks;
import com.qtech.forgemods.core.modules.tiles.blocks.machines.arcaneescalator.ArcaneEscalatorScreen;
import com.qtech.forgemods.core.util.Constants;
import com.qtech.forgemods.core.util.TextUtils;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
import mezz.jei.api.gui.ingredient.IGuiItemStackGroup;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class ArcaneEscalatorRecipeCategoryJei implements IRecipeCategory<ArcaneEscalatingRecipe> {
    private static final int GUI_START_X = 16;
    private static final int GUI_START_Y = 30;
    private static final int GUI_WIDTH = 146 - GUI_START_X;
    private static final int GUI_HEIGHT = 55 - GUI_START_Y;

    private final IDrawable background;
    private final IDrawable icon;
    private final IDrawableAnimated arrow;
    private final String localizedName;

    public ArcaneEscalatorRecipeCategoryJei(IGuiHelper guiHelper) {
        background = guiHelper.createDrawable(ArcaneEscalatorScreen.TEXTURE, GUI_START_X, GUI_START_Y, GUI_WIDTH, GUI_HEIGHT);
        icon = guiHelper.createDrawableIngredient(new ItemStack(ModBlocks.ARCANE_ESCALATOR));
        arrow = guiHelper.drawableBuilder(ArcaneEscalatorScreen.TEXTURE, 176, 14, 24, 17)
                .buildAnimated(200, IDrawableAnimated.StartDirection.LEFT, false);
        localizedName = TextUtils.translate("jei", "category.arcane_escalating").getString();
    }

    @Override
    public ResourceLocation getUid() {
        return Constants.ARCANE_ESCALATING;
    }

    @Override
    public Class<? extends ArcaneEscalatingRecipe> getRecipeClass() {
        return ArcaneEscalatingRecipe.class;
    }

    @Override
    public String getTitle() {
        return localizedName;
    }

    @Override
    public IDrawable getBackground() {
        return background;
    }

    @Override
    public IDrawable getIcon() {
        return icon;
    }

    @Override
    public void setIngredients(ArcaneEscalatingRecipe recipe, IIngredients ingredients) {
        ingredients.setInputLists(VanillaTypes.ITEM, recipe.getIngredientMap().keySet().stream()
                .map(ingredient -> Arrays.asList(ingredient.getMatchingStacks()))
                .collect(Collectors.toList()));
        ingredients.setOutput(VanillaTypes.ITEM, recipe.getRecipeOutput());
    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, ArcaneEscalatingRecipe recipe, IIngredients ingredients) {
        IGuiItemStackGroup itemStacks = recipeLayout.getItemStacks();
        itemStacks.init(0, true, 16 - GUI_START_X, 34 - GUI_START_Y);
        itemStacks.init(1, true, 34 - GUI_START_X, 34 - GUI_START_Y);
        itemStacks.init(2, true, 52 - GUI_START_X, 34 - GUI_START_Y);
        itemStacks.init(3, true, 70 - GUI_START_X, 34 - GUI_START_Y);
        itemStacks.init(4, false, 125 - GUI_START_X, 34 - GUI_START_Y);

        int i = 0;
        for (Map.Entry<Ingredient, Integer> entry : recipe.getIngredientMap().entrySet()) {
            Ingredient ingredient = entry.getKey();
            Integer count = entry.getValue();
            itemStacks.set(i++, Arrays.stream(ingredient.getMatchingStacks())
                    .map(s -> {
                        ItemStack stack = s.copy();
                        stack.setCount(count);
                        return stack;
                    })
                    .collect(Collectors.toList())
            );
        }
        itemStacks.set(4, recipe.getRecipeOutput());
    }

    @Override
    public void draw(ArcaneEscalatingRecipe recipe, MatrixStack matrixStack, double mouseX, double mouseY) {
        arrow.draw(matrixStack, 92 - GUI_START_X, 35 - GUI_START_Y);
    }
}
