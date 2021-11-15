package fuzs.easymagic.client;

import fuzs.easymagic.client.gui.screens.inventory.ModEnchantmentScreen;
import fuzs.easymagic.client.renderer.blockentity.ModEnchantTableRenderer;
import fuzs.easymagic.registry.ModRegistry;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.BlockEntityRendererRegistry;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;

public class EasyMagicClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ScreenRegistry.register(ModRegistry.ENCHANTMENT_MENU_TYPE, ModEnchantmentScreen::new);
        BlockEntityRendererRegistry.register(ModRegistry.ENCHANTING_TABLE_BLOCK_ENTITY_TYPE, ModEnchantTableRenderer::new);
    }
}
