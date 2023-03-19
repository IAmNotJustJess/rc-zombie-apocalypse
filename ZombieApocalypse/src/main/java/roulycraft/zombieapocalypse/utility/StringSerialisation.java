package roulycraft.zombieapocalypse.utility;

import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;

public class StringSerialisation {
    public static String deserialise(String str) {
        return LegacyComponentSerializer.legacySection().serialize(MiniMessage.miniMessage().deserialize(str));
    }

    public static String serialise(String str) {
        return MiniMessage.miniMessage().serialize(LegacyComponentSerializer.legacySection().deserialize(str));
    }

}
