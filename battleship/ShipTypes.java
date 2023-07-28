package battleship;

import java.util.ArrayList;
import java.util.List;

public class ShipTypes {
    enum Ships {
        AIRCRAFT("Aircraft Carrier", 5),
        BATTLESHIP("Battleship", 4),
        SUBMARINE("Submarine", 3),
        CRUISER("Cruiser", 3),
        DESTROYER("Destroyer", 2);

        private final String name;
        private final int numCells;
        public List<Coordinates> positions;
        Ships(String name, int numCells) {
            this.name = name;
            this.numCells = numCells;
            this.positions = new ArrayList<>();
        }

        public String getShipInput() {
            return "Enter the coordinates of the " + name;
        }

        public String getNumCells() {
            return " (%d cells): ".formatted(numCells);
        }
        public String getShipName() {
            return name;
        }

        public int getShipSize() {
            return numCells;
        }

        public List<Coordinates> getPositions() {
            return positions;
        }
    }
}
