package day17;

import java.util.HashSet;
import java.util.Set;

public class Cube {
    Set<Cube> neighbours;
    final int x;
    final int y;
    final int z;

    Cube(int x, int y, int z) {
        this.x =x;
        this.y=y;
        this.z=z;
        this.neighbours = neighbours();
    }

    Cube(int x, int y, int z, boolean excludeNeighbours) {
        this.x = x;
        this.y = y;
        this.z = z;
        if(!excludeNeighbours) this.neighbours = neighbours();
    }
    public Set<Cube> neighbours() {
        Set<Cube> neighbours = new HashSet<>();
        for(int i = x - 1; i <= x + 1; i++) {
            for(int j = y - 1; j <= y + 1; j++) {
                for(int k = z - 1; k <= z + 1; k++) {
                    Cube cube = new Cube(i, j, k, true);
                    if (!cube.equals(this)) neighbours.add(cube);
                }
            }
        }
        return neighbours;
    }

    @Override
    public boolean equals(Object obj) {
        Cube cube = ((Cube) obj);
        return this.x == cube.x && this.y == cube.y && this.z == cube.z;
    }

    @Override
    public int hashCode() {
        return x + y * 1_000 + z * 1_000_000;
    }
}
