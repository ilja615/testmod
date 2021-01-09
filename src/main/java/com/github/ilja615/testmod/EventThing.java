package com.github.ilja615.testmod;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityClassification;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.storage.IWorldInfo;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

public class EventThing
{
    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
    public static class EventsForTheMod
    {
        @SubscribeEvent
        public static void biomeModification(final BiomeLoadingEvent event)
        {
            if (event.getClimate().precipitation != Biome.RainType.NONE)
            {
                event.setClimate(new Biome.Climate(Biome.RainType.SNOW, 0.0f, event.getClimate().temperatureModifier, 2.0f));
                event.getSpawns().getSpawner(EntityClassification.MONSTER).add(new MobSpawnInfo.Spawners(ModStuffRegistry.SNOWMAN_ENTITY_TYPE.get(), 10, 1, 1));
                event.getGeneration().getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION).add(() -> ModStuffRegistry.CONFIGURED_FEATURE_LAZY_CAMP_FIRE.get());

                System.out.println("added snowy content to biome: "+event.getName().toString());
            }
        }
        @SubscribeEvent
        public static void tick(final TickEvent.WorldTickEvent event)
        {
            event.world.getWorldInfo().setRaining(true);
        }
    }
}
