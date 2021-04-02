package com.qtech.texturedmodels.block;

import com.qtech.texturedmodels.QTextureModels;
import com.qtech.texturedmodels.setup.Registration;
import com.qtech.texturedmodels.setup.config.BCModConfig;
import com.qtech.texturedmodels.tileentity.FrameBlockTile;
import com.qtech.texturedmodels.util.BCBlockStateProperties;
import com.qtech.texturedmodels.util.BlockAppearanceHelper;
import com.qtech.texturedmodels.util.BlockSavingHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.IWaterLoggable;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItem;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.common.extensions.IForgeBlockState;

import javax.annotation.Nullable;
import java.util.Objects;

import static com.qtech.texturedmodels.util.BCBlockStateProperties.LIGHT_LEVEL;
import static net.minecraft.state.properties.BlockStateProperties.WATERLOGGED;


/**
 * Main class for frameblocks - all important block info can be found here
 * This class is the most basic one for all frame blocks, so you can find most of the documentation here
 *
 * @author PianoManu
 * @version 1.7 10/20/20
 */
@SuppressWarnings("deprecation")
public class FrameBlock extends Block implements IForgeBlockState, IWaterLoggable {
    /**
     * Block property (can be seed when pressing F3 in-game)
     * This is needed, because we need to detect whether the blockstate has changed
     */
    public static final BooleanProperty CONTAINS_BLOCK = BCBlockStateProperties.CONTAINS_BLOCK;

    private static final VoxelShape MIDDLE_STRIP_NORTH = Block.makeCuboidShape(0.0, 8.0, 1.0, 16.0, 9.0, 2.0);
    private static final VoxelShape MIDDLE_STRIP_EAST = Block.makeCuboidShape(14.0, 8.0, 2.0, 15.0, 9.0, 14.0);
    private static final VoxelShape MIDDLE_STRIP_SOUTH = Block.makeCuboidShape(0.0, 8.0, 14.0, 16.0, 9.0, 15.0);
    private static final VoxelShape MIDDLE_STRIP_WEST = Block.makeCuboidShape(1.0, 8.0, 2.0, 2.0, 9.0, 14.0);
    private static final VoxelShape TOP = Block.makeCuboidShape(0.0, 15.0, 0.0, 16.0, 16.0, 16.0);
    private static final VoxelShape DOWN = Block.makeCuboidShape(0.0, 0.0, 0.0, 16.0, 1.0, 16.0);
    private static final VoxelShape NW_PILLAR = Block.makeCuboidShape(0.0, 1.0, 0.0, 2.0, 15.0, 2.0);
    private static final VoxelShape SW_PILLAR = Block.makeCuboidShape(0.0, 1.0, 14.0, 2.0, 15.0, 16.0);
    private static final VoxelShape NE_PILLAR = Block.makeCuboidShape(14.0, 1.0, 0.0, 16.0, 15.0, 2.0);
    private static final VoxelShape SE_PILLAR = Block.makeCuboidShape(14.0, 1.0, 14.0, 16.0, 15.0, 16.0);
    private static final VoxelShape MID = Block.makeCuboidShape(2.0, 1.0, 2.0, 14.0, 15.0, 14.0);
    private static final VoxelShape CUBE = VoxelShapes.or(MIDDLE_STRIP_EAST, MIDDLE_STRIP_SOUTH, MIDDLE_STRIP_WEST, MIDDLE_STRIP_NORTH, TOP, DOWN, NW_PILLAR, SW_PILLAR, NE_PILLAR, SE_PILLAR, MID);

    /**
     * classic constructor, all default values are set
     *
     * @param properties determined when registering the block (see {@link Registration}
     */
    public FrameBlock(Properties properties) {
        super(properties.variableOpacity());
        this.setDefaultState(this.stateContainer.getBaseState().with(CONTAINS_BLOCK, Boolean.FALSE).with(LIGHT_LEVEL, 0).with(WATERLOGGED, false));//.with(TEXTURE,0));
    }

