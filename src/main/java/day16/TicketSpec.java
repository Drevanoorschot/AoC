package day16;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class TicketSpec {
    Map<String, Set<Range>> fieldRanges;

    TicketSpec() {
        this.fieldRanges = new HashMap<>();
    }

    static class Range {
        int start;
        int stop;

        Range(int start, int stop) {
            this.start = start;
            this.stop = stop;
        }
    }

}
