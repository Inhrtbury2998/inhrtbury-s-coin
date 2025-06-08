package inhrtburyscoin.by.inhrtbury;

import inhrtburyscoin.by.inhrtbury.bundle.CopperCoinBundleItem;
import inhrtburyscoin.by.inhrtbury.bundle.DiamondCoinBundleItem;
import inhrtburyscoin.by.inhrtbury.bundle.GoldCoinBundleItem;
import inhrtburyscoin.by.inhrtbury.coin.CoinItem;
import inhrtburyscoin.by.inhrtbury.coin.CoinType;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ItemsRegistry {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, InhrtburysCoinMod.MODID);

    // 币袋
    public static final RegistryObject<Item> GOLD_COIN_BUNDLE =
            ITEMS.register("gold_coin_bundle",
                    () -> new GoldCoinBundleItem(new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> COPPER_COIN_BUNDLE =
            ITEMS.register("copper_coin_bundle",
                    () -> new CopperCoinBundleItem(new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> DIAMOND_COIN_BUNDLE =
            ITEMS.register("diamond_coin_bundle",
                    () -> new DiamondCoinBundleItem(new Item.Properties().stacksTo(1)));

    // 币
    public static final RegistryObject<Item> GOLD_COIN = ITEMS.register("gold_coin",
            () -> new CoinItem(new Item.Properties(), CoinType.GOLD, 1));
    public static final RegistryObject<Item> FIVE_GOLD_COINS = ITEMS.register("five_gold_coins",
            () -> new CoinItem(new Item.Properties(), CoinType.GOLD, 5));
    public static final RegistryObject<Item> TEN_GOLD_COINS = ITEMS.register("ten_gold_coins",
            () -> new CoinItem(new Item.Properties(), CoinType.GOLD, 10));
    public static final RegistryObject<Item> FIFTY_GOLD_COINS = ITEMS.register("fifty_gold_coins",
            () -> new CoinItem(new Item.Properties(), CoinType.GOLD, 50));
    public static final RegistryObject<Item> COPPER_COIN = ITEMS.register("copper_coin",
            () -> new CoinItem(new Item.Properties(), CoinType.COPPER, 1));
    public static final RegistryObject<Item> FIVE_COPPER_COINS = ITEMS.register("five_copper_coins",
            () -> new CoinItem(new Item.Properties(), CoinType.COPPER, 5));
    public static final RegistryObject<Item> TEN_COPPER_COINS = ITEMS.register("ten_copper_coins",
            () -> new CoinItem(new Item.Properties(), CoinType.COPPER, 10));
    public static final RegistryObject<Item> FIFTY_COPPER_COINS = ITEMS.register("fifty_copper_coins",
            () -> new CoinItem(new Item.Properties(), CoinType.COPPER, 50));
    public static final RegistryObject<Item> DIAMOND_COIN = ITEMS.register("diamond_coin",
            () -> new CoinItem(new Item.Properties(), CoinType.DIAMOND, 1));
    public static final RegistryObject<Item> FIVE_DIAMOND_COINS = ITEMS.register("five_diamond_coins",
            () -> new CoinItem(new Item.Properties(), CoinType.DIAMOND, 5));
    public static final RegistryObject<Item> TEN_DIAMOND_COINS = ITEMS.register("ten_diamond_coins",
            () -> new CoinItem(new Item.Properties(), CoinType.DIAMOND, 10));
    public static final RegistryObject<Item> FIFTY_DIAMOND_COINS = ITEMS.register("fifty_diamond_coins",
            () -> new CoinItem(new Item.Properties(), CoinType.DIAMOND, 50));
}
