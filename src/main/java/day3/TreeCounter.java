package day3;

import util.Util;

import java.io.IOException;
import java.net.URISyntaxException;

public class TreeCounter {

    public static String[] input;

    static {
        try {
            input = Util.processStringInput("/day3/input.txt");
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }
    }

    public static long slopecheck(int x_step, int y_step) {
        int max_x = input[0].length();
        int max_y = input.length;

        int x = 0;
        int y = 0;
        long count = 0;
        while(y < max_y) {
            count = input[y].charAt(x) == '#' ? count + 1 : count;
            x = (x + x_step) % max_x;
            y = y + y_step;
        }
        return count;
    }

    public static long ex2() {
        return slopecheck(1, 1) *
                slopecheck(3, 1) *
                slopecheck(5, 1) *
                slopecheck(7, 1) *
                slopecheck(1, 2);
    }

    public static void main(String[] args) throws IOException, URISyntaxException {
        System.out.println("---ex1---");
        System.out.println(slopecheck(3, 1));
        System.out.println("---ex2---");
        System.out.println(ex2());
    }
}
