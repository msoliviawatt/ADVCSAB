import java.io.FileWriter;
import java.io.IOException;

public class RandomDataGenerator {
     //generates random character that is a consonant
    private static char randomConsonant() {
        String options = "bcdfghjklmnpqrstvwxyz";
        int index = (int)(Math.random() * options.length());
        return options.charAt(index);
    }

    //generates random vowel
    private static char randomVowel() {
        String vowels = "aeiou";
        int index = (int)(Math.random() * vowels.length());
        return vowels.charAt(index);
    }

    //generates a random data element
    public static DataItem randomElement() {
        int num = (int)(Math.random() * 10000); //random number from 0-9999
        String word = "";
        int dataLength = (int)(Math.random() * 6) + 3; //random length
        boolean consonantHelper = true; //keeps track of consonants for alternating with vowels

        for(int i = 0; i < dataLength; i++) {
            if(consonantHelper) {
                word += randomConsonant();
                consonantHelper = false;
            } else {
                word += randomVowel();
                consonantHelper = true;
            }
        }
        return new DataItem(num, word);
    }

    public static void writeToFile(String fileName) {
        try {
            FileWriter fileWriter = new FileWriter(fileName);
            for(int i = 0; i < 10000; i++) {
                DataItem item = randomElement();
                fileWriter.write(item.getNumber() + " " + item.getWord()+"\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void main(String[] arg) {
        writeToFile("hashData.txt");
    }
}
