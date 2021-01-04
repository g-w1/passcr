// for file
import java.io.File;
import java.io.FileWriter;
// handle errors
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.NumberFormatException;
// for reading stuff
import java.util.Scanner;
// storing list of words
import java.util.ArrayList;
// Optionals
import java.util.Optional;
// random
import java.lang.Math;

public
class Runner {
    String pattern;
    String[] arguments;
  public
    static void main(String[] args) throws FileNotFoundException, IOException {
        Runner run = new Runner(args);
        run.driver();
    }

  public
    Runner(String[] args) { arguments = args; }

    void driver() throws FileNotFoundException, IOException {
        if (arguments.length != 2) {
            System.out.println("Need 2 arguments");
            return;
        }
        Optional<ArrayList<int[]>> optParsed = parse();
        if (!optParsed.isPresent()) {
            System.out.println(
                "Could not parse the first argument into a password " +
                "string\nNote: the only characters allowed are 'x', '!', 'n'.");
            return;
        }
        int num = 0;
        try {
            num = Integer.parseInt(arguments[1]);
        } catch (NumberFormatException e) {
            System.out.println(
                "Could not parse the second argument into an integer");
            return;
        }

        ArrayList<int[]> parsed = optParsed.get();
        for (int i = 0; i < parsed.size(); i++) {
            System.out.println("" + (char)numToChar(parsed.get(i)[0]) + " " +
                               parsed.get(i)[1]);
        }
    }
    // this was to see how fast I could write a tokenizer
    // in history and science class
    Optional<ArrayList<int[]>> parse() {
        String inputStr = arguments[0];
        ArrayList<int[]> toReturn = new ArrayList<int[]>();
        int curNum = 0;
        char curChar = '!';
        for (int i = 0; i < inputStr.length(); i++) {
            char c = inputStr.charAt(i);
            if (i == 0) {
                curChar = c;
            }
            switch (c) {
            case 'x':
            case 'n':
            case '!': {
                if (curChar != c) {
                    int[] tmp = {charToNum(c), curNum};
                    toReturn.add(tmp);
                    curNum = 1;
                    curChar = c;
                } else {
                    // System.out.println(c);
                    // System.out.println(curNum);
                    curNum += 1;
                    curChar = c;
                }
                break;
            }
            default:
                System.out.println(c);
                return Optional.empty();
            }
        }
        return Optional.of(toReturn);
    }
    // these are so i dont have to deal with tuples
    static int charToNum(char c) {
        switch (c) {
        case 'x':
            return 1;
        case 'n':
            return 2;
        case '!':
            return 3;
        }
        return 0;
    }
    static int numToChar(int n) {
        switch (n) {
        case 1:
            return 'x';
        case 2:
            return 'n';
        case 3:
            return '!';
        case 0:
            return 'b';
        }
        return 'd';
    }
    // convenient random
    static int getRandom(int max) { return (int)(Math.random() * (max + 1)); }
}
