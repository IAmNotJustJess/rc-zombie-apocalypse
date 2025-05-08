package IAmNotJustJess.destroyTheMonument.utility;

import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;

import java.util.ArrayList;
import java.util.List;

public class MiniMessageParser {
    public static String Deserialize(String string) {
        return LegacyComponentSerializer.legacyAmpersand().serialize(MiniMessage.miniMessage().deserialize(string));
    }

    public static List<String> DeserializeMultiline(String string) {
        String[] splitString = string.split("<newline>");
        List<String> stringList = new ArrayList<>();
        for (String stringFromSplit : splitString) {
            stringList.add(Deserialize(stringFromSplit));
        }
        return stringList;
    }
}
