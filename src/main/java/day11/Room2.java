package day11;

import util.Util;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static day11.SeatState.*;

public class Room2 {
    static String[] input;

    static {
        try {
            input = Util.processStringInput("/day11/input.txt");
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }
    }

    SeatState[][] seatSpace;

    public Room2() {
        seatSpace = new SeatState[input.length][input[0].length()];
        for (int row = 0; row < seatSpace.length; row++) {
            for (int col = 0; col < seatSpace[row].length; col++) {
                seatSpace[row][col] = charToState.get(input[row].charAt(col));
            }
        }
    }

    public static RoomChangeResult changeRoom(Room2 room) {
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

    public static boolean occupiable(Room2 r, int row, int col) {
        SeatState[][] room = r.seatSpace;
        if (room[row][col] != EMPTY) return false;
        return getAdjacency(room, row, col).stream().noneMatch(x -> x == FULL);
    }

    public static boolean leavable(Room2 r, int row, int col) {
        SeatState[][] room = r.seatSpace;
        if (room[row][col] != FULL) return false;
        return getAdjacency(room, row, col).stream().filter(x -> x == FULL).count() >= 5;
    }

    public static List<SeatState> getAdjacency(SeatState[][] room, int row, int col) {
        List<SeatState> adjacencies = new ArrayList<>();
        int row_max = room.length - 1;
        int col_max = room[0].length - 1;

        int r;
        int c;

        //UP
        r = row - 1;
        c = col;
        while(r >= 0) {
            if(room[r][c] != FLOOR) {
                adjacencies.add(room[r][c]);
                break;
            }
            r--;
        }
        //DOWN
        r = row + 1;
        c = col;
        while(r <= row_max) {
            if(room[r][c] != FLOOR) {
                adjacencies.add(room[r][c]);
                break;
            }
            r++;
        }
        //LEFT
        r = row;
        c = col - 1;
        while(c >= 0) {
            if(room[r][c] != FLOOR) {
                adjacencies.add(room[r][c]);
                break;
            }
            c--;
        }
        //RIGHT
        r = row;
        c = col + 1;
        while(c <= col_max) {
            if(room[r][c] != FLOOR) {
                adjacencies.add(room[r][c]);
                break;
            }
            c++;
        }
        //LEFT UP
        r = row - 1;
        c = col - 1;
        while(r >= 0 && c >= 0) {
            if(room[r][c] != FLOOR) {
                adjacencies.add(room[r][c]);
                break;
            }
            c--;
            r--;
        }
        //RIGHT UP
        r = row - 1;
        c = col + 1;
        while(r >= 0 && c <= col_max) {
            if(room[r][c] != FLOOR) {
                adjacencies.add(room[r][c]);
                break;
            }
            c++;
            r--;
        }
        //LEFT DOWN
        r = row + 1;
        c = col - 1;
        while(r <= row_max && c >= 0) {
            if(room[r][c] != FLOOR) {
                adjacencies.add(room[r][c]);
                break;
            }
            c--;
            r++;
        }
        //RIGHT DOWN
        r = row + 1;
        c = col + 1;
        while(r <= row_max && c <= col_max) {
            if(room[r][c] != FLOOR) {
                adjacencies.add(room[r][c]);
                break;
            }
            c++;
            r++;
        }
        return adjacencies;
    }

    public static void main(String[] args) {
        System.out.println("---ex2---");
        boolean changing = true;
        Room2 room = new Room2();
        while(changing) {
            RoomChangeResult result = changeRoom(room);
            room.seatSpace = result.newRoom;
            changing = result.changed;
        }
        long full_count = Arrays.stream(room.seatSpace).map(x -> Arrays.stream(x).filter(s -> s == FULL).count()).reduce(Long::sum).get();
        System.out.println(full_count);
    }

}
