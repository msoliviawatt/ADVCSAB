import java.util.*;

public class ReverseAutoWordGuesser {
    static Scanner input = new Scanner(System.in);
    public static MyStack<String> toStack(String word) {
        MyStack<String> ans = new MyStack();  
        for(int i=0; i<word.length(); i++)
        ans.push("" + word.charAt(i));
        return ans;
    }
      
    public static void main(String[] arg) {
        System.out.println("Player 1, please enter a word for Player 2 to guess");
        String word = input.nextLine().toLowerCase();
        while(word.length() <= 0) {
            System.out.println("Player 1, please enter a word for Player 2 to guess");
            word = input.nextLine().toLowerCase();
        }
        MyStack<String> wordStack = new MyStack();
        wordStack = toStack(word);
        int counter = word.length() - 1;
        int score = 0;
        int helper = 1;
        String fullWord = word;
        while(!wordStack.isEmpty() && wordStack.size() > 1) {
            System.out.println();
            System.out.print("Player 2, your word is: ");
            for(int i = 0; i < word.length() - helper; i++) {
                System.out.print("_ ");
            }
            for(int i = word.length() - helper; i < word.length(); i++) {
                System.out.print(word.substring(i, i + 1) + " ");
            }
            wordStack.pop();
            System.out.println("\nScore: " + score + "\nWhat letter do you guess?");
            String guess = input.nextLine();
            System.out.println();
            if(wordStack.peek().equalsIgnoreCase(guess)) {
                System.out.println("Good guess. You earn " + helper + " point(s).");
                score += helper;
                helper++;
                counter--;
            }
            else {
                System.out.println("Incorrect. You lose " + counter + " point(s).");
                score -= word.length() - counter;
                helper++;
                counter--;
            }
        }
        System.out.println("\nCongratulations! You have completed the game.\nThe word was: " + fullWord);
        System.out.println("Final score: " + score);
        input.close();
    }
}
