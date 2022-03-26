package com.fizzbuzz;

class EscapeCode {
    static final String RESET = "\033[0m";

    enum Color {
        BLACK("\033[0;30m"),
        RED("\033[0;31m"),
        GREEN("\033[0;32m"),
        YELLOW("\033[0;33m"),
        BLUE("\033[0;34m"),
        PURPLE("\033[0;35m"),
        CYAN("\033[0;36m"),
        WHITE("\033[0;37m");

        final String code;

        Color(String code) {
            this.code = code;
        }
    }

    static String moveUp(int n) {
        return String.format("\033[%dA", n);
    }

    static String moveDown(int n) {
        return String.format("\033[%dB", n);
    }

    static String moveForward(int n) {
        return String.format("\033[%dC", n);
    }

    static String moveBack(int n) {
        return String.format("\033[%dD", n);
    }
}
