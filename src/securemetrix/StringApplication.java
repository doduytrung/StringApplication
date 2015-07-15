/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package securemetrix;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class will read text file content, replace some characters 'a' and then
 * display to on the console file.
 *
 * @author test
 */
public class StringApplication {

    private final static char SEARCH_CHARACTER = 'a';
    private final static char FIRST_SIGN_CHARACTER = '#';
    private final static char SECOND_SIGN_CHARACTER = '$';
    Pattern pattern;
    Matcher matcher;

    public StringApplication() {
        String regrex = "[" + SEARCH_CHARACTER + "]+";
        pattern = Pattern.compile(regrex);
    }

    /**
     * This method reads the text file content line by line, replace some
     * characters 'a' and then display to on the console file.
     *
     * @param inputFilename
     */
    public void replaceFile(String inputFilename) {
        try {
            FileReader fr = new FileReader(inputFilename);
            BufferedReader br = new BufferedReader(fr);
            String line;

            while ((line = br.readLine()) != null) {
                if (line.isEmpty()) {
                    System.out.println();
                } else {
                    line = replaceLine(line);
                    System.out.println(line);
                }
            }
        } catch (IOException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    /**
     * This method replaces some characters 'a' with RULE 1, RULE 2
     *
     * @param line
     * @return
     */
    public String replaceLine(String line) {
        int length = line.length();
        StringBuilder outputString = new StringBuilder();
        matcher = pattern.matcher(line);
        int start = 0;
        int end = 0;
        int range = 0;

        while (matcher.find()) {
            start = matcher.start();
            if (start > end) {
                //append characters which do not match
                outputString.append(line.substring(end, start));
            }
            end = matcher.end();
            // calculate the total of searched characters and then apply rules to replace
            range = end - start;
            if (range == 1) {
                //apply RULE 1: single-character
                outputString.append(FIRST_SIGN_CHARACTER);
            } else {
                //apply RULE 2: multi-character
                outputString.append(SEARCH_CHARACTER);
                for (int i = 0; i < range - 1; i++) {
                    outputString.append(SECOND_SIGN_CHARACTER);
                }
            }
        }
        if (end < length) {
            outputString.append(line.substring(end, length));
        }
        return outputString.toString();
    }

    /**
     * The entry method of the application Must pass at least an argument
     *
     * @param args
     */
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("You must give me a filename!");
            return;
        }
        String filename = args[0];
        StringApplication app = new StringApplication();
        app.replaceFile(filename);
    }

}
