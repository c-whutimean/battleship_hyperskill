package battleship;

import java.util.Arrays;
import java.util.List;

public class Field {
    static final String[][] field = new String[10][10];
    static final String[][] battleField = new String[10][10];
    static final String[][] foggyField = new String[10][10];
    static final String letters = "ABCDEFGHIJ";
    static final String[] letterArr = letters.split("");
    static final List<String> letterList = Arrays.asList(letterArr);

    static void fillField() {
        for (int r = 0; r < field.length; r++) {
            char ch = (char) (65 + r);
            field[r][0] = Character.toString(ch);
            Arrays.fill(field[r], "~");
            Arrays.fill(foggyField[r], "~");
            Arrays.fill(battleField[r], "~");
        }
    }

    static void printField() {
        int i;
        System.out.print(" ");
        for (i = 1; i <= 10; i++) {
            System.out.print(" " + i);
        }
        System.out.println();
        for (i = 0; i < field.length; i++) {
            char c = (char) (65 + i);
            System.out.print(c);
            for (int j = 0; j < field[i].length; j++) {
                System.out.print(" " + field[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    static void printFoggyField() {
        int i;
        System.out.print(" ");
        for (i = 1; i <= 10; i++) {
            System.out.print(" " + i);
        }
        System.out.println();
        for (i = 0; i < foggyField.length; i++) {
            char c = (char) (65 + i);
            System.out.print(c);
            for (int j = 0; j < foggyField[i].length; j++) {
                System.out.print(" " + foggyField[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    static void printBattleField() {
        int i;
        System.out.print(" ");
        for (i = 1; i <= 10; i++) {
            System.out.print(" " + i);
        }
        System.out.println();
        for (i = 0; i < battleField.length; i++) {
            char c = (char) (65 + i);
            System.out.print(c);
            for (int j = 0; j < battleField[i].length; j++) {
                System.out.print(" " + battleField[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }
}
