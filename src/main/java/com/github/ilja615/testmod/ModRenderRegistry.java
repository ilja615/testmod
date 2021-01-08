package com.github.ilja615.testmod;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.SpriteRenderer;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class ModRenderRegistry
{
    public static void registerEntityRenderers()
    {
        RenderingRegistry.registerEntityRenderingHandler(ModStuffRegistry.SNOWMAN_ENTITY_TYPE.get(), SnowmanRender::new);
        RenderingRegistry.registerEntityRenderingHandler(ModStuffRegistry.AURORA_CURSED_SNOWBALL_ENTITY_TYPE.get(), (renderManager) -> new SpriteRenderer<>(renderManager, Minecraft.getInstance().getItemRenderer()));
    }
}

