package battleship;

import java.util.*;

public class Main {
    static List<Integer[][]> shipPlacement = new ArrayList<>();
    static List<List<Integer[][]>> allShips = new ArrayList<List<Integer[][]>>();
    static List<Coordinates> foggyList = new ArrayList<>();
    static List<Coordinates> copyList = new ArrayList<>();
    static int hits = 0;
    static int sank = 0;
    static int hitting = 0;

    public static void sortCoordinates(List<Coordinates> coordinatesList) {
        Collections.sort(coordinatesList, Comparator.comparingInt(Coordinates::getX)
                .thenComparingInt(Coordinates::getY));
    }

    static boolean checkSank() {
        sortCoordinates(foggyList);

        for (ShipTypes.Ships ships : ShipTypes.Ships.values()) {
            if (hits >= ships.getShipSize()) {
                List<Coordinates> positions = ships.getPositions();

                for (int i = 0; i < positions.size(); i++) {
                    if (positions.get(i).equals(foggyList.get(i))) {
                        hitting++;
                        if (hitting == ships.getShipSize()) {
                            //System.out.println("i = " + i);
                            for (int j = hitting; j < foggyList.size(); j++) {
                                copyList.add(foggyList.get(j));
                            }
                            foggyList.clear();
                            foggyList.addAll(copyList);
                            copyList.clear();
                            hits -= ships.getShipSize();
                            hitting -= ships.getShipSize();
                            sank++;
                            return true;
                        }
                    }
                }
            }
        }
        return false; // No matching ships found
    }


    public static void main(String[] args) {
        Field.fillField();
        Field.printField();
        Scanner sc = new Scanner(System.in);

        for (ShipTypes.Ships ships : ShipTypes.Ships.values()) {
            SetUpField.getShipCoordinates(ships);
        }

        System.out.println("The game starts!\n");
        Field.printFoggyField();
        System.out.println("Take a shot!");

        int shot = 0;

        while (true) {
            String input = sc.nextLine().toUpperCase().trim();
            String[] move = input.split("");
            String letter = move[0];
            StringBuilder number = new StringBuilder();
            for (int i = 1; i < move.length; i++) {
                number.append(move[i]);
            }
            int num = Integer.parseInt(number.toString()) - 1;

            if (!Field.letters.contains(letter) || num < 0 || num > 9) {
                System.out.println("Error! You entered the wrong coordinates! Try again: ");
            } else {
                int row = Field.letterList.indexOf(letter);
                if (Field.field[row][num].equals("O")) {
                    Field.foggyField[row][num] = "X";
                    Coordinates coord = new Coordinates(row, num);
                    if (!foggyList.contains(coord)) {
                        foggyList.add(coord);
                        shot++;
                        if (shot == 17) {
                            Field.printFoggyField();
                            System.out.println("You sank the last ship. You won. Congratulations!");
                            break;
                        }
                    }
                    Coordinates.getCoord();
                    boolean valid = checkSank();    //hits
                    if (valid) {
                        if (sank == 5) {
                            Field.printFoggyField();
                            System.out.println("You sank the last ship. You won. Congratulations!");
                            break;
                        } else {
                            Field.printFoggyField();
                            System.out.println("\nYou sank a ship! Specify a new target:");
                        }
                    } else {
                        Field.printFoggyField();
                        System.out.println("\nYou hit a ship! Try again:");
                    }
                } else {
                    Field.foggyField[row][num] = "M";
                    Field.printFoggyField();
                    System.out.println("You missed! Try again:");
                }
            }
        }
    }
}
