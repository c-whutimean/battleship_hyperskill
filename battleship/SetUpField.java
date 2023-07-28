package battleship;

import java.util.Scanner;

public class SetUpField {
    static Scanner sc = new Scanner(System.in);
    static boolean toContinue;
    static char row_1;
    static char row_2;
    static int num_1;
    static int num_2;
    static int size;
    static String char_1;
    static String char_2;
    static int letter_1;
    static int letter_2;

    static void setShipCoordinates(String input, ShipTypes.Ships ships) {
        String[] ans = input.split("\\s+");
        row_1 = ans[0].charAt(0);
        row_2 = ans[1].charAt(0);
        num_1 = Integer.parseInt(ans[0].substring(1));
        num_2 = Integer.parseInt(ans[1].substring(1));
        size = ships.getShipSize();
        char_1 = String.valueOf(row_1).toUpperCase();
        char_2 = String.valueOf(row_2).toUpperCase();
        letter_1 = Field.letterList.indexOf(char_1);
        letter_2 = Field.letterList.indexOf(char_2);
    }

    static void getShipCoordinates(ShipTypes.Ships ships) {
        toContinue = true;
        while (true) {
            System.out.println(ships.getShipInput() + ships.getNumCells());
            String input = sc.nextLine().toUpperCase().trim();
            setShipCoordinates(input, ships);
            toContinue = checkShipCoordinates(num_1, num_2, size, ships);
            if (!toContinue) {
                break;
            }
        }
        System.out.println();
    }


    static boolean checkLength(int col_1, int col_2, int size) {
        int length = Math.abs(col_1 - col_2) + 1;
        if (length == size) {
            toContinue = false;
        } else {
            toContinue = true;
            String mssg = "Error! Wrong length! Try again!";
            System.out.println(mssg + "\n");
        }
        return toContinue;
    }

    static boolean checkShipCoordinates(int num_1, int num_2, int size, ShipTypes.Ships ships) {
        toContinue = false;

        if (letter_1 != letter_2 && num_1 != num_2) {
            System.out.println("Error! Wrong ship location! Try again: ");
            System.out.println();
            toContinue = true;
        } else {
            if (letter_1 != letter_2) {   // vertical placement
                toContinue = checkLength(letter_1, letter_2, size);
                if (!toContinue) {
                    toContinue = Checker.checkColumnLength(letter_1, letter_2, num_1, ships);
                }
            }
            if (letter_1 == letter_2 && num_1 != num_2) { //horizontal placement
                num_1 -= 1;
                num_2 -= 1;
                toContinue = checkLength(num_1, num_2, size);
                if (!toContinue) {
                    toContinue = Checker.checkRowLength(num_1, num_2, letter_1, ships);
                }
            }
        }
        return toContinue;
    }
}
