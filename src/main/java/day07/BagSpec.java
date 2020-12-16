package day07;

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
                continue;
            }
            String type = bag.replaceAll("[0-9]+ ", "").replace("bags", "bag");
            tempMap.put(type, amount);
        }
        typeAmountContainer = tempMap;
    }

    static {
        try {
            input = Util.processStringInput("/day07/input.txt");
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

    public static Set<String> findAllContainers(Set<BagSpec> allBagSpecs, String bagSpecName) {
        Set<String> containers = allBagSpecs.stream().filter(s -> s.type.equals(bagSpecName)).findFirst().get().containedIn;
        Set<String> tempContainers = new HashSet<>();
        for(String bag : containers) {
            Set<String> containersOfContainer = findAllContainers(allBagSpecs, bag);
            tempContainers.addAll(containersOfContainer);
        }
        containers.addAll(tempContainers);
        return containers;
    }
    public static int countInnerContainers(Set<BagSpec> allBagSpecs, String bagSpecName) {
        BagSpec bagSpec = allBagSpecs.stream().filter(s -> s.type.equals(bagSpecName)).findFirst().get();
        int count = bagSpec.typeAmountContainer.isEmpty() ? 0 : bagSpec.typeAmountContainer.keySet().stream().map(bagSpec.typeAmountContainer::get).reduce(Integer::sum).get();
        for (String spec : bagSpec.typeAmountContainer.keySet()) {
            count += bagSpec.typeAmountContainer.get(spec) * countInnerContainers(allBagSpecs, spec);
        }
        return count;
    }

    public static void main(String[] args) {
        Set<BagSpec> bagSpecs = contructBagSet();
        System.out.println(findAllContainers(bagSpecs, "shiny gold bag").size());
        System.out.println(countInnerContainers(bagSpecs, "shiny gold bag"));
    }

}
