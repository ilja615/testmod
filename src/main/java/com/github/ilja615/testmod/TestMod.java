package com.github.ilja615.testmod;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;

import static com.github.ilja615.testmod.TestMod.MOD_ID;

@Mod(MOD_ID)
public class TestMod
{
    public static final String MOD_ID = "testmod";

    public TestMod()
    {
        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::setupCommon);

        ModStuffRegistry.ITEMS.register(modEventBus);
        ModStuffRegistry.BLOCKS.register(modEventBus);
        ModStuffRegistry.ENTITY_TYPES.register(modEventBus);
        ModStuffRegistry.FEATURES.register(modEventBus);
    }

    private void setupCommon(final FMLCommonSetupEvent event)
    {
        GlobalEntityTypeAttributes.put(ModStuffRegistry.SNOWMAN_ENTITY_TYPE.get(), SnowmanEntity.prepareAttributes().create());
    }

    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {
        @SubscribeEvent
        public static void clientSetup(final FMLClientSetupEvent event)
        {
            ModRenderRegistry.registerEntityRenderers();
        }

        @SubscribeEvent
        public static void onRegisterItems(final RegistryEvent.Register<Item> event) {
            final IForgeRegistry<Item> registry = event.getRegistry();
            ModStuffRegistry.BLOCKS.getEntries().stream().map(RegistryObject::get).forEach(block -> {
                final BlockItem blockItem = new BlockItem(block, ModStuffRegistry.ITEM_PROPERTY);
                blockItem.setRegistryName(block.getRegistryName());
                registry.register(blockItem);
            });
            event.getRegistry().registerAll(ModStuffRegistry.registerEntitySpawnEgg(ModStuffRegistry.S, 0xffffff, 0x121212, "snowman_egg"));
        }
    }
}
