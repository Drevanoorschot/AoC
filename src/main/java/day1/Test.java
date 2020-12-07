package day1;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class Test {
    public static void main(String[] args) {
        Set<Integer> treeset = new TreeSet<>();
        for (int i = 0; i < 100; i++) {
            treeset.add(i*i);
            System.out.println(treeset);
        }
    }
}
