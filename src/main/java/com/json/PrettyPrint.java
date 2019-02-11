package com.json;

import java.nio.file.Files;
import java.nio.file.Paths;

public class PrettyPrint {

    public static void main(String args[]) throws Exception {

        //String path = "C:\\MyWorkspace\\JsonPrettyPrint\\src\\main\\resources\\simple.json";
        String path = "C:\\MyWorkspace\\JsonPrettyPrint\\src\\main\\resources\\NestedJson.json";
        //String path = "C:\\MyWorkspace\\JsonPrettyPrint\\src\\main\\resources\\NestedJsonWithArray.json";
        String json = Files.readAllLines(Paths.get(path)).get(0);

        PrettyPrint pp = new PrettyPrint();
        System.out.println( pp.fromJson(json));
    }

    public String fromJson(String json) {
        if(json != null && !json.trim().isEmpty()) {
            System.out.println("json - "+json);

            char[] jsonChars = json.toCharArray();
            StringBuffer formattedJson = new StringBuffer();
            char previousChar = 0;
            int spaces = 0;
            int doubleQuotesCount = 0;
            for(int i=0; i<jsonChars.length; i++) {
                char currentChar = jsonChars[i];
                if(currentChar == ' ') continue;
                if(SpecialCharactersCheck.isOpeningCurlyBrace(previousChar)) spaces = spaces +3;
                if(SpecialCharactersCheck.isClosingCurlyBrace(currentChar)) spaces = spaces -3;
                if(SpecialCharactersCheck.isOpeningSquareBracket(previousChar)) spaces = spaces + 5;
                if(SpecialCharactersCheck.isClosingSquareBracket(currentChar)) spaces = spaces - 5;
                if(SpecialCharactersCheck.isDoubleQuote(currentChar)) doubleQuotesCount++;

                if(canAddLineSeperator(previousChar, currentChar, doubleQuotesCount)) {
                    formattedJson.append(System.lineSeparator()).append(getSpaces(spaces));
                }
                formattedJson.append(currentChar);
                previousChar = currentChar;
            }
            return formattedJson.toString();
        }

        return null;
    }

    private boolean canAddLineSeperator(char previousChar, char currentChar, int doubleQuoteCount) {
        return SpecialCharactersCheck.isOpeningCurlyBrace(previousChar)
                || SpecialCharactersCheck.isClosingCurlyBrace(currentChar)
                || (SpecialCharactersCheck.isDoubleQuote(currentChar) && SpecialCharactersCheck.isComma(previousChar))
                || (SpecialCharactersCheck.isClosingCurlyBrace(currentChar) && SpecialCharactersCheck.isComma(previousChar))
                || (SpecialCharactersCheck.isClosingSquareBracket(currentChar) && SpecialCharactersCheck.isComma(previousChar));
    }

    private String getSpaces(int count){
        StringBuffer sc = new StringBuffer();
        for(int i=0; i<count; i++) {
            sc.append(' ');
        }
        return sc.toString();
    }




}
