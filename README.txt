TestMod
Also known as: The Curse Aurora.
Made by ilja615.

--- INFO ABOUT THE MOD:
this mod was made for Only One - Winter Competition.
this mod is for forge 1.16.4 version 35.1.28

--- CONTENT ABOUT INSIDE THE MOD THAT COUNTS TOWARDS THE SCORE SYSTEM FOR THE COMPETITION:
1 Block:
    Aurora Cursed Snow Block, this block deals damage and inflicts negative effects on entities who touch it.
    It can be crafted from 4 Snowball Empowered in a small square shape.
    It can be found in the BUILDING_BLOCKS itemGroup.
1 Item:
    Snowball Empowered, this snowball can be thrown and does 1 or 2 damage and 5 to blazes.
    It can be found in the MATERIALS itemGroup.
    It can be obtained in survival in 3 ways:
    - From killing Snowman (amount get: 0-15)
    - From breaking AuroraCursedSnowBlock (amount get: 4)
    - From the gifts loottable (amount get: either 0 or 2-17)
1 Non-Living Entity:
    Snowball Empowered (this is the entity form of it, this is a projectile)
1 Living Entity:
    Snowman, this evil snowman was made by the aurora curse, he is an enemy that throws SnowballEmpowered at you. You can also have fun snowball fights with them.
    They spawn in most biomes. (read more about what "most" biomes exactly means later)
1 Block with Tile Entity/GUI/Container:
    Giftbox, This block can store 3 stacks of items.
    It can be found at campfires.
    It can also be crafted from 8 paper in the shape of how you craft a chest.
    The texture of it is random and comes in 3 variants: red/green, white/pink, and yellow/blue.
    It can be found in the BUILDING_BLOCKS itemGroup.
1 Worldgen Feature:
    Campfire, a campfire with some chairs and presents generates in most biomes. The campfire is sometimes have a haybale, and sometimes is extinguished.
    The chairs (stair blocks, not a new block) can be spruce, oak, or darkOak wooden. The presents are Giftbox blocks with 3 random items from the gifts loottable in it.

--- REMAINING CONTENT & TWEAKS:
1 Spawn Egg:
    This one summons a Snowman.
    It can be found in the MISC itemGroup.
1 DamageSource:
    auroraCursedSnow this is the block it can kill you and that makes a custom death message in the chat.
The aurora curse:
    The aurora curse is an aurora that is rendered in the sky using a custom DimensionRenderInfo with an override for the getSkyRenderHandler, and using Perlin noise to animate it.
    The story is that this curse makes the world really cold and frozen.
    If you look in the EventThing class, or if you playtest the mod, you will likely notice that it is snowing in biomes that normally it can't snow in, such as the forest and plains.
    And also that it is permanently snowing so now I will explain that:
    In "most biomes", (the biomes that their Climate's precipitation type is not normally equal to Biome.RainType.NONE, so for example not the desert or the nether biomes)
    , the following things will occur:
    - the Climate precipitation type is changed to snow, the temperature to 0.0f and the downfall to 2.0f.
    - and the mod's Campfire feature generates here
    - and the mod's Snowman spawns here.

there is other stuff like configured_feature, container, te, loot_table, crafting recipes etc but none of them are worth explaining.


With all that out of the way I wish that you have a lot of fun with playing the mod :]
Greetings,
ilja615