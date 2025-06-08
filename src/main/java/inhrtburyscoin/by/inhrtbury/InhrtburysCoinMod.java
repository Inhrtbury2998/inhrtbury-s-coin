package inhrtburyscoin.by.inhrtbury;

import com.mojang.logging.LogUtils;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(InhrtburysCoinMod.MODID)
public class InhrtburysCoinMod
{
    // Define mod id in a common place for everything to reference

    public static final String MODID = "inhrtburyscoin";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);

    public static final RegistryObject<CreativeModeTab> INHRTBURYSCOIN_TAB = CREATIVE_MODE_TABS.register("inhrtburyscoin_tab",
            () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(ItemsRegistry.GOLD_COIN_BUNDLE.get()))
                    .title(Component.translatable("itemGroup.inhrtburyscoin_tab"))
                    .displayItems((parameters, output) -> {
                        output.accept(ItemsRegistry.GOLD_COIN_BUNDLE.get());
                        output.accept(ItemsRegistry.GOLD_COIN.get());
                        output.accept(ItemsRegistry.FIVE_GOLD_COINS.get());
                        output.accept(ItemsRegistry.TEN_GOLD_COINS.get());
                        output.accept(ItemsRegistry.FIFTY_GOLD_COINS.get());
                        output.accept(ItemsRegistry.COPPER_COIN.get());
                        output.accept(ItemsRegistry.FIVE_COPPER_COINS.get());
                        output.accept(ItemsRegistry.TEN_COPPER_COINS.get());
                        output.accept(ItemsRegistry.FIFTY_COPPER_COINS.get());
                        output.accept(ItemsRegistry.DIAMOND_COIN.get());
                        output.accept(ItemsRegistry.FIVE_DIAMOND_COINS.get());
                        output.accept(ItemsRegistry.TEN_DIAMOND_COINS.get());
                        output.accept(ItemsRegistry.FIFTY_DIAMOND_COINS.get());
                    })
                    .build());
    public InhrtburysCoinMod(FMLJavaModLoadingContext context)
    {
        IEventBus modEventBus = context.getModEventBus();

        ItemsRegistry.ITEMS.register(modEventBus);
        CREATIVE_MODE_TABS.register(modEventBus);

    }
}
