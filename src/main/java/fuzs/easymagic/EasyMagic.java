package fuzs.easymagic;

import fuzs.easymagic.config.ClientConfig;
import fuzs.easymagic.config.ServerConfig;
import fuzs.easymagic.network.message.S2CEnchantingDataMessage;
import fuzs.easymagic.registry.ModRegistry;
import fuzs.puzzleslib.config.ConfigHolder;
import fuzs.puzzleslib.config.ConfigHolderImpl;
import fuzs.puzzleslib.network.MessageDirection;
import fuzs.puzzleslib.network.NetworkHandler;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EasyMagic implements ModInitializer {
    public static final String MOD_ID = "easymagic";
    public static final String MOD_NAME = "Easy Magic";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_NAME);

    public static final NetworkHandler NETWORK = NetworkHandler.of(MOD_ID);
    @SuppressWarnings("Convert2MethodRef")
    public static final ConfigHolder<ClientConfig, ServerConfig> CONFIG = ConfigHolder.of(() -> new ClientConfig(), () -> new ServerConfig());

    @Override
    public void onInitialize() {
        ((ConfigHolderImpl<?, ?>) CONFIG).addConfigs(MOD_ID);
        registerMessages();
        ModRegistry.touch();
    }

    private static void registerMessages() {
        NETWORK.register(S2CEnchantingDataMessage.class, S2CEnchantingDataMessage::new, MessageDirection.TO_CLIENT);
    }
}
