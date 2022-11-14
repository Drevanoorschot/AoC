package day16;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Ticket {
    Integer[] values;

    Ticket(List<Integer> values) {
        this.values = values.toArray(new Integer[0]);
    }
}
