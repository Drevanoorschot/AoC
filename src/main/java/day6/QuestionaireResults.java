package day6;

import util.Util;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;
import java.util.stream.Collectors;

public class QuestionaireResults {
    public static String[] input;

    static {
        try {
            input = Util.processStringInput("/day6/input.txt", "\n\n");
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }
    }

    public static int ex1() {
        return Arrays.stream(input).map(s -> s.replaceAll("\n", "")).map(str -> str.chars().mapToObj(e->(char)e).collect(Collectors.toSet())).map(Set::size).reduce(Integer::sum).get();
    }

    public static int ex2() {
        return Arrays.stream(input)
                .map(s -> s.split("\n"))
                .map(arr -> Arrays.stream(arr).map(str -> str.chars().mapToObj(e->(char)e).collect(Collectors.toSet())))
                .map(stm -> stm.reduce((x, y) -> {x.retainAll(y); return x;}))
                .map(st -> st.get().size()).reduce(Integer::sum).get();
    }

    public static void main(String[] args) {
        System.out.println("---ex1---");
        System.out.println(ex1());
        System.out.println("---ex2---");
        System.out.println(ex2());
    }

}
