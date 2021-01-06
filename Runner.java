// for file
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Paths;
import java.nio.file.Files;
// handle errors
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.NumberFormatException;
// for reading stuff
import java.util.Scanner;
import java.util.stream.Stream;
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
    Bank bank;
  public
    static void main(String[] args) throws FileNotFoundException, IOException {
        Runner run = new Runner(args);
        run.driver();
    }

  public
    Runner(String[] args) {
        arguments = args;
        bank = new Bank();
    }

    void driver() throws FileNotFoundException, IOException {

        String help =
            "Could not parse the first argument into a password " +
            "string\nNote: the only characters allowed are 'x', 'b', 'n'." +
            "\nx for a letter, b for a special char (!@#$%) n for a " +
            "number\nThe second argument is how many passwords to generate\n" +
            "Third argument is dictionary file\n ";
        if (arguments.length != 3) {
            System.out.println("Need 3 arguments");
            System.out.println(help);
            return;
        }
        bank.load(arguments[2]);
        Optional<ArrayList<int[]>> optParsed = parse();
        if (!optParsed.isPresent()) {
            System.out.println(help);
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

        for (int j = 0; j < num; j++) {
            ArrayList<int[]> parsed = optParsed.get();
            String password = new String();

            for (int i = 0; i < parsed.size(); i++) {
                char type = (char)numToChar(parsed.get(i)[0]);
                int nu = parsed.get(i)[1];
                password += generate(type, nu);
            }

            System.out.println(password);
        }
    }
    String generate(char type, int num) throws IOException {
        String out = new String();
        switch (type) {
        case 'b':
            for (int i = 0; i < num; i++)
                out += genBangHash();
            break;
        case 'n':
            for (int i = 0; i < num; i++)
                out += getRandom(9);
            break;
        case 'x':
            out += readLineFromFile(num);
            break;
        }
        return out;
    }
    static char genBangHash() {
        switch (getRandom(10) + 1) {
        case 1:
            return '#';
        case 2:
            return '!';
        case 3:
            return '@';
        case 4:
            return '$';
        case 5:
            return '%';
        case 6:
            return '^';
        case 7:
            return '&';
        case 8:
            return '*';
        case 9:
            return '(';
        case 10:
            return ')';
        }
        return 0;
    }
    // this was to see how fast I could write a tokenizer
    // in history and science class
    Optional<ArrayList<int[]>> parse() {
        String inputStr = arguments[0];
        ArrayList<int[]> toReturn = new ArrayList<int[]>();
        int curNum = 0;
        char curChar = 'b';
        for (int i = 0; i < inputStr.length(); i++) {
            char c = inputStr.charAt(i);
            if (i == 0) {
                curChar = c;
            }
            switch (c) {
            case 'x':
            case 'n':
            case 'b': {
                if (curChar != c) {
                    int[] tmp = {charToNum(curChar), curNum};
                    toReturn.add(tmp);
                    curNum = 1;
                    curChar = c;
                } else {
                    curNum += 1;
                    curChar = c;
                }
                break;
            }
            default:
                return Optional.empty();
            }
        }

        int[] tmp = {charToNum(curChar), curNum};
        toReturn.add(tmp);

        return Optional.of(toReturn);
    }
    // these are so i dont have to deal with tuples
    static int charToNum(char c) {
        switch (c) {
        case 'x':
            return 1;
        case 'n':
            return 2;
        case 'b':
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
            return 'b';
        case 0:
            return 'b';
        }
        return 'd';
    }
    // convenient random
    static int getRandom(int max) { return (int)(Math.random() * (max + 1)); }

    String readLineFromFile(int fNum) throws IOException {
        String got = bank.getWordOfLenth(fNum);
        if (got == null) {
            System.out.println("Could not find word with length " + fNum);
            System.exit(1);
        }
        return got;
    }
}
