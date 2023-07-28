package battleship;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Checker {
    static private boolean touching;
    static List<List<Coordinates>> fieldList = new ArrayList<>();
    static int[][] fieldArr = new int[5][];

    static boolean checkUp(int letter, int num) {
        touching = false;
        if (letter == 0) {
            return touching;
        } else {
            if (Field.field[letter - 1][num].equals("O")) {
                error();
                touching = true;
            }
        }
        return touching;
    }

    static boolean checkDown(int letter, int num) {
        touching = false;
        if (letter == 9) {
            return touching;
        } else {
            if (Field.field[letter + 1][num].equals("O")) {
                error();
                touching = true;
            }
        }
        return touching;
    }

    static boolean checkRight(int letter, int num) {
        touching = false;
        if (num == 9) {
            return touching;
        } else {
            if (Field.field[letter][num + 1].equals("O")) {
                touching = true;
            }
        }
        return touching;
    }

    static boolean checkLeft(int letter, int num) {
        touching = false;
        if (num == 0) {
            return touching;
        } else {
            if (Field.field[letter][num - 1].equals("O")) {
                error();
                touching = true;
            }
        }
        return touching;
    }

    static void error() {
        String mssg1 = "Error! You placed it too close to another one.";
        String mssg2 = " Try again: ";
        System.out.println(mssg1 + mssg2 + '\n');
    }

    static boolean checkRowLength(int length1, int length2, int index, ShipTypes.Ships ships) {
        boolean touchingUp;
        boolean touchingDown;
        touching = true;

        int start = Math.min(length1, length2);
        int end = Math.max(length1, length2);
        boolean oLeft = checkLeft(index, start);
        boolean oRight = checkRight(index, start);

        if (!oRight && !oLeft) {
            for (int c = start; c <= end; c++) {
                touchingUp = checkUp(index, c);
                touchingDown = checkDown(index, c);
                int length = 0;
                if (!touchingUp && !touchingDown) {
                    Field.field[index][c] = "O";
                    Field.battleField[index][c] = "X";
                    Coordinates coordinate = new Coordinates(index, c);
                    Coordinates.coordList.add(coordinate);
                    length++;

                } else {
                    error();
                    return touching;
                }
            }
        } else {
            return touching;
        }
        System.out.println();
        Field.printField();
        ships.positions.addAll(Coordinates.coordList);
        Coordinates.coordList.clear();
        touching = false;
        return touching;
    }

    static boolean checkColumnLength(int row1, int row2, int col, ShipTypes.Ships ships) {
        boolean touchingRight;
        boolean touchingLeft;
        touching = true;

        int start = Math.min(row1, row2);
        int end = Math.max(row1, row2);
        boolean oUP = checkUp(start, col);
        boolean oDown = checkDown(end, col);

        if (!oUP && !oDown) {
            for (int i = start; i <= end; i++) {
                touchingRight = checkRight(i, col - 1);
                touchingLeft = checkLeft(i, col - 1);
                if (!touchingRight && !touchingLeft) {
                    Field.field[i][col - 1] = "O";
                    Field.battleField[i][col - 1] = "X";
                    Coordinates coordinate = new Coordinates(i, col - 1);
                    Coordinates.coordList.add(coordinate);
                } else {
                    error();
                }
            }
        } else {
            return touching;
        }
        System.out.println();
        Field.printField();
        ships.positions.addAll(Coordinates.coordList);
        Coordinates.coordList.clear();
        //touching = false;   // false means successful
        return false;
    }
}
