// for file
import java.io.File;
// handle errors
import java.io.FileNotFoundException;
// for reading stuff
import java.util.Scanner;

public
class SetterUpper {
public
  static void main(String[] args) {
    try {
      File dict = new File("dict");
      Scanner dictScanner = new Scanner(dict);
      while (dictScanner.hasNextLine()) {
        String line = dictScanner.nextLine();
        System.out.println(line);
      }
    } catch (FileNotFoundException e) {
      System.out.println("bruh");
      e.printStackTrace();
    }
  }
}
