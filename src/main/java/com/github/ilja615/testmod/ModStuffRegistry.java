package com.github.ilja615.testmod;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.common.util.Lazy;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModStuffRegistry
{
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, TestMod.MOD_ID);
    public static final RegistryObject<Block> AURORA_CURSED_SNOW_BLOCK = BLOCKS.register("aurora_cursed_snow_block", () -> new AuroraCursedSnowBlock(Block.Properties.from(Blocks.SNOW_BLOCK)));

    public static final DamageSource AURORA_CURSED_SNOW = new DamageSource("auroraCursedSnow");

    public static final Item.Properties ITEM_PROPERTY = new Item.Properties().group(ItemGroup.BUILDING_BLOCKS);
    public static final Item.Properties ITEM_PROPERTY2 = new Item.Properties().group(ItemGroup.MATERIALS);

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, TestMod.MOD_ID);
    public static final RegistryObject<Item> AURORA_CURSED_SNOWBALL = ITEMS.register("aurora_cursed_snowball", () -> new EmpoweredSnowballItem(ModStuffRegistry.ITEM_PROPERTY2));

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITIES, TestMod.MOD_ID);
    public static final RegistryObject<EntityType<EmpoweredSnowballEntity>> AURORA_CURSED_SNOWBALL_ENTITY_TYPE = ENTITY_TYPES.register("aurora_snowball",
            () -> EntityType.Builder.<EmpoweredSnowballEntity>create(EmpoweredSnowballEntity::new, EntityClassification.MISC).size(0.25F, 0.25F).trackingRange(4).func_233608_b_(10).build("testmod:snowball"));

    public static final EntityType<SnowmanEntity> S = EntityType.Builder.<SnowmanEntity>create(com.github.ilja615.testmod.SnowmanEntity::new, EntityClassification.CREATURE).size(0.7F, 1.9F).build("testmod:snowman");
    public static final RegistryObject<EntityType<SnowmanEntity>> SNOWMAN_ENTITY_TYPE = ENTITY_TYPES.register("snowman",
            () -> S);

    public static Item registerEntitySpawnEgg(EntityType<?> type, int color1, int color2, String name)
    {
        SpawnEggItem item = new SpawnEggItem(type, color1, color2, new Item.Properties().group(ItemGroup.MISC));
        item.setRegistryName(name);
        return item;
    }

    // feature
    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, TestMod.MOD_ID);
    public static final RegistryObject<CampfireFeature> CAMP_FIRE = FEATURES.register("camp_fire", () -> new CampfireFeature(NoFeatureConfig.field_236558_a_));
    // configured feature
    public static final Lazy<ConfiguredFeature<?, ?>> CONFIGURED_FEATURE_LAZY_CAMP_FIRE = Lazy.of(() -> Registry.register(WorldGenRegistries.CONFIGURED_FEATURE,
            new ResourceLocation(TestMod.MOD_ID, "camp_fire"), CAMP_FIRE.get()
            .withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).square().withPlacement(Placement.COUNT_EXTRA
            .configure(new AtSurfaceWithExtraConfig(0, 0.08F, 1)))));
}
