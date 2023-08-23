package roulycraft.zombieapocalypse.utility;

public class PolishNumberConverter {

    public static String convert(Integer number, String msg1, String msg2, String msg3) {

        int absNumber = Math.abs(number);
        String message = Integer.toString(number);
        message += " ";

        if (absNumber == 1) {
            message += msg1;
        }
        else if (absNumber % 10 >= 2 && absNumber % 10 <= 4) {
            message += msg2;
        }
        else if (absNumber >= 5 && absNumber <= 20) {
            message += msg3;
        }
        else {
            message += msg3;
        }

        return message;
    }
}
