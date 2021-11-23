package fuzs.easymagic.world.inventory;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;

import java.util.function.Function;

public interface GlobalMenu {
    default void slotsChangedGlobally(Function<AbstractContainerMenu, Container> containerAccess, ContainerLevelAccess access, Player excluded) {
        access.execute((level, pos) -> {
            if (!level.isClientSide) {
                ((ServerLevel) level).players().stream()
                        .filter(player -> player != excluded)
                        .map(player -> player.containerMenu)
                        .filter(this::isSameMenu)
                        .forEach(menu -> ((GlobalMenu) menu).slotsChangedLocally(containerAccess.apply(menu)));
            }
        });
    }

    boolean isSameMenu(AbstractContainerMenu other);

    void slotsChangedLocally(Container inventory);
}
