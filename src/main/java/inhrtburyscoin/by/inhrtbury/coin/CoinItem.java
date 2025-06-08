package inhrtburyscoin.by.inhrtbury.coin;

import net.minecraft.world.item.Item;

public class CoinItem extends Item {
    private final CoinType coinType;
    private final int value;

    public CoinItem(Properties properties, CoinType coinType, int value) {
        super(properties);
        this.coinType = coinType;
        this.value = value;
    }

    public CoinType getCoinType() {
        return coinType;
    }

    public int getValue() {
        return value;
    }
}
