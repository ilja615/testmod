package com.github.ilja615.testmod;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;

@OnlyIn(Dist.CLIENT)
public class SnowmanRender extends MobRenderer<SnowmanEntity, SnowmanModel>
{

    public SnowmanRender(EntityRendererManager manager)
    {
        super(manager, new SnowmanModel(), 0.5f);
    }

    @Nullable
    @Override
    public ResourceLocation getEntityTexture(SnowmanEntity entity)
    {
        return new ResourceLocation("testmod:textures/entity/snowman.png");
    }
}
