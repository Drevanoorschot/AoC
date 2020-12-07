package day4;

import util.Util;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;


public class Day4 {

    public static String[] input;

    public static final Set<String> mandatory_fields = Set.of(
            "byr",
            "iyr",
            "eyr",
            "hgt",
            "hcl",
            "ecl",
            "pid"
    );

    public static final Set<String> eye_colors = Set.of(
            "amb",
            "blu",
            "brn",
            "gry",
            "grn",
            "hzl",
            "oth"
    );

    static {
        try {
            input = Util.processStringInput("/day4/input.txt", "\n\n");
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }
    }

    public static int ex1() {
        int count = 0;
        for (String i : input) {
            count = (Set.of(Arrays.copyOf(i.split(":"), i.split(":").length - 1)).stream().map(x -> x.substring(x.length() - 3)).collect(Collectors.toSet())).containsAll(mandatory_fields) ? count + 1 : count;
        }
        return count;
    }

    public static int ex2() {
        int count = 0;
        for (String i : input) {
            if (!(Set.of(Arrays.copyOf(i.split(":"), i.split(":").length - 1)).stream().map(x -> x.substring(x.length() - 3)).collect(Collectors.toSet())).containsAll(mandatory_fields))
                continue;
            int byr;
            int iyr;
            int eyr;
            String hgt = i.split("hgt:")[1].split("\\s")[0];
            String hgt_unit = hgt.substring(hgt.length() - 2);
            int hgt_num;
            try {
                byr = Integer.parseInt(i.split("byr:")[1].split("\\s")[0]);
                iyr = Integer.parseInt(i.split("iyr:")[1].split("\\s")[0]);
                eyr = Integer.parseInt(i.split("eyr:")[1].split("\\s")[0]);
                hgt_num = Integer.parseInt(hgt.substring(0, hgt.length() - 2));
            } catch (NumberFormatException e) {
                continue;
            }
            String hcl = i.split("hcl:")[1].split("\\s")[0];
            String ecl = i.split("ecl:")[1].split("\\s")[0];
            String pid = i.split("pid:")[1].split("\\s")[0];


            if (!(byr >= 1920 && byr <= 2002)) continue;
            if (!(iyr >= 2010 && iyr <= 2020)) continue;
            if (!(eyr >= 2020 && eyr <= 2030)) continue;
            if (!(hcl.matches("#[a-f0-9]{6}"))) continue;
            if (!(eye_colors.contains(ecl))) continue;
            if (!(pid.matches("[0-9]{9}"))) continue;
            if (!((hgt_unit.equals("cm") && hgt_num >= 150 && hgt_num <= 193) ||
                    (hgt_unit.equals("in") && hgt_num >= 59 && hgt_num <= 76))) continue;
            count++;

        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println("---ex1---");
        System.out.println(ex1());
        System.out.println("---ex2---");
        System.out.println(ex2());
    }
}
