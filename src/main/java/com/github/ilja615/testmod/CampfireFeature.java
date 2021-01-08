package com.github.ilja615.testmod;

import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.CampfireBlock;
import net.minecraft.block.StairsBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.*;
import net.minecraft.tags.BlockTags;
import net.minecraft.tileentity.ChestTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.IWorldGenerationBaseReader;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.server.ServerWorld;

import java.util.Collections;
import java.util.List;
import java.util.Random;

public class CampfireFeature extends Feature<NoFeatureConfig>
{
    private static final BlockState CAMPFIRE = Blocks.CAMPFIRE.getDefaultState();
    private static final BlockState HAY_BALE = Blocks.HAY_BLOCK.getDefaultState();
    private static final BlockState[] STAIRS = new BlockState[]{Blocks.OAK_STAIRS.getDefaultState(), Blocks.SPRUCE_STAIRS.getDefaultState(), Blocks.DARK_OAK_STAIRS.getDefaultState()};

    public CampfireFeature(Codec<NoFeatureConfig> p_i231953_1_)
    {
        super(p_i231953_1_);
    }

    @Override
    public boolean generate(ISeedReader worldIn, ChunkGenerator generator, Random rand, BlockPos positionIn, NoFeatureConfig config)
    {
        positionIn = new BlockPos(positionIn.getX(), 128, positionIn.getZ());

        while (positionIn.getY() > 1 && isAirOrLeavesAt(worldIn, positionIn)) positionIn = positionIn.down();

        if (!isDirtAt(worldIn, positionIn))
        {
            return false; // this tree is only allowed to grow on soil, but not on water or plant or other thing
        }
        ifAirSetBlock(worldIn, positionIn.up(), CAMPFIRE);

        if (rand.nextBoolean())
            setBlockState(worldIn, positionIn, HAY_BALE);
        else if (rand.nextBoolean())
            setBlockState(worldIn, positionIn.up(), CAMPFIRE.with(CampfireBlock.LIT, false));

        for (Direction di : Direction.values())
        {
            if (di.getAxis() != Direction.Axis.Y) {
                BlockState b = STAIRS[rand.nextInt(STAIRS.length)].with(StairsBlock.FACING, di);
                if (rand.nextBoolean()) {
                    if (isDirtAt(worldIn, positionIn.offset(di, 2)))
                        ifAirSetBlock(worldIn, positionIn.offset(di, 2).up(), b);
                } else if (rand.nextBoolean()) {
                    BlockState box = ModStuffRegistry.GIFTBOX.get().getDefaultState();
                    if (isDirtAt(worldIn, positionIn.offset(di)))
                    {
                        ifAirSetBlock(worldIn, positionIn.offset(di).up(), box);
                        TileEntity te = worldIn.getTileEntity(positionIn.offset(di).up());
                        if (te instanceof GiftboxTileEntity && worldIn.getWorld() != null)
                        {
                            LootContext.Builder lootcontext$builder = (new LootContext.Builder((ServerWorld)worldIn.getWorld())).withParameter(LootParameters.field_237457_g_, Vector3d.copyCentered(positionIn.offset(di).up())).withSeed(rand.nextLong());
                            LootTable lootTable = worldIn.getWorld().getServer().getLootTableManager().getLootTableFromLocation(ModStuffRegistry.GIFTS);
                            List<ItemStack> list = lootTable.generate(lootcontext$builder.build(LootParameterSets.CHEST));
                            Collections.shuffle(list);
                            if (list != null) ((GiftboxTileEntity)te).setItems(list);
                        }
                    }
                }
                if (rand.nextBoolean()) {
                    BlockState box = ModStuffRegistry.GIFTBOX.get().getDefaultState();
                    if (isDirtAt(worldIn, positionIn.offset(di).offset(di.rotateY())))
                    {
                        ifAirSetBlock(worldIn, positionIn.offset(di).offset(di.rotateY()).up(), box);
                        TileEntity te = worldIn.getTileEntity(positionIn.offset(di).offset(di.rotateY()).up());
                        if (te instanceof GiftboxTileEntity && worldIn.getWorld() != null)
                        {
                            LootContext.Builder lootcontext$builder = (new LootContext.Builder((ServerWorld)worldIn.getWorld())).withParameter(LootParameters.field_237457_g_, Vector3d.copyCentered(positionIn.offset(di).offset(di.rotateY()).up())).withSeed(rand.nextLong());
                            LootTable lootTable = worldIn.getWorld().getServer().getLootTableManager().getLootTableFromLocation(ModStuffRegistry.GIFTS);
                            List<ItemStack> list = lootTable.generate(lootcontext$builder.build(LootParameterSets.CHEST));
                            Collections.shuffle(list);
                            if (list != null) ((GiftboxTileEntity)te).setItems(list);
                        }
                    }
                }
            }
        }

        return true;
    }

    public static boolean isAirOrLeavesAt(IWorldGenerationBaseReader p_236412_0_, BlockPos p_236412_1_)
    {
        return p_236412_0_.hasBlockState(p_236412_1_, (p_236411_0_) ->
        {
            return p_236411_0_.isAir() || p_236411_0_.isIn(BlockTags.LEAVES);
        });
    }

    private void ifAirSetBlock(ISeedReader worldIn, BlockPos pos, BlockState blockState)
    {
        if (isAirAt(worldIn, pos)) setBlockState(worldIn, pos, blockState);
    }
}
