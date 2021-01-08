package com.github.ilja615.testmod;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class AuroraCursedSnowBlock extends Block
{
    protected static final VoxelShape THIS_SHAPE = Block.makeCuboidShape(1.0D, 1.0D, 1.0D, 15.0D, 15.0D, 15.0D);
    private static Effect[] SOME_NEGATIVE_EFFECTS = new Effect[]{Effects.POISON, Effects.BLINDNESS, Effects.SLOWNESS, Effects.HUNGER, Effects.NAUSEA};

    public AuroraCursedSnowBlock(Properties p) { super(p); }

    //get render shape
    public VoxelShape func_230335_e_(BlockState p_230335_1_, IBlockReader p_230335_2_, BlockPos p_230335_3_) {
        return VoxelShapes.fullCube();
    }

    //get raytrace shape
    public VoxelShape func_230322_a_(BlockState p_230322_1_, IBlockReader p_230322_2_, BlockPos p_230322_3_, ISelectionContext p_230322_4_) {
        return VoxelShapes.fullCube();
    }

    //get collision shape
    public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context)
    {
        return THIS_SHAPE;
    }

    @Override
    public void onEntityCollision(BlockState state, World worldIn, BlockPos pos, Entity entityIn)
    {
        if (entityIn instanceof LivingEntity && entityIn.getType() != EntityType.SNOW_GOLEM && ((LivingEntity) entityIn).getActivePotionEffects().size() == 0)
        {
            entityIn.attackEntityFrom(ModStuffRegistry.AURORA_CURSED_SNOW, 1.0F);
            Effect effect = SOME_NEGATIVE_EFFECTS[worldIn.rand.nextInt(SOME_NEGATIVE_EFFECTS.length)];
            ((LivingEntity) entityIn).addPotionEffect(new EffectInstance(effect, 100, 1));
        }
    }
}
