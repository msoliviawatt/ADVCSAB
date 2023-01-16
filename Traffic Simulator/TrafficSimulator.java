import java.io.FileReader;
import java.util.*;
import java.io.*;

public class TrafficSimulator {
    public static void main(String [] arg) {
        ArrayList<Integer> numbers = new ArrayList<Integer>();
        try {
            FileReader reader = new FileReader("Traffic.txt");
            Scanner scanner = new Scanner(reader);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                int num = Integer.parseInt(line);
                numbers.add(num);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        } 

        int mainsec, mainprob, maplesec, mapleprob;
        mainsec = numbers.get(0);
        mainprob = numbers.get(1);
        maplesec = numbers.get(2);
        mapleprob = numbers.get(3);

        int counter = 0;

        Scanner input = new Scanner(System.in);
        System.out.println("How many light cycles would you like to see?");
        int cycles = input.nextInt();

        MyQueue<String> main = new MyQueue<String>();
        MyQueue<String> maple = new MyQueue<String>();

        for(int i = 0; i < cycles; i++) {
            System.out.println("Cycle " + (i + 1) + "\n----------");
            for(int j = 0; j < mainsec; j++) {
                System.out.println("Main street (GREEN): " + main);
                System.out.println("Maple street (RED): " + maple + "\n");
                int x = (int)(Math.random() * 99 + 1);
                int y = (int)(Math.random() * 99 + 1);
                if(x <= mainprob) {
                    main.add(randLetter());
                }
                if(y <= mapleprob) {
                    maple.add(randLetter());
                }
                if(!main.isEmpty()) {
                    main.remove();
                    counter++;
                }
            }
            for(int j = 0; j < maplesec; j++) {
                System.out.println("Main street (RED): " + main);
                System.out.println("Maple street (GREEN): " + maple + "\n");
                int x = (int)(Math.random() * 99 + 1);
                int y = (int)(Math.random() * 99 + 1);
                if(x <= mainprob) {
                    main.add(randLetter());
                }
                if(y <= mapleprob) {
                    maple.add(randLetter());
                }
                if(!maple.isEmpty()) {
                    maple.remove();
                    counter++;
                }
            }
        }
        System.out.println("Number of cars that made it through the intersection: " + counter);
        input.close();
    }

    public static String randLetter() {
        int num = (int)(Math.random()*26);
        if(Math.random() < 0.5) {
            return "" + (char)('a' + num);
        } else {
            return "" + (char)('A' + num);
        }
    }
}
