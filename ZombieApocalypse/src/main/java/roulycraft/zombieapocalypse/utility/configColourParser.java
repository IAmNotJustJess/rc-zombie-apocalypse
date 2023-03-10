package roulycraft.zombieapocalypse.utility;
public class ConfigColourParser {

    public static String getColour(String input) {

        StringBuilder output;
        String[] split;

        switch (input.length()) {

            case 6:

                output = new StringBuilder("§x");
                split = input.split("");

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
