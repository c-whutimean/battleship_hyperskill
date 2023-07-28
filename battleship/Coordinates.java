package battleship;

import java.util.*;

public class Coordinates {
    private int x;
    private int y;
    static List<Coordinates> coordList = new ArrayList<>();

    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Coordinates other = (Coordinates) obj;
        return x == other.x && y == other.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    public static void getCoord() {
        for (Coordinates coordinate : coordList) {
            System.out.println(coordinate.getX() + " " + coordinate.getY());
        }
        System.out.println();
    }
}
