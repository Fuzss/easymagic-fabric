package fuzs.easymagic.network.message;

import fuzs.easymagic.client.gui.screens.inventory.ModEnchantmentScreen;
import fuzs.puzzleslib.network.message.Message;
import net.minecraft.client.Minecraft;
import net.minecraft.core.Registry;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentInstance;

import java.util.ArrayList;
import java.util.List;

public class S2CEnchantingInfoMessage implements Message {
    private int containerId;
    private List<EnchantmentInstance> firstSlotData;
    private List<EnchantmentInstance> secondSlotData;
    private List<EnchantmentInstance> thirdSlotData;

    public S2CEnchantingInfoMessage() {

    }

    public S2CEnchantingInfoMessage(int containerId, List<EnchantmentInstance> firstSlotData, List<EnchantmentInstance> secondSlotData, List<EnchantmentInstance> thirdSlotData) {
        this.containerId = containerId;
        this.firstSlotData = firstSlotData;
        this.secondSlotData = secondSlotData;
        this.thirdSlotData = thirdSlotData;
    }

    @Override
    public void write(FriendlyByteBuf buf) {
        buf.writeByte(this.containerId);
        this.writeEnchantmentInstance(buf, this.firstSlotData);
        this.writeEnchantmentInstance(buf, this.secondSlotData);
        this.writeEnchantmentInstance(buf, this.thirdSlotData);
    }

    @Override
    public void read(FriendlyByteBuf buf) {
        this.containerId = buf.readByte();
        this.firstSlotData = this.readEnchantmentInstance(buf);
        this.secondSlotData = this.readEnchantmentInstance(buf);
        this.thirdSlotData = this.readEnchantmentInstance(buf);
    }

    private void writeEnchantmentInstance(FriendlyByteBuf buf, List<EnchantmentInstance> dataList) {
        buf.writeByte(dataList.size());
        for (EnchantmentInstance data : dataList) {
            buf.writeInt(Registry.ENCHANTMENT.getId(data.enchantment));
            buf.writeShort(data.level);
        }
    }

    private List<EnchantmentInstance> readEnchantmentInstance(FriendlyByteBuf buf) {
        int listSize = buf.readByte();
        List<EnchantmentInstance> slotData = new ArrayList<>(listSize);
        for (int i = 0; i < listSize; i++) {
            Enchantment enchantment = Registry.ENCHANTMENT.byId(buf.readInt());
            slotData.add(new EnchantmentInstance(enchantment, buf.readShort()));
        }
        return slotData;
    }

    @Override
    public EnchantingInfoHandler makeHandler() {
        return new EnchantingInfoHandler();
    }

    private static class EnchantingInfoHandler extends PacketHandler<S2CEnchantingInfoMessage> {
        @Override
        public void handle(S2CEnchantingInfoMessage packet, Player player, Object gameInstance) {
            if (((Minecraft) gameInstance).screen instanceof ModEnchantmentScreen screen) {
                if (player.containerMenu.containerId == packet.containerId) {
                    screen.setSlotTooltip(0, packet.firstSlotData);
                    screen.setSlotTooltip(1, packet.secondSlotData);
                    screen.setSlotTooltip(2, packet.thirdSlotData);
                }
            }
        }
    }
}
