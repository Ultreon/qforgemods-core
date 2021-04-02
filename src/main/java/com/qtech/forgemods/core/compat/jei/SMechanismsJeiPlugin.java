package com.qtech.forgemods.core.compat.jei;

import com.qtech.forgemods.core.QFMCore;
import com.qtech.forgemods.core.init.ModRecipes;
import com.qtech.forgemods.core.init.Registration;
import com.qtech.forgemods.core.modules.tiles.ModBlocks;
import com.qtech.forgemods.core.modules.tiles.blocks.machines.alloysmelter.AlloySmelterContainer;
import com.qtech.forgemods.core.modules.tiles.blocks.machines.alloysmelter.AlloySmelterScreen;
import com.qtech.forgemods.core.modules.tiles.blocks.machines.arcaneescalator.ArcaneEscalatorContainer;
import com.qtech.forgemods.core.modules.tiles.blocks.machines.arcaneescalator.ArcaneEscalatorScreen;
import com.qtech.forgemods.core.modules.tiles.blocks.machines.compressor.CompressorContainer;
import com.qtech.forgemods.core.modules.tiles.blocks.machines.compressor.CompressorScreen;
import com.qtech.forgemods.core.modules.tiles.blocks.machines.crusher.CrusherContainer;
import com.qtech.forgemods.core.modules.tiles.blocks.machines.crusher.CrusherScreen;
import com.qtech.forgemods.core.modules.tiles.blocks.machines.dryingrack.DryingRackBlock;
import com.qtech.forgemods.core.modules.tiles.blocks.machines.electricfurnace.ElectricFurnaceContainer;
import com.qtech.forgemods.core.modules.tiles.blocks.machines.electricfurnace.ElectricFurnaceScreen;
import com.qtech.forgemods.core.modules.tiles.blocks.machines.infuser.InfuserScreen;
import com.qtech.forgemods.core.modules.tiles.blocks.machines.mixer.MixerScreen;
import com.qtech.forgemods.core.modules.tiles.blocks.machines.refinery.RefineryScreen;
import com.qtech.forgemods.core.modules.tiles.blocks.machines.solidifier.SolidifierScreen;
import com.qtech.forgemods.core.modules.items.ModItems;
import com.qtech.forgemods.core.modules.items.objects.fluid.CanisterItem;
import com.qtech.forgemods.core.util.Constants;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.VanillaRecipeCategoryUid;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.registration.*;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.ResourceLocation;

import java.util.List;
import java.util.stream.Collectors;

@JeiPlugin
public class SMechanismsJeiPlugin implements IModPlugin {
    private static final ResourceLocation PLUGIN_UID = QFMCore.rl("plugin/main");

    private static List<IRecipe<?>> getRecipesOfType(IRecipeType<?> recipeType) {
        assert Minecraft.getInstance().world != null;
        return Minecraft.getInstance().world.getRecipeManager().getRecipes().stream()
                .filter(r -> r.getType() == recipeType)
                .collect(Collectors.toList());
    }

