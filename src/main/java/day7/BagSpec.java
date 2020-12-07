package day7;

import util.Util;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;
import java.util.stream.Collectors;

public class BagSpec {
    public static String[] input;
    public final String type;
    public final Map<String, Integer> typeAmountContainer;
    public Set<String> containedIn = new HashSet<>();


    public BagSpec(String bagSpec) {
        this.type = bagSpec.split(" contain ")[0].replace("bags", "bag");
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
            String type = bag.replaceAll("[0-9]+ ", "").replace("bags", "bag");
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

    public static Set<BagSpec> contructBagSet() {
        Set<BagSpec> bagSpecs = Arrays.stream(input).map(BagSpec::new).collect(Collectors.toSet());
        for (BagSpec bagSpec : bagSpecs) {
            for (String contained : bagSpec.typeAmountContainer.keySet()) {
                bagSpecs.stream().filter(spec -> spec.type.equals(contained)).forEach(s -> s.containedIn.add(bagSpec.type));
            }
        }
        return bagSpecs;
    }

    public static Set<BagSpec> findAllContainers(Set<BagSpec> allBagSpecs, String bagSpecName) {
        return allBagSpecs.stream().filter(spec -> spec.typeAmountContainer.containsKey(bagSpecName)).map(s -> s.containedIn.isEmpty() ? new HashSet<>() : findAllContainers(allBagSpecs, s.type)).reduce((x, y) -> {
            x.addAll(y);
            return x;
        }).get();
    }

    public static void main(String[] args) {
        Set<BagSpec> bagSpecs = contructBagSet();
        System.out.println(findAllContainers(bagSpecs, "shiny gold bag").size());
    }

}
