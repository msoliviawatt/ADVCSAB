import java.util.*;
public class Test {
    public static void main(String[] arg) {
        Stack<String> pile3 = new Stack<String>();
        String temp = "";
        pile3.push("a");
        pile3.push("e");
        while(!pile3.isEmpty())
        temp = pile3.pop();
        pile3.push(temp);
        pile3.push(temp);
        System.out.println(pile3);
    }
}
