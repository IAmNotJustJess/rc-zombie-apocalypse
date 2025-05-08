package IAmNotJustJess.destroyTheMonument.utility;

import java.util.List;
import java.util.Random;

public class RandomElementPicker {

    public static <E> E getRandomElement(List<E> list) {
        return list.get(new Random().nextInt(list.size() - 1));
    }
}
