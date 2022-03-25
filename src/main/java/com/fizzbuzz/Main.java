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
        System.out.println("Hej världen!");
    }
}
