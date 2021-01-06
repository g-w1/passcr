// for file
import java.io.File;
import java.io.FileWriter;
// handle errors
import java.io.FileNotFoundException;
import java.io.IOException;
// for reading stuff
import java.util.Scanner;
// storing list of words
import java.util.ArrayList;
import java.util.HashMap;
import java.lang.Math;

public
class Bank {
    HashMap<Integer, ArrayList<String>> lenToAllWordsOfThatLen;
  public
    Bank() {
        lenToAllWordsOfThatLen = new HashMap<Integer, ArrayList<String>>();
    }
  public
    void load(String wordFile) throws FileNotFoundException, IOException {
        try {
            File dict = new File(wordFile);
            Scanner dictScanner = new Scanner(dict);
            ArrayList<String> words = new ArrayList<String>();
            while (dictScanner.hasNextLine()) {
                // read it line by line
                String word = dictScanner.nextLine();
                ArrayList<String> list =
                    lenToAllWordsOfThatLen.get(word.length());
                if (list == null) {
                    ArrayList<String> specialLenListOfWords =
                        new ArrayList<String>();
                    specialLenListOfWords.add(word);
                    lenToAllWordsOfThatLen.put(word.length(),
                                               specialLenListOfWords);
                } else {
                    list.add(word);
                }
            }

        } catch (IOException e) {
            System.out.println("Could not open file" + wordFile + e);
            System.exit(1);
        }
    }
  public
    String getWordOfLenth(int length) {
        ArrayList<String> tmp = lenToAllWordsOfThatLen.get(length);
        if (tmp == null)
            return null;
        return tmp.get(getRandom(tmp.size()));
    }

    static int getRandom(int max) { return (int)(Math.random() * (max)); }
}
