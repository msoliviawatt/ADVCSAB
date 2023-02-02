import java.util.*;

public class Permutations {
    public static ArrayList<String> recursion(String x) {
        ArrayList<String> ans = new ArrayList<String>();

        //base case
        if(x.length() == 1) {
            ans.add(x);
        } else {
            for(int i = 0; i < x.length(); i++) {
                String first = x.substring(i, i + 1); //reserves first letter
                String rest = x.substring(0, i) + x.substring(i + 1); //the rest of the letters
                ArrayList<String> others = recursion(rest); //creates an array list with the combinations of the other letters
                for(String j : others) {
                    ans.add(first + j); //concatenates the other letters to the first letter
                }
                recursion(rest);
            }
        }
        return ans;
    }

    public static void main(String[] arrgs) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter a string");
        String x = input.nextLine();
        System.out.println(recursion(x));
        input.close();
    }
}

