package roulycraft.zombieapocalypse.utility;

public class MinuteSecondsFormat {
    public static String convert(int seconds) {

        String output;

        String formatOne;
        String formatTwo;

        int minutes = seconds / 60;
        seconds -= minutes * 60;

        formatOne = Integer.toString(minutes);
        formatTwo = Integer.toString(seconds);

        if (formatOne.length() == 1) {
            formatOne = "0" + formatOne;
        }

        if (formatTwo.length() == 1) {
            formatTwo = "0" + formatTwo;
        }

        output = formatOne + ":" + formatTwo;

        return output;

    }
}
