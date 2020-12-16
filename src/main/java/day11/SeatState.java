package day11;

import java.util.HashMap;
import java.util.Map;

public enum SeatState {
    EMPTY, FULL, FLOOR;

    public static final Map<Character, SeatState> charToState = initCharToStateMap();

    private static Map<Character, SeatState> initCharToStateMap() {
        Map<Character, SeatState> map = new HashMap<>();
        map.put('.', FLOOR);
        map.put('#', FULL);
        map.put('L', EMPTY);
        return map;
    }


}
