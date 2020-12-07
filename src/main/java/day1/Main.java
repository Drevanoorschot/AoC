package day1;

import util.Util;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws URISyntaxException, IOException {
        // file processing
        List<Integer> numbers = Util.processNumberInput("/day1/input.txt");
        //exercise 1
        System.out.println("---ex 1----");
        solveEx1(numbers);
        System.out.println("---ex 2----");
        solveEx2(numbers);
    }

    private static void solveEx1(List<Integer> numbers) {
        Set<Integer> complements = new HashSet<>();
        for (int number : numbers) {
            if (complements.contains(number)) {
                System.out.println(String.format("Complement found: %d, %d", 2020 - number, number));
                System.out.println(String.format("Complement product: %d", (2020 - number) * number));
            } else {
                complements.add(2020 - number);
            }
        }
    }

    private static void solveEx2(List<Integer> numbers) {
        Set<Set<Integer>> solutions = new HashSet<>();
        numbers.forEach(x -> numbers.forEach(y -> numbers.forEach(z -> solutions.add(new HashSet<Integer>(Arrays.asList(x, y, z))))));
        Set<Set<Integer>> sol = solutions.stream().filter(x -> x.size() == 3).filter(x -> (x.stream().reduce(Integer::sum)).get() == 2020).collect(Collectors.toSet());
        int solint = solutions.stream().filter(x -> x.size() == 3).filter(x -> (x.stream().reduce(Integer::sum)).get() == 2020).findFirst().get().stream().reduce((z,y) -> z * y).get();
        System.out.println(sol);
        System.out.println(solint);
    }

    private static void doNothing() {

    }
}
