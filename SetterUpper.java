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

public
class SetterUpper {
  public
    static void main(String[] args) throws FileNotFoundException, IOException {
        int size = 0;
        boolean init = false;
        File dict = new File("dict");
        Scanner dictScanner = new Scanner(dict);
        ArrayList<String> words = new ArrayList<String>();
        while (dictScanner.hasNextLine()) {
            // read it line by line
            String word = dictScanner.nextLine();
            int tmpsize = word.length();
            if (init) {
                size = tmpsize;
                init = false;
            }
            if (tmpsize != size) {
                String nameToWrite =
                    String.format("shortened/%d.list", tmpsize);
                size = tmpsize;
                File fileToWrite = new File(nameToWrite);
                fileToWrite.createNewFile();

                FileWriter writer = new FileWriter(nameToWrite);
                writer.write(words.size() + "\n");
                for (int i = 0; i < words.size(); i++) {
                    writer.write(words.get(i) + "\n");
                }
                writer.close();
                // System.out.println("wrote: " + nameToWrite);
                words.clear();
            } else {
                words.add(word);
            }
        }
    }
}
