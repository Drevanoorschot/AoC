package day17;

import util.Util;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Main {

    static char[][] input;

    static {
        try {
            input = Arrays.stream(Util.processStringInput("/day17/input.txt")).map(String::toCharArray).toArray(char[][]::new);
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }
    }

    static long ex1() {
        Set<Cube> activeCubes = new HashSet<>();
        for (int y = 0; y < input.length; y++) {
            for (int x = 0; x < input[y].length; x++) {
                if (input[y][x] == '#') activeCubes.add(new Cube(x, y, 0));
            }
        }
        for (int i = 0; i < 6; i++) {
            Set<Cube> new_active = new HashSet<>();
            Set<Cube> finalActiveCubes = activeCubes;
            activeCubes.forEach(x -> x.neighbours.retainAll(finalActiveCubes));
            activeCubes.stream()
                    .filter(x -> x.neighbours.size() == 2 || x.neighbours.size() == 3)
                    .forEach(new_active::add);
            activeCubes.forEach(x -> x.neighbours = x.neighbours());
            Set<Cube> all_neighbours = new HashSet<>();
            activeCubes.stream()
                    .map(x -> x.neighbours)
                    .forEach(all_neighbours::addAll);
            Set<Cube> from_inactive = all_neighbours.stream()
                    .filter(n -> {
                        Set<Cube> cs = n.neighbours();
                        cs.retainAll(finalActiveCubes);
                        return cs.size() == 3;
                    })
                    .filter(n -> !finalActiveCubes.contains(n))
                    .collect(Collectors.toSet());
            from_inactive.forEach(n -> n.neighbours = n.neighbours());
            new_active.addAll(from_inactive);
            activeCubes = new_active;
        }
        return activeCubes.size();
    }

    static long ex2() {
        Set<HyperCube> activeCubes = new HashSet<>();
        for (int y = 0; y < input.length; y++) {
            for (int x = 0; x < input[y].length; x++) {
                if (input[y][x] == '#') activeCubes.add(new HyperCube(x, y, 0, 0));
            }
        }
        for (int i = 0; i < 6; i++) {
            Set<HyperCube> new_active = new HashSet<>();
            Set<HyperCube> finalActiveCubes = activeCubes;
            activeCubes.forEach(x -> x.neighbours.retainAll(finalActiveCubes));
            activeCubes.stream()
                    .filter(x -> x.neighbours.size() == 2 || x.neighbours.size() == 3)
                    .forEach(new_active::add);
            activeCubes.forEach(x -> x.neighbours = x.neighbours());
            Set<HyperCube> all_neighbours = new HashSet<>();
            activeCubes.stream()
                    .map(x -> x.neighbours)
                    .forEach(all_neighbours::addAll);
            Set<HyperCube> from_inactive = all_neighbours.stream()
                    .filter(n -> {
                        Set<HyperCube> cs = n.neighbours();
                        cs.retainAll(finalActiveCubes);
                        return cs.size() == 3;
                    })
                    .filter(n -> !finalActiveCubes.contains(n))
                    .collect(Collectors.toSet());
            from_inactive.forEach(n -> n.neighbours = n.neighbours());
            new_active.addAll(from_inactive);
            activeCubes = new_active;
        }
        return activeCubes.size();
    }

    public static void main(String[] args) {
        System.out.println("---ex1---");
        System.out.println(ex1());
        System.out.println("---ex2---");
        System.out.println(ex2());
    }
}