    /**
     * Assign needed blockstates to frame block - we need "contains_block" and "light_level", both because we have to check for blockstate changes
     */
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(WATERLOGGED, CONTAINS_BLOCK, LIGHT_LEVEL);
    }

    /**
     * Yep, it's a complex block structure, so we need a tile entity
     *
     * @param state regardless of its state, it always has a TileEntity
     * @return regardless of its state, it always has a TileEntity -> returns true every time
     */
    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    /**
     * When placed, this method is called and a new FrameBlockTile is created
     * This is needed to store a block inside the frame, change its light value etc.
     *
     * @param state regardless of its state, we always create the TileEntity
     * @param world regardless of the world it's in, we always create the TileEntity
     * @return the new empty FrameBlock-TileEntity
     */
    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new FrameBlockTile();
    }

    /**
     * Is called, whenever the block is right-clicked:
     * First it is checked, whether the world is remote (this has to be done client-side only).
     * Afterwards, we check, whether the held item is some sort of block item (e.g. logs, but not torches)
     * If that's the case, we ask for the tile entity of the frame and if the frame is empty, we fill it with the held block and remove the item from the player's inventory
     * If the frame is not empty and the player holds the hammer, the contained block is dropped into the world
     *
     * @param state  state of the block that is clicked
     * @param world  world the block is placed in
     * @param pos    position (x,y,z) of block
     * @param player entity of the player that includes all important information (health, armor, inventory,
     * @param hand   which hand is used (e.g. you have a sword in your main hand and an axe in your off-hand and right click a log -> you use the off-hand, not the main hand)
     * @param trace  to determine which part of the block is clicked (upper half, lower half, right side, left side, corners...)
     * @return see {@link ActionResultType}
     */
    @Override
    public ActionResultType onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult trace) {
        ItemStack item = player.getHeldItem(hand);
        if (!world.isRemote) {
            BlockAppearanceHelper.setLightLevel(item, state, world, pos, player, hand);
            BlockAppearanceHelper.setTexture(item, state, world, player, pos);
            BlockAppearanceHelper.setDesign(world, pos, player, item);
            BlockAppearanceHelper.setDesignTexture(world, pos, player, item);
            BlockAppearanceHelper.setOverlay(world, pos, player, item);
            if (item.getItem() instanceof BlockItem) {
                if (state.get(BCBlockStateProperties.CONTAINS_BLOCK) || Objects.requireNonNull(item.getItem().getRegistryName()).getNamespace().equals(QTextureModels.MOD_ID)) {
                    return ActionResultType.PASS;
                }
                TileEntity tileEntity = world.getTileEntity(pos);
                int count = player.getHeldItem(hand).getCount();
                Block heldBlock = ((BlockItem) item.getItem()).getBlock();
                if (tileEntity instanceof FrameBlockTile && !item.isEmpty() && BlockSavingHelper.isValidBlock(heldBlock) && !state.get(CONTAINS_BLOCK)) {
                    BlockState handBlockState = ((BlockItem) item.getItem()).getBlock().getDefaultState();
                    insertBlock(world, pos, state, handBlockState);
                    if (!player.isCreative())
                        player.getHeldItem(hand).setCount(count - 1);
                }
            }
            //hammer is needed to remove the block from the frame - you can change it in the config
            if (player.getHeldItem(hand).getItem() == Registration.HAMMER.get() || (!BCModConfig.HAMMER_NEEDED.get() && player.isSneaking())) {
                if (!player.isCreative())
                    this.dropContainedBlock(world, pos);
                state = state.with(CONTAINS_BLOCK, Boolean.FALSE);
                world.setBlockState(pos, state, 2);
            }
        }
        return ActionResultType.SUCCESS;
    }

    /**
     * Used to drop the contained block
     * We check the tile entity, get the block from the tile entity and drop it at the block pos plus some small random coords in the world
     *
     * @param worldIn the world where we drop the block
     * @param pos     the block position where we drop the block
     */
    protected void dropContainedBlock(World worldIn, BlockPos pos) {
        if (!worldIn.isRemote) {
            TileEntity tileentity = worldIn.getTileEntity(pos);
            if (tileentity instanceof FrameBlockTile) {
                FrameBlockTile frameTileEntity = (FrameBlockTile) tileentity;
                BlockState blockState = frameTileEntity.getMimic();
                if (!(blockState == null)) {
                    worldIn.playEvent(1010, pos, 0);
                    frameTileEntity.clear();
                    float f = 0.7F;
                    double d0 = (double) (worldIn.rand.nextFloat() * 0.7F) + (double) 0.15F;
                    double d1 = (double) (worldIn.rand.nextFloat() * 0.7F) + (double) 0.060000002F + 0.6D;
                    double d2 = (double) (worldIn.rand.nextFloat() * 0.7F) + (double) 0.15F;
                    ItemStack itemstack1 = new ItemStack(blockState.getBlock());
                    ItemEntity itementity = new ItemEntity(worldIn, (double) pos.getX() + d0, (double) pos.getY() + d1, (double) pos.getZ() + d2, itemstack1);
                    itementity.setDefaultPickupDelay();
                    worldIn.addEntity(itementity);
                    frameTileEntity.clear();
                }
            }
        }
    }

    /**
     * Used to place a block in a frame. Therefor we need the tile entity of the block and set its mimic to the given block state.
     * Lastly, we update the block state (useful for observers or something, idk)
     *
     * @param worldIn   the world where we drop the block
     * @param pos       the block position where we drop the block
     * @param state     the old block state
     * @param handBlock the block state of the held block - the block we want to insert into the frame
     */
    public void insertBlock(IWorld worldIn, BlockPos pos, BlockState state, BlockState handBlock) {
        TileEntity tileentity = worldIn.getTileEntity(pos);
        if (tileentity instanceof FrameBlockTile) {
            FrameBlockTile frameTileEntity = (FrameBlockTile) tileentity;
            frameTileEntity.clear();
            frameTileEntity.setMimic(handBlock);
            worldIn.setBlockState(pos, state.with(CONTAINS_BLOCK, Boolean.TRUE), 2);
        }
    }

    /**
     * This method is called, whenever the state of the block changes (e.g. the block is harvested)
     *
     * @param state    old blockstate
     * @param worldIn  world of the block
     * @param pos      block position
     * @param newState new blockstate
     * @param isMoving whether the block has some sort of motion (should never be moving - false)
     */
    @Override
    public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
        if (state.getBlock() != newState.getBlock()) {
            dropContainedBlock(worldIn, pos);

            super.onReplaced(state, worldIn, pos, newState, isMoving);
        }
        if (state.get(WATERLOGGED)) {
            worldIn.getPendingFluidTicks().scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(worldIn));
        }
    }

    //unused
    public int setLightValue(BlockState state, int amount) {
        if (state.get(LIGHT_LEVEL) > 15) {
            return 15;
        }
        return state.get(LIGHT_LEVEL);
    }

    //unused
    public boolean isTransparent(BlockState state) {
        //return this.isTransparent;
        return true;
    }

    /**
     * This method returns the light value of the block, i.e. the emitted light level
     *
     * @param state state of the block
     * @param world world the block is in
     * @param pos   block position
     * @return new amount of light that is emitted by the block
     */
    @Override
    public int getLightValue(BlockState state, IBlockReader world, BlockPos pos) {
        if (state.get(LIGHT_LEVEL) > 15) {
            return 15;
        }
        return state.get(LIGHT_LEVEL);
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        BlockPos blockpos = context.getPos();
        FluidState fluidstate = context.getWorld().getFluidState(blockpos);
        if (fluidstate.getFluid() == Fluids.WATER) {
            return this.getDefaultState().with(WATERLOGGED, fluidstate.isSource());
        } else {
            return this.getDefaultState();
        }
    }

    @Override
    public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
        if (stateIn.get(WATERLOGGED)) {
            worldIn.getPendingFluidTicks().scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickRate(worldIn));
        }

        return super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
    }

    @Override
    @SuppressWarnings("deprecation")
    public FluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStillFluidState(false) : super.getFluidState(state);
    }

    @Override
    @SuppressWarnings("deprecation")
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        if (!state.get(CONTAINS_BLOCK)) {
            return CUBE;
        }
        return VoxelShapes.fullCube();
    }
}
//========SOLI DEO GLORIA========//