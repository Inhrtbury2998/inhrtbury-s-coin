package inhrtburyscoin.by.inhrtbury.bundle;

import inhrtburyscoin.by.inhrtbury.ItemsRegistry;
import inhrtburyscoin.by.inhrtbury.coin.CoinType;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class GoldCoinBundleItem extends BundleItem {
    private static final int WITHDRAW_AMOUNT = 1000;

    public GoldCoinBundleItem(Properties properties) {
        super(properties);
    }
    @Override
    public boolean isBarVisible(@NotNull ItemStack stack) {
        return true;
    }

    @Override
    public int getBarWidth(@NotNull ItemStack stack) {
        int stored = getStoredCoins(stack);
        float ratio = stored / (float) MAX_COINS;
        return Math.round(13.0F * ratio);
    }

    @Override
    public int getBarColor(@NotNull ItemStack stack) {
        return 0xFFD700; // 金色
    }

    @Override
    protected InteractionResultHolder<ItemStack> depositCoins(Player player, ItemStack bundle) {
        int stored = getStoredCoins(bundle);
        int availableSpace = MAX_COINS - stored;

        if (availableSpace <= 0) {
            player.displayClientMessage(Component.translatable("bundle.inhrtburyscoin.full"), true);
            return InteractionResultHolder.fail(bundle);
        }

        int coinsInInventory = getCoinsInInventory(player, CoinType.GOLD);
        if (coinsInInventory <= 0) {
            player.displayClientMessage(Component.translatable("bundle.inhrtburyscoin.no_coins"), true);
            return InteractionResultHolder.fail(bundle);
        }

        int toDeposit = Math.min(availableSpace, Math.min(coinsInInventory, MAX_COINS));
        removeCoinsFromInventory(player, toDeposit, CoinType.GOLD);
        setStoredCoins(bundle, stored + toDeposit);

        player.displayClientMessage(
                Component.translatable("bundle.inhrtburyscoin.deposited", toDeposit),
                true
        );

        return InteractionResultHolder.success(bundle);
    }

    @Override
    protected InteractionResultHolder<ItemStack> withdrawCoins(Player player, ItemStack bundle) {
        int stored = getStoredCoins(bundle);

        if (stored <= 0) {
            player.displayClientMessage(Component.translatable("bundle.inhrtburyscoin.empty"), true);
            return InteractionResultHolder.fail(bundle);
        }

        int toWithdraw = Math.min(stored, WITHDRAW_AMOUNT);
        setStoredCoins(bundle, stored - toWithdraw);

        // 优先使用大面额硬币
        int remaining = toWithdraw;

        Item fiftyCoin = ItemsRegistry.FIFTY_GOLD_COINS.get();
        int fiftyCount = remaining / 50;
        if (fiftyCount > 0) {
            player.addItem(new ItemStack(fiftyCoin, fiftyCount));
            remaining %= 50;
        }
        if (remaining >= 10) {
            Item tenCoin = ItemsRegistry.TEN_GOLD_COINS.get();
            int tenCount = remaining / 10;
            player.addItem(new ItemStack(tenCoin, tenCount));
            remaining %= 10;
        }
        if (remaining >= 5) {
            Item fiveCoin = ItemsRegistry.FIVE_GOLD_COINS.get();
            int fiveCount = remaining / 5;
            player.addItem(new ItemStack(fiveCoin, fiveCount));
            remaining %= 5;
        }
        if (remaining > 0) {
            Item oneCoin = ItemsRegistry.GOLD_COIN.get();
            player.addItem(new ItemStack(oneCoin, remaining));
        }

        player.displayClientMessage(
                Component.translatable("bundle.inhrtburyscoin.withdrawn", toWithdraw),
                true
        );

        return InteractionResultHolder.success(bundle);
    }
}
