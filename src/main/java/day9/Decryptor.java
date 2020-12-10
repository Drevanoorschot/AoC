package day9;

import util.Util;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Decryptor {
    public static List<Long> input;

    static {
        try {
            input = Util.processLargeNumberInput("/day9/input.txt");
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }
    }

    public static long ex1() {
        for (int i = 25; i < input.size(); i++) {
            long matcher = input.get(i);
            List<Long> preamble = input.subList(i - 25, i);
            boolean foundPair = false;
            for (long x : preamble) {
                for (long y : preamble) {
                    if (x + y == matcher) {
                        foundPair = true;
                        break;
                    }
                }
                if (foundPair) break;
            }
            if (!foundPair) {
                return input.get(i);
            }
//            if (!preamble.stream().map(x -> preamble.stream().map(y -> x + y).collect(Collectors.toList())).reduce((l1, l2) -> {l1.addAll(l2); return l1;}).filter(n -> n.equals(matcher)).isPresent()) return input.get(i);
        }
        return -1;
    }

    public static long ex2(long target) {
        for (int i = 0; i < input.size(); i++) {
            long cont_sum = 0;
            int p = i;
            long min = input.get(i);
            long max = input.get(i);
            while(cont_sum < target) {
                cont_sum += input.get(p);
                min = min > input.get(p) ? input.get(p) : min;
                max = max < input.get(p) ? input.get(p) : max;
                if(cont_sum == target) {
                    return min + max;
                }
                p++;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println("---ex1---");
        long ex1_ans = ex1();
        System.out.println(ex1_ans);
        System.out.println("---ex2---");
        System.out.println(ex2(ex1_ans));

    }
}
