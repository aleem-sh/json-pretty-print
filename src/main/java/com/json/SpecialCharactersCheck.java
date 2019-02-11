package com.json;

public class SpecialCharactersCheck {
    public static boolean isOpeningCurlyBrace(char value) {
        return (value == '{');
    }

    public static boolean isClosingCurlyBrace(char value) {
        return (value == '}');
    }

    public static boolean isComma(char value) {
        return (value == ',');
    }

    public static boolean isDoubleQuote(char value) {
        return (value == '"');
    }

    public static boolean isOpeningSquareBracket(char value) {
        return (value == '[');
    }

    public static boolean isClosingSquareBracket(char value) {
        return (value == ']');
    }
}
