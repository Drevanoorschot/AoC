package day15;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class NumberGame {

    Map<Integer, Integer> number_last_turn;
    int turn;
    int next_number;
    boolean first;

    NumberGame(Map<Integer, Integer> input, int turn, int next_number) {
        number_last_turn = input;
        this.next_number = next_number;
        this.turn = turn;
    }

    private void play(int till_turn) {
        while (turn < till_turn) {
            int old_number = next_number;
            if (!number_last_turn.containsKey(next_number)) {
                next_number = 0;
            } else {
                int last_mentioned = number_last_turn.get(next_number);
                next_number = turn - last_mentioned;
            }
            number_last_turn.put(old_number, turn);
            turn++;
        }
    }

    public static void main(String[] args) {
        System.out.println("---ex1---");
        Map<Integer, Integer> number_last_turn = new HashMap<>();
        number_last_turn.put(11, 1);
        number_last_turn.put(0, 2);
        number_last_turn.put(1, 3);
        number_last_turn.put(10, 4);
        number_last_turn.put(5, 5);
        NumberGame ng = new NumberGame(number_last_turn, 6, 19);
        ng.play(2020);
        System.out.println(ng.next_number);

        System.out.println("---ex2---");
        LocalDateTime start = LocalDateTime.now();
        number_last_turn = new HashMap<>();
        number_last_turn.put(11, 1);
        number_last_turn.put(0, 2);
        number_last_turn.put(1, 3);
        number_last_turn.put(10, 4);
        number_last_turn.put(5, 5);
        ng = new NumberGame(number_last_turn, 6, 19);
        ng.play(30_000_000);
        LocalDateTime end = LocalDateTime.now();
        System.out.println(ng.next_number);
    }
}
