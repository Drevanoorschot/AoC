package util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class Util {
    public static File openResource(String path) throws URISyntaxException {
        URL url = Util.class.getResource(path);
        return new File(url.toURI());
    }


    public static String fileToString(File file) throws IOException {
        return Files.readString(file.toPath());
    }

    public static List<Integer> processNumberInput(String path) throws URISyntaxException, IOException {
        String input = fileToString(openResource(path));
        String[] inputArray = input.split("\n");
        List<Integer> numbers = new ArrayList<>();
        List.of(inputArray).forEach(str -> numbers.add(Integer.parseInt(str)));
        return numbers;
    }

    public static String[] processStringInput(String path) throws URISyntaxException, IOException {
        String input = fileToString(openResource(path));
        return input.split("\n");
    }

    public static String[] processStringInput(String path, String delimiter) throws URISyntaxException, IOException {
        String input = fileToString(openResource(path));
        return input.split(delimiter);
    }

    public static List<Long> processLargeNumberInput(String path) throws URISyntaxException, IOException {
        String input = fileToString(openResource(path));
        String[] inputArray = input.split("\n");
        List<Long> numbers = new ArrayList<>();
        List.of(inputArray).forEach(str -> numbers.add(Long.parseLong(str)));
        return numbers;
    }
}