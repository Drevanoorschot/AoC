package day14;

import util.Util;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static java.lang.Long.toBinaryString;
import static util.Util.longToBytes;

public class Memory {
    static String[] input;

    static {
        try {
            input = Util.processStringInput("/day14/input.txt");
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }
    }

    Map<Long, Long> memory;
    char[] mask;

    Memory() {
        memory = new HashMap<>();
    }

    void setMask(String mask) {
        char[] mask_array = new char[36];
        for(int i = 0; i < mask.length(); i++) {
            mask_array[i] = mask.charAt(i);
        }
        this.mask = mask_array;
    }

    static char[] longToChar(long val) {
        char[] valChar = new char[36];
        char[] longChar = Long.toBinaryString(val).toCharArray();
        for(int i = 0; i < 36 - longChar.length; i++) {
            valChar[i] = '0';
        }
        System.arraycopy(longChar, 0, valChar, 36 - longChar.length, longChar.length);
        return valChar;
    }

    static long charToLong(char[] val) {
        return Long.parseLong(String.valueOf(val), 2);
    }

    char[] applyMask(char[] val) {
        char[] returnArr = new char[36];
        for(int i = 0; i < returnArr.length; i++) {
            returnArr[i] = mask[i] == 'X' ? val[i] : mask[i];
        }
        return returnArr;
    }

    static long ex1() {
        Memory memory = new Memory();
        for(String line : input) {
            if(line.startsWith("mask")) {
                memory.setMask(line.replace("mask = ", ""));
            } else {
                long addr = Long.parseLong(line.replace("mem[", "").split("]")[0]);
                long val = Long.parseLong(line.split(" = ")[1]);
                long entry = charToLong(memory.applyMask(longToChar(val)));
                memory.memory.put(addr, entry);
            }
        }
        return memory.memory.keySet().stream().map(x -> memory.memory.get(x)).reduce(Long::sum).get();
    }

    static long ex2() {
        Memory memory = new Memory();
        for(String line : input) {
            if(line.startsWith("mask")) {
                memory.setMask(line.replace("mask = ", ""));
            } else {
                char[] addr = longToChar(Long.parseLong(line.replace("mem[", "").split("]")[0]));
                long val = Long.parseLong(line.split(" = ")[1]);
                memory.memoryWrite(0, val, addr);
            }
        }
        return memory.memory.keySet().stream().map(x -> memory.memory.get(x)).reduce(Long::sum).get();
    }

    void memoryWrite(int pointer, long val, char[] addr) {
        if(pointer == addr.length) {
            memory.put(charToLong(addr), val);
            return;
        }
        switch(mask[pointer]) {
            case '0':
                memoryWrite(pointer + 1, val, addr);
                break;
            case '1':
                addr[pointer] = '1';
                memoryWrite(pointer + 1, val, addr);
                break;
            case 'X':
                char[] addr_alt = Arrays.copyOf(addr, 36);
                addr_alt[pointer] = '1';
                addr[pointer] = '0';
                memoryWrite(pointer + 1, val, addr);
                memoryWrite(pointer + 1, val, addr_alt);
                break;
        }
    }


    public static void main(String[] args) {
        System.out.println("---ex1---");
        System.out.println(ex1());
        System.out.println("---ex2---");
        System.out.println(ex2());
    }

}
