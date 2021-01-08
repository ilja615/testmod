package com.github.ilja615.testmod;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

public class EmpoweredSnowballItem extends Item
{
    public EmpoweredSnowballItem(Properties p_i48466_1_) {
        super(p_i48466_1_);
    }

    public ActionResult<ItemStack> onItemRightClick(World p_77659_1_, PlayerEntity p_77659_2_, Hand p_77659_3_)
    {
        ItemStack lvt_4_1_ = p_77659_2_.getHeldItem(p_77659_3_);
        p_77659_1_.playSound((PlayerEntity)null, p_77659_2_.getPosX(), p_77659_2_.getPosY(), p_77659_2_.getPosZ(), SoundEvents.ENTITY_SNOWBALL_THROW, SoundCategory.NEUTRAL, 0.5F, 0.4F / (random.nextFloat() * 0.4F + 0.8F));
        if (!p_77659_1_.isRemote)
        {
            EmpoweredSnowballEntity lvt_5_1_ = new EmpoweredSnowballEntity(p_77659_1_, p_77659_2_);
            lvt_5_1_.setItem(lvt_4_1_);
            lvt_5_1_.func_234612_a_(p_77659_2_, p_77659_2_.rotationPitch, p_77659_2_.rotationYaw, 0.0F, 1.5F, 1.0F);
            p_77659_1_.addEntity(lvt_5_1_);
        }

        p_77659_2_.addStat(Stats.ITEM_USED.get(this));
        if (!p_77659_2_.abilities.isCreativeMode)
        {
            lvt_4_1_.shrink(1);
        }

        return ActionResult.func_233538_a_(lvt_4_1_, p_77659_1_.isRemote());
    }
}
