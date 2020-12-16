package day02;

import util.Util;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

public class PasswordSpec {
    public final int min;
    public final int max;
    public final char c;
    public final String password;


    public PasswordSpec(String passwordSpec) {
        this.min = Integer.parseInt(passwordSpec.split("-")[0]);
        this.max = Integer.parseInt(passwordSpec.split("-")[1].split(" ")[0]);
        this.c = passwordSpec.split(" ")[1].charAt(0);
        this.password = passwordSpec.split(" ")[2];
    }

    public int countChar() {
        List<Character> chars = password.chars().mapToObj(e -> (char) e).collect(Collectors.toList());
        return (int) chars.stream().filter(c -> c == this.c).count();
    }

    public boolean checkCharPos() {
        char[] chars = password.toCharArray();
        int correct = 0;
        if (chars[min - 1] == c) correct++;
        if (chars[max - 1] == c) correct++;
        return correct == 1;
    }

    public static void main(String[] args) throws IOException, URISyntaxException {
        String[] input = Util.processStringInput("/day02/input.txt");
        //ex1
        int ex1Count = 0;
        int ex2Count = 0;
        for (String i : input) {
            PasswordSpec ps = new PasswordSpec(i);
            if (ps.countChar() >= ps.min && ps.countChar() <= ps.max) ex1Count++;
            if (ps.checkCharPos()) ex2Count++;
        }
        System.out.println(String.format("exercise 1: %d", ex1Count));
        System.out.println(String.format("exercise 2: %d", ex2Count));
    }

}
