package fuzs.easymagic.registry;

import fuzs.easymagic.EasyMagic;
import fuzs.easymagic.world.inventory.ModEnchantmentMenu;
import fuzs.easymagic.world.level.block.entity.ModEnchantmentTableBlockEntity;
import fuzs.puzzleslib.registry.RegistryManager;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.EnchantmentTableBlockEntity;

public class ModRegistry {
    private static final RegistryManager REGISTRY = RegistryManager.of(EasyMagic.MOD_ID);
    public static final BlockEntityType<EnchantmentTableBlockEntity> ENCHANTING_TABLE_BLOCK_ENTITY_TYPE = REGISTRY.registerRawBlockEntityType("enchanting_table", () -> FabricBlockEntityTypeBuilder.create(ModEnchantmentTableBlockEntity::new, Blocks.ENCHANTING_TABLE));
    public static final MenuType<ModEnchantmentMenu> ENCHANTMENT_MENU_TYPE = REGISTRY.registerRawMenuType("enchanting", () -> ModEnchantmentMenu::new);

    public static void touch() {

    }
}
