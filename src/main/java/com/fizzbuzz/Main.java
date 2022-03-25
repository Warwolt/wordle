package com.fizzbuzz;

public class Main {
    class EscapeCode {
        static final String RESET = "\033[0m";
        static final String COLOR_BLACK = "\033[0;30m";
        static final String COLOR_RED = "\033[0;31m";
        static final String COLOR_GREEN = "\033[0;32m";
        static final String COLOR_YELLOW = "\033[0;33m";
        static final String COLOR_BLUE = "\033[0;34m";
        static final String COLOR_PURPLE = "\033[0;35m";
        static final String COLOR_CYAN = "\033[0;36m";
        static final String COLOR_WHITE = "\033[0;37m";
    }

    public static void main(String[] args) {
        System.out.println();
        System.out.println("      WORDLE    ");
        printRow("Flame");
        printRow("Floor");
        printRow("Sword");
        printRow("Scope");
        printRow("Beans");
        System.out.println("Q W E R T Y U I O P");
        System.out.println(" A S D F G H J K L");
        System.out.println("  Z X C V B N M");
    }

    static void printRow(String word) {
        char[] rowChars = new char[5];
        int i = 0;

        for (; i < word.length(); i++) {
            rowChars[i] = word.charAt(i);
        }

        for (; i < 5; i++) {
            rowChars[i] = ' ';
        }

        System.out.println(" ┌─┐┌─┐┌─┐┌─┐┌─┐");
        System.out.println(String.format(
                " │%c││%c││%c││%c││%c│",
                rowChars[0],
                rowChars[1],
                rowChars[2],
                rowChars[3],
                rowChars[4]));
        System.out.println(" └─┘└─┘└─┘└─┘└─┘");
    }
}
