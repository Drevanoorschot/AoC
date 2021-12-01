package day11;

import day10.Adapter;
import util.Util;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static day11.SeatState.*;

public class Room {
    static String[] input;

    static {
        try {
            input = Util.processStringInput("/day11/input.txt");
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }
    }

    SeatState[][] seatSpace;

    public Room() {
        seatSpace = new SeatState[input.length][input[0].length()];
        for (int row = 0; row < seatSpace.length; row++) {
            for (int col = 0; col < seatSpace[row].length; col++) {
                seatSpace[row][col] = charToState.get(input[row].charAt(col));
            }
        }
    }

    public static RoomChangeResult changeRoom(Room room) {
        SeatState[][] newState = new SeatState[input.length][input[0].length()];
        boolean changed = false;
        for (int row = 0; row < newState.length; row++) {
            for (int col = 0; col < newState[row].length; col++) {
                if (occupiable(room, row, col)) {
                    newState[row][col] = FULL;
                    changed = true;
                } else if (leavable(room, row, col)) {
                    newState[row][col] = EMPTY;
                    changed = true;
                } else newState[row][col] = room.seatSpace[row][col];
            }
        }
        return new RoomChangeResult(newState, changed);
    }

    public static boolean occupiable(Room r, int row, int col) {
        SeatState[][] room = r.seatSpace;
        if (room[row][col] != EMPTY) return false;
        return getAdjacency(room, row, col).stream().noneMatch(x -> x == FULL);
    }

    public static boolean leavable(Room r, int row, int col) {
        SeatState[][] room = r.seatSpace;
        if (room[row][col] != FULL) return false;
        return getAdjacency(room, row, col).stream().filter(x -> x == FULL).count() >= 4;
    }

    public static List<SeatState> getAdjacency(SeatState[][] room, int row, int col) {
        List<SeatState> adjacencies = new ArrayList<>();
        int row_max = room.length - 1;
        int col_max = room[0].length - 1;
        if (row > 0) adjacencies.add(room[row - 1][col]);
        if (row < row_max) adjacencies.add(room[row + 1][col]);
        if (col > 0) adjacencies.add(room[row][col - 1]);
        if (col < col_max) adjacencies.add(room[row][col + 1]);

        if (row > 0 && col > 0) adjacencies.add(room[row - 1][col - 1]);
        if (row > 0 && col < col_max) adjacencies.add(room[row - 1][col + 1]);
        if (row < row_max && col > 0) adjacencies.add(room[row + 1][col - 1]);
        if (row < row_max && col < col_max) adjacencies.add(room[row + 1][col + 1]);
        return adjacencies;
    }

    public static void main(String[] args) {
        System.out.println("---ex1---");
        boolean changing = true;
        Room room = new Room();
        while(changing) {
            RoomChangeResult result = changeRoom(room);
            room.seatSpace = result.newRoom;
            changing = result.changed;
        }
        long full_count = Arrays.stream(room.seatSpace).map(x -> Arrays.stream(x).filter(s -> s == FULL).count()).reduce(Long::sum).get();
        System.out.println(full_count);
    }

}
