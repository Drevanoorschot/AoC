package day10;

import util.Util;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Adapter {

    public static List<Adapter> input;
    public static long successes = 0;
    public static int target;

    static {
        try {
            input = Util.processNumberInput("/day10/input.txt").stream().sorted().map(Adapter::new).collect(Collectors.toList());
        } catch (URISyntaxException | IOException e) {
                e.printStackTrace();
        }
    }

    public final int voltage;
    public int difference; //between -3 and 3
    public long next_adapter_count;
    public long possible_trace_count;

    public Adapter(int voltage) {
        this.voltage = voltage;
    }

    public static int ex1() {
        int target = 0;
        for (int i = 0; i < input.size(); i++) {
            input.get(i).difference = target - input.get(i).voltage;
            target = input.get(i).voltage;
        }
        return (int) (input.stream().map(y -> Math.abs(y.difference)).filter(x -> x == 1).count() *
                (1+ input.stream().map(y -> Math.abs(y.difference)).filter(x -> x == 3).count()));
    }

    public static long find_combs() {
        input.get(input.size() - 1).possible_trace_count = 1;
        for(int i = input.size() - 2; i >= 0; i--) {
            int current_voltage = input.get(i).voltage;
            input.get(i).possible_trace_count = input.stream()
                    .filter(x -> x.voltage > current_voltage && x.voltage <= current_voltage + 3)
                    .map(y -> y.possible_trace_count)
                    .reduce(Long::sum).get();
        }
        return input.stream().filter(x -> x.voltage <= 3).map(x -> x.possible_trace_count).reduce(Long::sum).get();
    }

    public static void findChain(int current_voltage) {
        if(current_voltage == target) {
            successes++;
            return;
        }
        Set<Adapter> next_adapters = input.stream().filter(x -> x.voltage > current_voltage && x.voltage <= current_voltage + 3).collect(Collectors.toSet());
        for(Adapter adapter : next_adapters) {
            findChain(adapter.voltage);
        }
    }

    public static void main(String[] args) {
        target = input.get(input.size() - 1).voltage; //input.size();
        System.out.println("---ex1---");
        System.out.println(ex1());
        System.out.println("---ex2---");
//        findChain(0);
        System.out.println(find_combs());
    }

}
