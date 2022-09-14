package roulycraft.zombieapocalypse.utility;

public class CatchArgumentIndexException {
    public String obtain(String[] args, Integer i) {
        try {
            String test = args[i];
            return "true";
        } catch (ArrayIndexOutOfBoundsException e) {
            return "false";
        }
    }
}