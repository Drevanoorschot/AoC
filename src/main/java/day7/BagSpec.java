package day7;

import util.Util;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class BagSpec {
    public static String[] input;
    public final String type;
    public final Map<String, Integer> typeAmountContainer;


    public BagSpec(String bagSpec) {
        this.type = bagSpec.split(" contain ")[0];
        String[] containings = bagSpec.split(" contain ")[1].split(", ");
        Map<String, Integer> tempMap = new HashMap<>();
        for (String bag : bagSpec.replace(".", "").split(" contain ")[1].split(", ")) {
            int amount = 0;
            try {
                amount = Integer.parseInt(bag.split(" ")[0]);
            } catch (NumberFormatException e) {
                tempMap.put(bag.replaceAll("no ", ""), 0);
                continue;
            }
            String type = bag.replaceAll("[0-9]+ ", "");
            tempMap.put(type, amount);
        }
        typeAmountContainer = tempMap;
    }

    static {
        try {
            input = Util.processStringInput("/day7/input.txt");
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }
    }

    public static int ex1() {
        BagSpec[] bagSpecs = Arrays.stream(input).map(BagSpec::new).toArray(BagSpec[]::new);
        return 1;
    }

    public static void main(String[] args) {
        ex1();
    }

}
