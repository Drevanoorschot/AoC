package day12;

import util.Util;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;

public class Ship {
    static String[] input;

    static {
        try {
            input = Util.processStringInput("/day12/input.txt");
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }
    }

    int x;
    int y;

    int facing_degree;

    public Ship() {
        this.x = 0;
        this.y = 0;
        this.facing_degree = 90;
    }

    public void executeCommand(String command) throws Exception {
        char inst = command.charAt(0);
        int val = Integer.parseInt(command.substring(1));
        switch (inst) {
            case 'N':
                this.y += val;
                break;
            case 'S':
                this.y -= val;
                break;
            case 'E':
                this.x += val;
                break;
            case 'W':
                this.x -= val;
                break;
            case 'L':
                this.facing_degree = (((this.facing_degree - val) % 360) + 360) % 360;
                break;
            case 'R':
                this.facing_degree = (this.facing_degree + val) % 360;
                break;
            case 'F':
                switch (this.facing_degree) {
                    case (0):
                        this.y += val;
                        break;
                    case (90):
                        this.x += val;
                        break;
                    case (180):
                        this.y -= val;
                        break;
                    case (270):
                        this.x -= val;
                        break;
                    default:
                        throw new Exception(String.format("invalid number of degree %d", this.facing_degree));
                }
                break;
            default:
                throw new Exception(String.format("Unknown command %s", command));
        }
    }

    public int manhattan() {
        return Math.abs(x) + Math.abs(y);
    }

    public void displace(int x, int y) {
        this.x += x;
        this.y += y;
    }

    public static void main(String[] args) {
        System.out.println("---ex1---");
        Ship ship = new Ship();
        Arrays.stream(input).forEach(cmd -> {
            try {
                ship.executeCommand(cmd);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        System.out.println(ship.manhattan());

        System.out.println("---ex2---");
        WayPoint wp = new WayPoint(new Ship());
        Arrays.stream(input).forEach(cmd -> {
            try {
                wp.executeCommand(cmd);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        System.out.println(wp.ship.manhattan());
    }

}
