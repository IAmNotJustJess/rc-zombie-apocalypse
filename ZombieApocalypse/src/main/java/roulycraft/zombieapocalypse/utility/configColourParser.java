package roulycraft.zombieapocalypse.utility;

import com.google.common.base.Splitter;

public class ConfigColourParser {

    public static String getColour(String input) {

        StringBuilder output;
        Iterable<String> split;

        switch (input.length()) {

            case 6:

                output = new StringBuilder("§x");
                split = Splitter.fixedLength(1).split(input);

                for (String i : split) {

                    output.append("§").append(i);

                }

                break;

            case 1:

                output = new StringBuilder("§"+input);

                break;

            default:

                return null;
        }


        return output.toString();

    }

}
