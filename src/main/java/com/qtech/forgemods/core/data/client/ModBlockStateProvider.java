package com.qtech.forgemods.core.data.client;

import com.qtech.forgemods.core.QFMCore;
import com.qtech.forgemods.core.modules.tiles.ModBlocks;
import com.qtech.forgemods.core.modules.tiles.blocks.machines.dryingrack.DryingRackBlock;
import com.qtech.forgemods.core.modules.items.OreMaterial;
import com.qsoftware.modlib.silentlib.registry.BlockRegistryObject;
import com.qsoftware.modlib.silentlib.util.NameUtils;
import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.Objects;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, QFMCore.modId, exFileHelper);
    }

    @Nonnull
    @Override
    public String getName() {
        return "QForgeMod - Block States and Models";
    }

    @Override
    protected void registerStatesAndModels() {
        Arrays.stream(OreMaterial.values()).forEach(metal -> {
            metal.getOre().ifPresent(this::simpleBlock);
            metal.getStorageBlock().ifPresent(this::simpleBlock);
        });

        models().withExistingParent("drying_rack", mcLoc("block/block"))
                .texture("0", "#wood")
                .texture("particle", "#wood")
                .element()
                .from(0, 12, 0)
                .to(16, 16, 4)
                .face(Direction.DOWN).uvs(0, 0, 16, 4).texture("#0").end()
                .face(Direction.UP).uvs(0, 0, 16, 4).texture("#0").end()
                .face(Direction.NORTH).uvs(0, 0, 16, 4).texture("#0").end()
                .face(Direction.SOUTH).uvs(0, 0, 16, 4).texture("#0").end()
                .face(Direction.WEST).uvs(0, 0, 4, 4).texture("#0").end()
                .face(Direction.EAST).uvs(0, 0, 4, 4).texture("#0").end()
                .end();

        dryingRack(ModBlocks.ACACIA_DRYING_RACK.get(), "block/acacia_planks");
        dryingRack(ModBlocks.BIRCH_DRYING_RACK.get(), "block/birch_planks");
        dryingRack(ModBlocks.DARK_OAK_DRYING_RACK.get(), "block/dark_oak_planks");
        dryingRack(ModBlocks.JUNGLE_DRYING_RACK.get(), "block/jungle_planks");
        dryingRack(ModBlocks.OAK_DRYING_RACK.get(), "block/oak_planks");
        dryingRack(ModBlocks.SPRUCE_DRYING_RACK.get(), "block/spruce_planks");
        dryingRack(ModBlocks.EUCALYPTUS_DRYING_RACK.get(), "qforgemod:blocks/eucalyptus_planks");
        dryingRack(ModBlocks.CHERRY_DRYING_RACK.get(), "qforgemod:blocks/cherry_planks");

        simpleBlock(ModBlocks.STONE_MACHINE_FRAME.get(), models()
                .withExistingParent("stone_machine_frame", modLoc("block/machine_frame"))
                .texture("all", "qforgemod:blocks/machine_frame/stone"));
        simpleBlock(ModBlocks.ALLOY_MACHINE_FRAME.get(), models()
                .withExistingParent("alloy_machine_frame", modLoc("block/machine_frame"))
                .texture("all", "qforgemod:blocks/machine_frame/alloy"));
        for (BlockRegistryObject<Block> block : ModBlocks.BOOKSHELFS) {
            QFMCore.LOGGER.info("Generating block state and model for " + block.getRegistryName());
            simpleBlock(block.get(), models()
                    .withExistingParent(block.getName(), mcLoc("block/cube_column"))
                    .texture("end", "minecraft:block/oak_planks")
                    .texture("side", "qforgemod:blocks/bookshelfs/" + block.getName()));
        }
    }

    private void dryingRack(DryingRackBlock block, String texture) {
        getVariantBuilder(block).forAllStatesExcept(state -> {
            String name = NameUtils.from(block).getPath();
            return ConfiguredModel.builder()
                    .modelFile(models()
                            .withExistingParent(name, modLoc("block/drying_rack"))
                            .texture("wood", mcLoc(texture)))
                    .rotationY((int) state.get(DryingRackBlock.FACING).getHorizontalAngle())
                    .build();
        }, DryingRackBlock.WATERLOGGED);
    }

    public ResourceLocation blockTexture(Block block) {
        ResourceLocation name = block.getRegistryName();
        return new ResourceLocation(Objects.requireNonNull(name).getNamespace(), "blocks/" + name.getPath());
    }
}