    @Override
    public ResourceLocation getPluginUid() {
        return PLUGIN_UID;
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        IGuiHelper guiHelper = registration.getJeiHelpers().getGuiHelper();
        registration.addRecipeCategories(new AlloySmeltingRecipeCategoryJei(guiHelper));
        registration.addRecipeCategories(new ArcaneEscalatorRecipeCategoryJei(guiHelper));
//        registration.addRecipeCategories(new EnchanterRecipeCategoryJei(guiHelper));
        registration.addRecipeCategories(new CompressingRecipeCategoryJei(guiHelper));
        registration.addRecipeCategories(new CrushingRecipeCategoryJei(guiHelper));
        registration.addRecipeCategories(new DryingRecipeCategoryJei(guiHelper));
        registration.addRecipeCategories(new InfusingRecipeCategory(guiHelper));
        registration.addRecipeCategories(new MixingRecipeCategory(guiHelper));
        registration.addRecipeCategories(new RefiningRecipeCategory(guiHelper));
        registration.addRecipeCategories(new SolidifyingRecipeCategory(guiHelper));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        registration.addRecipes(getRecipesOfType(ModRecipes.Types.ALLOY_SMELTING), Constants.ALLOY_SMELTING);
        registration.addRecipes(getRecipesOfType(ModRecipes.Types.ARCANE_ESCALATING), Constants.ARCANE_ESCALATING);
//        registration.addRecipes(getRecipesOfType(ModRecipes.Types.ENCHANTING), Constants.ENCHANTING);
        registration.addRecipes(getRecipesOfType(ModRecipes.Types.COMPRESSING), Constants.COMPRESSING);
        registration.addRecipes(getRecipesOfType(ModRecipes.Types.CRUSHING), Constants.CRUSHING);
        registration.addRecipes(getRecipesOfType(ModRecipes.Types.DRYING), Constants.DRYING);
        registration.addRecipes(getRecipesOfType(ModRecipes.Types.INFUSING), Constants.INFUSING);
        registration.addRecipes(getRecipesOfType(ModRecipes.Types.MIXING), Constants.MIXING);
        registration.addRecipes(getRecipesOfType(ModRecipes.Types.REFINING), Constants.REFINING);
        registration.addRecipes(getRecipesOfType(ModRecipes.Types.SOLIDIFYING), Constants.SOLIDIFYING);
    }

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration) {
        registration.addRecipeClickArea(AlloySmelterScreen.class, 90, 32, 28, 23, Constants.ALLOY_SMELTING);
        registration.addRecipeClickArea(ArcaneEscalatorScreen.class, 90, 32, 28, 23, Constants.ARCANE_ESCALATING);
//        registration.addRecipeClickArea(EnchanterScreen.class, 90, 32, 28, 23, Constants.ENCHANTING);
        registration.addRecipeClickArea(CompressorScreen.class, 78, 32, 28, 23, Constants.COMPRESSING);
        registration.addRecipeClickArea(CrusherScreen.class, 45, 32, 28, 23, Constants.CRUSHING);
        registration.addRecipeClickArea(ElectricFurnaceScreen.class, 78, 32, 28, 23,
                VanillaRecipeCategoryUid.BLASTING, VanillaRecipeCategoryUid.FURNACE);
        registration.addRecipeClickArea(InfuserScreen.class, 79, 31, 24, 23, Constants.INFUSING);
        registration.addRecipeClickArea(MixerScreen.class, 92, 31, 24, 23, Constants.MIXING);
        registration.addRecipeClickArea(RefineryScreen.class, 43, 31, 24, 23, Constants.REFINING);
        registration.addRecipeClickArea(SolidifierScreen.class, 79, 31, 24, 23, Constants.SOLIDIFYING);
    }

    @Override
    public void registerRecipeTransferHandlers(IRecipeTransferRegistration registration) {
        registration.addRecipeTransferHandler(AlloySmelterContainer.class, Constants.ALLOY_SMELTING, 0, 4, 5, 36);
        registration.addRecipeTransferHandler(ArcaneEscalatorContainer.class, Constants.ARCANE_ESCALATING, 0, 4, 5, 36);
//        registration.addRecipeTransferHandler(EnchanterContainer.class, Constants.ENCHANTING, 0, 4, 5, 36);
        registration.addRecipeTransferHandler(CompressorContainer.class, Constants.COMPRESSING, 0, 1, 2, 36);
        registration.addRecipeTransferHandler(CrusherContainer.class, Constants.CRUSHING, 0, 1, 5, 36);
        registration.addRecipeTransferHandler(ElectricFurnaceContainer.class, Constants.REFINING, 0, 1, 2, 36);
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.ALLOY_SMELTER), Constants.ALLOY_SMELTING);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.BASIC_ALLOY_SMELTER), Constants.ALLOY_SMELTING);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.ARCANE_ESCALATOR), Constants.ARCANE_ESCALATING);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.BASIC_ARCANE_ESCALATOR), Constants.ARCANE_ESCALATING);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.COMPRESSOR), Constants.COMPRESSING);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.CRUSHER), Constants.CRUSHING);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.BASIC_CRUSHER), Constants.CRUSHING);
        Registration.getBlocks(DryingRackBlock.class).forEach(block ->
                registration.addRecipeCatalyst(new ItemStack(block), Constants.DRYING));
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.ELECTRIC_FURNACE),
                VanillaRecipeCategoryUid.BLASTING, VanillaRecipeCategoryUid.FURNACE);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.INFUSER), Constants.INFUSING);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.MIXER), Constants.MIXING);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.REFINERY), Constants.REFINING);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.SOLIDIFIER), Constants.SOLIDIFYING);
    }

    @Override
    public void registerItemSubtypes(ISubtypeRegistration registration) {
        registration.registerSubtypeInterpreter(ModItems.CANISTER.get(), CanisterItem::getFluidKey);
    }
}
