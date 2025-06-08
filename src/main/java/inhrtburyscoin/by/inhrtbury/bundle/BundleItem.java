package inhrtburyscoin.by.inhrtbury.bundle;

import inhrtburyscoin.by.inhrtbury.coin.CoinItem;
import inhrtburyscoin.by.inhrtbury.coin.CoinType;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public abstract class BundleItem extends Item {
    protected static final int MAX_COINS = 10000; // 改为 protected
    private static final String STORED_COINS_TAG = "StoredCoins";

    public BundleItem(Properties properties) {
        super(properties);
    }

    // 获取币袋中存储的硬币数量
    public int getStoredCoins(ItemStack stack) {
        CompoundTag tag = stack.getTag();
        return tag != null ? tag.getInt(STORED_COINS_TAG) : 0;
    }

    // 设置币袋中存储的硬币数量 (改为 protected)
    protected void setStoredCoins(ItemStack stack, int amount) {
        CompoundTag tag = stack.getOrCreateTag();
        tag.putInt(STORED_COINS_TAG, Math.min(amount, MAX_COINS));
    }

    // 获取玩家背包中特定类型的硬币总价值 (改为 protected)
    protected int getCoinsInInventory(Player player, CoinType coinType) {
        int total = 0;
        for (ItemStack stack : player.getInventory().items) {
            if (stack.getItem() instanceof CoinItem coinItem &&
                    coinItem.getCoinType() == coinType) {
                total += stack.getCount() * coinItem.getValue();
            }
        }
        return total;
    }

    // 从玩家背包中移除指定价值的硬币 (改为 protected)
    protected void removeCoinsFromInventory(Player player, int amountToRemove, CoinType coinType) {
        int remaining = amountToRemove;

        // 优先移除小面额硬币
        for (int i = 0; i < player.getInventory().items.size(); i++) {
            ItemStack stack = player.getInventory().items.get(i);
            if (stack.getItem() instanceof CoinItem coinItem &&
                    coinItem.getCoinType() == coinType) {

                int coinValue = coinItem.getValue();
                int stackValue = stack.getCount() * coinValue;

                if (stackValue <= remaining) {
                    // 移除整个堆叠
                    player.getInventory().items.set(i, ItemStack.EMPTY);
                    remaining -= stackValue;
                } else {
                    // 移除部分硬币
                    int coinsToRemove = remaining / coinValue;
                    if (coinsToRemove > 0) {
                        stack.shrink(coinsToRemove);
                        remaining -= coinsToRemove * coinValue;
                    }
                }

                if (remaining <= 0) break;
            }
        }
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack bundle = player.getItemInHand(hand);

        if (!level.isClientSide) {
            if (player.isShiftKeyDown()) {
                return withdrawCoins(player, bundle);
            } else {
                return depositCoins(player, bundle);
            }
        }
        return InteractionResultHolder.pass(bundle);
    }

    // 抽象方法，子类实现具体存取逻辑
    protected abstract InteractionResultHolder<ItemStack> depositCoins(Player player, ItemStack bundle);
    protected abstract InteractionResultHolder<ItemStack> withdrawCoins(Player player, ItemStack bundle);
}