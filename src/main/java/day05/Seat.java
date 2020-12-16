package day05;

import util.Util;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;
import java.util.stream.Collectors;

public class Seat {
    public static String[] input;

    static {
        try {
            input = Util.processStringInput("/day05/input.txt", "\n");
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }
    }

    public final int row;
    public final int col;

    public Seat(String encodedSeat) {
        char[] row = (encodedSeat.substring(0, 7)).toCharArray();
        char[] col = encodedSeat.substring(7).toCharArray();

        int min = 0;
        int max = 127;
        for (int i = 0; i < row.length; i++) {
            if (row[i] == 'F') max = (int) (max - Math.ceil((max - min) / 2.0));
            else min = 1 + min + (max - min) / 2;
        }
        this.row = row[6] == 'F' ? min : max;

        min = 0;
        max = 7;
        for (int i = 0; i < col.length; i++) {
            if (col[i] == 'L') max = (int) (max - Math.ceil((max - min) / 2.0));
            else min = 1 + min + (max - min) / 2;
        }
        this.col = col[2] == 'L' ? min : max;
    }

    public int id() {
        return this.row * 8 + this.col;
    }

    public static int ex1() {
        return Arrays.stream(input).map(Seat::new).map(Seat::id).reduce((z, y) -> z > y ? z : y).get();
    }

    public static Set<Integer> ex2() {
        Set<Integer> seats = Arrays.stream(input).map(Seat::new).map(Seat::id).collect(Collectors.toSet());
        Set<Integer> possible_seats = new HashSet<>();
        for (int s1 : seats) {
            for (int s2 : seats) {
                if (s1 + 2 == s2 && !seats.contains(s1 + 1)) {
                    possible_seats.add(s1 + 1);
                }
            }
        }
        return possible_seats;
    }

    public static void main(String[] args) {
        System.out.println(new Seat("FBFBBFFRLR").id());
        System.out.println(new Seat("BFFFBBFRRR").id());
        System.out.println(new Seat("FFFBBBFRRR").id());
        System.out.println(new Seat("BBFFBBFRLL").id());
        System.out.println("---ex1---");
        System.out.println(ex1());
        System.out.println("---ex2---");
        System.out.println(ex2());

    }

}
