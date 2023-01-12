import java.util.*;
public class Parenthesis {
    public static MyStack<String> toStack(String word) {
        MyStack<String> ans = new MyStack();  
        for(int i=0; i<word.length(); i++)
        ans.push("" + word.charAt(i));
        return ans;
    }

    public static boolean test(String x) {
        MyStack<String> wordStack = new MyStack();
        for(int i = 0; i < x.length(); i++) {
            String y = x.substring(i, i + 1);

            if(y.equals("(") || y.equals("[") || y.equals("{")) {
                wordStack.push(y);
            }
            if(wordStack.isEmpty()) {
                return false;
            }
            String check;
            switch (y) {
                case ")":
                    check = wordStack.pop();
                    if(check.equals("{") || check.equals("[")) {
                        return false;
                    }
                    break;
                case "}":
                    check = wordStack.pop();
                    if(check.equals("(") || check.equals("[")) {
                        return false;
                    }
                    break;
                case "]":
                    check = wordStack.pop();
                    if(check.equals("(") || check.equals("{")) {
                        return false;
                    }
                    break;
            }
        }
        return (wordStack.isEmpty());
    }
    public static void main(String[] arg) {
        Scanner input = new Scanner(System.in);
        String choice = "y";
        while(choice.equalsIgnoreCase("y")) {
            System.out.println("Enter an expression to scan for balances parenthesis");
            String user = input.nextLine();
            if(test(user)) {
                System.out.println("The parenthesis are balanced!");
            }
            else {
                System.out.println("The parenthesis are not balanced :(");
            }
            System.out.println("Enter 'y' to use this program again or 'n' to quit");
            choice = input.nextLine();
            while(!(choice.equalsIgnoreCase("y") || choice.equalsIgnoreCase("n"))) {
                System.out.println("Invalid input.\nEnter 'y' to use this program again or 'n' to quit");
                choice = input.nextLine();
            }
        }
        input.close();
    }
}