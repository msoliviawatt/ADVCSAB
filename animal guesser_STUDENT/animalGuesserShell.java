import java.io.*;
import java.util.*;
//The Animal Guessing Program:	d oberle, 2006
//This game will attempt to guess an animal that the user is thinking of by asking yes/no questions.
//If the program does not know the animal, it will add it to its data base, making it a little smarter for the next user.
//Implement an array as a heap.  Each index has a parent at (index/2), a left child as (index*2) and a right child at index*2+1.
//The index path following a "no" response will go to the left child (left subtree).  
//The index path following a "yes" response will go to the right child (right subtree).

public class animalGuesserShell
{
      //pre:  "fileName" is the name of a real file containing lines of text
      //post: returns the number of lines in fileName O(n)
   public static int getFileSize(String fileName)throws IOException
   {
      Scanner input = new Scanner(new FileReader(fileName));
      int size=0;
      while (input.hasNextLine())				//while there is another line in the file
      {
         size++;										//add to the size
         input.nextLine();							//go to the next line in the file
      }
      input.close();									//always close the files when you are done
      return size;
   }
   
   	//pre:  "fileName" is the name of a real file containing lines of text - the first line intended to be unused
      //post:returns a String array of all the elements in <filename>.txt, with index 0 unused (heap) O(n)
   public static String[] readFile(String fileName)throws IOException
   {
      int size = getFileSize(fileName);		//holds the # of elements in the file
      String[] list = new String[size];		//a heap will not use index 0;
      Scanner input = new Scanner(new FileReader(fileName));
      int i=0;											//index for placement in the array
      String line;	
      while (input.hasNextLine())				//while there is another line in the file
      {
         line=input.nextLine();					//read in the next Line in the file and store it in line
         list[i]= line;								//add the line into the array
         i++;											//advance the index of the array         
      }
      input.close();	
      return list;					
   }
   
     //pre: 
     //post:displays all of the elements of the array words O(n)
   public static void showArray(String[] words)
   {
      for (int i=0; i<words.length; i++)
         System.out.println(words[i] + " ");
      System.out.println();
      System.out.println("Size of array:" + words.length);
   }
   
   //Post: puts all the elements in the array into <filename>.txt,
   //      with one element per line O(n)
   public static void writeToFile(String[] array, String filename) throws IOException
   {
      System.setOut(new PrintStream(new FileOutputStream(filename)));
      for(int i = 0; i < array.length; i++) 
         System.out.println(array[i]);
   }
   
   //pre: let>='A' && let <='Z'  OR    let>='a' && let<='z'
   //post:returns true if let is a vowel O(1)
   public static boolean isVowel(char let)
   {
      return (let=='a' || let=='e' || let=='i' || let=='o' || let=='u' || let=='A' || let=='E' || let=='I' || let=='O' || let=='U');
   }
   
    //post: returns true if a user prompt is N, No, NO, n, nO or no O(1)
   public static boolean isNo(String ans)
   {
      return (ans.toLowerCase().equals("no") || ans.toLowerCase().equals("n"));
   }
   
      //post: returns true if a user prompt is y, Y, Yes, yes, YES, yES, or yeS O(1)
   public static boolean isYes(String ans)
   {
      return (ans.toLowerCase().equals("yes") || ans.toLowerCase().equals("y"));
   }

   public static String[] increaseArrayLength(String[] x) {
      String[] result = new String[x.length * 2];
      for(int i = 0; i < x.length; i++) {
         result[i] = x[i];
      }
      return result;
   }
   
   public static void main(String argv[])throws IOException
   {
      Scanner input = new Scanner(System.in);
      int play = 1;
      boolean helper = true;
      String [] questions = readFile("animal.txt");
      while(play == 1) {
         System.out.println("Welcome!" + "\n" + "Think of an animal :D");
         int i = 1;
         String ans = "";
         while(i < questions.length) {
            if(questions[i].equals(null) || questions[i].equals("null")) {
               break;
            }
            System.out.print(questions[i] + "\n");
            ans = input.nextLine();
            if(isNo(ans)) {
               i *= 2;
               helper = true;
            }
            else if(isYes(ans)) {
               i = i * 2 + 1;
               helper = false;            }
            else {
               System.out.println("Please enter 'yes' or 'no'");
            }
         }

         if(helper) {
            i/=2;
         }
         else {
            i = i / 2 - 1;
         }
         String newAnimal = "";
         String newQuestion = "";
         String guessedAnimal = questions[i].substring(questions[i].indexOf("a"), questions[i].length() - 1);
         if(isNo(ans)) {
            System.out.println("Sad! What animal were you thinking of?: ");
            newAnimal = input.nextLine();
            System.out.println("What is a question that is true for " + newAnimal + " and false for " + guessedAnimal + "?");
            newQuestion = input.nextLine();
            questions = increaseArrayLength(questions);
            questions[i] = newQuestion;
            questions[i * 2] = "Is it a(n) " + guessedAnimal + "?";
            questions[i * 2 + 1] = "Is it a(n) " + newAnimal + "?";
         }
         else if(isYes(ans)) {
            System.out.println("Yay! I guessed your animal!");
         }
         System.out.println("Enter '1' to play again or '0' to quit");
         play = input.nextInt();
         while(play != 1 && play != 0) {
            System.out.println("Please enter 1 or 0");
         }
      }
      System.out.println("Bye!");
      writeToFile(questions, "animal.txt");
      input.close();
   } 
}