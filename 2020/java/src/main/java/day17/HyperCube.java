package day17;

import java.util.HashSet;
import java.util.Set;

public class HyperCube {
    Set<HyperCube> neighbours;
    final int x;
    final int y;
    final int z;
    final int w;

    HyperCube(int x, int y, int z, int w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
        this.neighbours = neighbours();
    }

    HyperCube(int x, int y, int z, int w, boolean excludeNeighbours) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
        if (!excludeNeighbours) this.neighbours = neighbours();
    }

    public Set<HyperCube> neighbours() {
        Set<HyperCube> neighbours = new HashSet<>();
        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                for (int k = z - 1; k <= z + 1; k++) {
                    for (int l = w - 1; l <= w + 1; l++) {
                        HyperCube cube = new HyperCube(i, j, k, l, true);
                        if (!cube.equals(this)) neighbours.add(cube);
                    }
                }
            }
        }
        return neighbours;
    }

    @Override
    public boolean equals(Object obj) {
        HyperCube cube = ((HyperCube) obj);
        return this.x == cube.x && this.y == cube.y && this.z == cube.z && this.w == cube.w;
    }

    @Override
    public int hashCode() {
        return x + y * 100 + z * 10_000 + w * 1_000_000;
    }
}
