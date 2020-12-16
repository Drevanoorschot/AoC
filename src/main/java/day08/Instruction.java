package day08;

import util.Util;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;

import static day08.InstructionType.jmp;
import static day08.InstructionType.nop;

public class Instruction {

    public static String[] input;

    static {
        try {
            input = Util.processStringInput("/day08/input.txt", "\n");
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }
    }

    InstructionType type;
    int arg;
    boolean visited;


    public Instruction(InstructionType type, int arg) {
        this.type = type;
        this.arg = arg;
        this.visited = false;
    }

    public Instruction(String instruction) {
        String[] instructionArray = instruction.split(" ");
        this.type = InstructionType.valueOf(instructionArray[0]);
        this.arg = Integer.parseInt(instructionArray[1]);
        this.visited = false;
    }

    public static ExecutionResult executeInput(Instruction[] instructions) throws Exception {
        int accumulator = 0;
        int programCounter = 0;
        try {
            while (!instructions[programCounter].visited) {
                instructions[programCounter].visited = true;
                switch (instructions[programCounter].type) {
                    case nop:
                        programCounter++;
                        break;
                    case acc:
                        accumulator += instructions[programCounter].arg;
                        programCounter++;
                        break;
                    case jmp:
                        programCounter += instructions[programCounter].arg;
                        break;
                    default:
                        throw new Exception("Unknown command type");
                }
            }
        } catch (IndexOutOfBoundsException e) {
            return new ExecutionResult(accumulator, true);
        }
        return new ExecutionResult(accumulator, false);
    }

    public static int ex2() throws Exception {
        for (int i = 0; i < input.length; i++) {
            Instruction[] instructions = Arrays.stream(input).map(Instruction::new).toArray(Instruction[]::new);
            if (instructions[i].type != InstructionType.acc) {
                InstructionType newType = instructions[i].type == nop ? jmp : nop;
                instructions[i] = new Instruction(newType, instructions[i].arg);
                ExecutionResult result = executeInput(instructions);
                if (result.terminated) {
                    return result.accumulator;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) throws Exception {
        Instruction[] instructions = Arrays.stream(input).map(Instruction::new).toArray(Instruction[]::new);
        System.out.println("---ex1---");
        ExecutionResult ex1 = executeInput(instructions);
        System.out.println(String.format("%d terminated: %b", ex1.accumulator, ex1.terminated));
        System.out.println("---ex2---");
        System.out.println(ex2());
    }

    public static class ExecutionResult {
        int accumulator;
        boolean terminated;

        ExecutionResult(int accumulator, boolean terminated) {
            this.accumulator = accumulator;
            this.terminated = terminated;
        }
    }
}
