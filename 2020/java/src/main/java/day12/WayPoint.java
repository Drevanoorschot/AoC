package day12;

public class WayPoint {
    int x;
    int y;

    Ship ship;

    WayPoint(Ship ship) {
        x = 10;
        y = 1;
        this.ship = ship;
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
                rotate(360 - val);
                break;
            case 'R':
                rotate(val);
                break;
            case 'F':
                ship.displace(x * val, y * val);
                break;
            default:
                throw new Exception(String.format("Unknown command %s", command));
        }
    }

    public void rotate(int degrees) throws Exception {
        switch (degrees) {
            case(0):
                break;
            case(90):
                swap();
                y = -y;
                break;
            case(180):
                x = -x;
                y = -y;
                break;
            case(270):
                swap();
                x = -x;
                break;
            default:
                throw new Exception(String.format("invalid number of degree %d", degrees));
        }
    }

    private void swap() {
        x = x + y;
        y = x - y;
        x = x - y;
    }
}
