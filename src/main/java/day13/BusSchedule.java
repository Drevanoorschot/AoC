package day13;

import util.Util;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;
import java.util.stream.Collectors;

public class BusSchedule {
    static String[] input;

    static {
        try {
            input = Util.processStringInput("/day13/input.txt");
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }
    }


    List<Integer> ids;
    int arrival;
    Map<Long, Long> id_offsets;

    BusSchedule() {
        arrival = Integer.parseInt(input[0]);
        ids = Arrays.stream(input[1].split(",")).filter(x -> !x.equals("x")).map(Integer::parseInt).collect(Collectors.toList());
        id_offsets = new HashMap<>();
        List<Integer> lookupArray = Arrays.stream(input[1].split(",")).map(x -> x.equals("x") ? "-1" : x).map(Integer::parseInt).collect(Collectors.toList());
        ids.forEach(id -> id_offsets.put((long) id, (long) lookupArray.indexOf(id)));
    }

    public int ex1() {
        Map<Integer, Integer> arrivalmap = new HashMap<>();
        ids.forEach(id -> arrivalmap.put((((arrival / id) + 1) * id) - arrival, id));
        int minWaiting = arrivalmap.keySet().stream().reduce((x,y) -> x < y ? x : y).get();
        return minWaiting * arrivalmap.get(minWaiting);
    }




    public static void main(String[] args) {
        BusSchedule busSchedule = new BusSchedule();
        System.out.println("---ex1---");
        System.out.println(busSchedule.ex1());
        System.out.println("---ex2---");

    }
}