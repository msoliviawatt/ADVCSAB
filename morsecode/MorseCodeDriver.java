   import java.io.*;
   import java.util.*;

   public class MorseCodeDriver
   {
      public static void main(String[]arg)throws IOException
      {
         Scanner input = new Scanner(System.in); //allows for the collection of user input
         
         HashMap<String, String> encodeMap = new HashMap<String, String>(); //initializes hash map for encoding
         HashMap<String, String> decodeMap = new HashMap<String, String>(); //initializes hash map for decoding

         encodeMap = MorseCode.map(MorseCode.english, MorseCode.code, encodeMap); //sets keys and values for encoding
         decodeMap =  MorseCode.map(MorseCode.code, MorseCode.english, decodeMap); //sets keys and values for decoding

         System.out.println("Would you like to encode or decode");
         String encordec = input.nextLine().toLowerCase(); //stores the user's response (encode or decode)

         //makes sure the response is usable
         while(!(encordec.equals("encode") || encordec.equals("e") || encordec.equals("decode") || encordec.equals("d"))) {
            System.out.println("Please enter a valid response: 'e' or 'd'");
            encordec = input.nextLine().toLowerCase();
         }

         boolean createFile = false; //used to differentiate between user input and existing file translation

         //encode
         if(encordec.equals("encode") || encordec.equals("e")) { //if the user chooses to encode
            System.out.println("Would you like to encode from a file? (y/n)"); {
               String ans = input.nextLine();
               while(!(ans.equalsIgnoreCase("y") || ans.equalsIgnoreCase("n"))) { //checks for valid user input
                  System.out.println("Please enter 'y' or 'n'");
                  ans = input.nextLine();
               }
               if(ans.equalsIgnoreCase("n")) { //if the user wants to encode inputted text rather than text from a file
                  createFile = true; //means a file needs to be created that holds the user's input
               }

            }
            if(!createFile) { //if the user chooses to encode from an existing file
               String fileName = "";
               boolean helper = true; //stops the loop if there is not a FileNotFoundException
               do {
                  try {
                     System.out.println("Enter the file name");
                     fileName = input.nextLine();
                     encode(fileName, decodeMap);
                     helper = false; //stops the loop when the user enters a valid file name
                  } catch (FileNotFoundException e) {
                     System.out.println("Invalid file name");
                  }
               } while (helper);
               if(sendToFile()) { //if the user chooses to send their encoded text to a file
                  writeToFile(encode(fileName, encodeMap));;
                  System.out.println("your encoded text has been stored in the file 'user.txt'");
               }
               else {
                  System.out.println("your text encoded: " + encode(fileName, encodeMap));
               }
            }

            else if(createFile) { //if the user decides they want to encode direct input
               System.out.println("Enter what you wish to encode");
               String x = input.nextLine();
               String ans = "";
               x.toUpperCase(); //changes the user's answer to uppercase because the hashmap keys are uppercase
               for(int i = 0; i < x.length() - 1; i++) { //loops through the characters of the user's input
                  if(x.substring(i, i + 1).equals(" ")) {
                     ans += "/"; //replaces spaces with slashes
                  }
                  else {
                     ans += encodeMap.get(x.substring(i, i + 1).toUpperCase()); //switches out text key for morse code value
                  }
                  ans += " "; //adds a space between characters
               }
               ans += encodeMap.get(x.substring(x.length() -1).toUpperCase()); //ensures that the last character does not get left out
               if(sendToFile()) { //if the user wants to send their encoded text to a file
                  writeToFile(ans);; //writes to the user file
                  System.out.println("your encoded text has been stored in the file 'user.txt'");
               }
               else {
                  System.out.println("your text encoded: " + ans);
               }
            }
         }

         //decode
         else if(encordec.equals("decode") || encordec.equals("d")) { //if the user chooses to decode
            System.out.println("Would you like to decode from a file? (y/n)"); {
               String ans = input.nextLine();
               while(!(ans.equalsIgnoreCase("y") || ans.equalsIgnoreCase("n"))) { //checks for valid user input
                  System.out.println("Please enter 'y' or 'n'");
                  ans = input.nextLine();
               }
               if(ans.equalsIgnoreCase("n")) {
                  createFile = true; //means we need to create a new file for user input
               }
            }
            if(!createFile) { //if we do not need to create a new file (it already exists)
               String fileName = "";
               boolean helper = true; //stops the loop if there is not a FileNotFoundException
               do {
                  try {
                     System.out.println("Enter the file name");
                     fileName = input.nextLine();
                     decode(fileName, encodeMap);
                     helper = false; //stops the loop when the user enters a valid file name
                  } catch (FileNotFoundException e) {
                     System.out.println("Invalid file name");
                  }
               } while (helper);
               
               if(sendToFile()) { //if the user wants to send the decrypted message to a file, rather than to the screen
                  String line = "";
                  Scanner reader = new Scanner(new FileReader(fileName)); //allows for iteration through file lines
                  System.out.print("your text decoded: ");
                  while(reader.hasNextLine()) {
                     line = reader.nextLine();
                     writeToFile(decode(line, decodeMap)); //adds each decoded line to the user file
                  }

                  System.out.println("your decoded text has been stored in the file 'user.txt'");
               }
               else { //if the user wants to see the decoded message printed out to them, not sent to a file
                  String line = "";
                  Scanner reader = new Scanner(new FileReader(fileName));
                  System.out.print("your text decoded: ");
                  while(reader.hasNextLine()) {
                     line = reader.nextLine();
                     System.out.print(decode(line, decodeMap));
                  }
               }
            }

            else if(createFile) { //if the user doesn't want to decode from a file
               System.out.println("Enter what you wish to decode");
               String ans = input.nextLine();
               ans = decode(ans, decodeMap); //decodes the user's line of text
               if(sendToFile()) {
                  writeToFile(ans);;
                  System.out.println("your decoded text has been stored in the file 'user.txt'");
               }
               else {
                  System.out.println("your text decoded: " + ans);
               }
            }
         }

         input.close();
      }

      //pre: fileName is a file that exists and has lines of text
      //post: translates plain text into morse code
      public static String encode(String fileName, HashMap<String, String> encodeMap) throws IOException {
         String ans = "";
         String x = ""; //keeps track of the current line as the file reader goes through
         Scanner input = new Scanner(new FileReader(fileName));
         while(input.hasNextLine()) {
            x = input.nextLine().toUpperCase(); //changes line characters to uppercase, as those match the encode keys
            for(int i = 0; i < x.length() - 1; i++) { //iterates through the characters of line x
               if(x.substring(i, i + 1).equals(" ")) { //replaces spaces with a slash
                  ans += "/";
               }
               else {
                  ans += encodeMap.get(x.substring(i, i + 1).toUpperCase()); //adds the character's morse code value to the final answer
               }
               ans += " ";
            }
         }
         ans += encodeMap.get(x.substring(x.length() -1).toUpperCase()); //translates the last character
         return ans;
      }

      //pre: line is a string that is not null
      //post: translates morse code into plain text
      public static String decode(String line, HashMap<String, String> decodeMap) throws IOException {
         String ans = "";

         String[] array = line.trim().split(" "); //separates morse code by plain text character groupings
         for(String x : array) {
            if(x.equals("/")) {
               ans += " "; //replaces slashes with spaces
            }
            else {
               ans += decodeMap.get(x); //adds the morse code's equivalent text character to the anwer
            }
         }
         return ans.toLowerCase(); //lowercase because it would be in all caps otherwise, which is a bit too aggressive
      }

      //helper method that keeps track of the user's decision to send text to a file
      public static boolean sendToFile() {
         boolean result = false; //default value
         Scanner input = new Scanner(System.in); //collects user input
         System.out.println("Would you like to send your output to a file? (y/n)");
         String ans = input.nextLine();
         while(!(ans.equalsIgnoreCase("y") || ans.equalsIgnoreCase("n"))) { //enforces a valid response
            System.out.println("Please enter 'y' or 'n'");
            ans = input.nextLine();
         }
         if(ans.equalsIgnoreCase("y")) { //if the user wants to send this program's output to a file
            result = true;
         }
         input.close();
         return result;

      }

      //helper method to add program output to the user file
      public static void writeToFile(String x) throws IOException {
         FileWriter fw = new FileWriter("user.txt"); //allows for file rewriting
         fw.write(x); //adds given text to user.txt
         fw.close();
      }
      
   }
      
