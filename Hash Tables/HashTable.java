import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class HashTable {

    private int size = 1000; //array size
    private Node[] table; //array of binary tree roots

    //node class
    private static class Node {
        private DataItem item;
        private Node left, right;

        public Node(DataItem item) {
            this.item = item;
            this.left = null;
            this.right = null;
        }
    }

    //constructor
    //creates a table of size 1000
    public HashTable() {
        table = new Node[size];
        for (int i = 0; i < size; i++) {
            table[i] = null;
        } //sets all values to null
    }

    //for adding items to the table
    public void add(DataItem item) {
        int hash = item.hashCode();
        int index = hash % size;
        Node newNode = new Node(item);
        if (table[index] == null) {
            table[index] = newNode;
        } else {
            addHelper(table[index], newNode);
        }
    }

    private Node addHelper(Node current, Node newNode) {
        if (current == null) {
            return newNode;
        } else if (newNode.item.compareTo(current.item) < 0) {
            current.left = addHelper(current.left, newNode);
        } else {
            current.right = addHelper(current.right, newNode);
        }
        return current;
    }

    public DataItem get(String word) {
        int hash = new DataItem(0, word).hashCode();
        int index = hash % table.length;
        Node current = table[index];

        while (current != null) {
            if (current.item.getWord().equals(word)) {
                return current.item;
            } else if (current.item.compareTo(new DataItem(hash, word)) > 0) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        return null;
    }

    public static void main(String[] args) throws FileNotFoundException {
        HashTable hashTable = new HashTable();
        File file = new File("hashData.txt");
        Scanner scanner = new Scanner(file);

        while (scanner.hasNextLine()) {
            String[] line = scanner.nextLine().split(" ");
            int number = Integer.parseInt(line[0]);
            String word = line[1];
            DataItem item = new DataItem(number, word);
            hashTable.add(item);
        }

        scanner.close();

        Scanner input = new Scanner(System.in);
        boolean again = true;
        while(again) {
            System.out.print("Enter a word to find its associated number: ");
            String word = input.nextLine();
            DataItem item = hashTable.get(word);
            if (item != null) {
                System.out.println("The number associated with the word \"" + word + "\" is " + item.getNumber());
            } else {
                System.out.println("There is no number associated with \"" + word + "\"");
            }
            System.out.println("Enter 'y' to run this again.");
            String hi = input.nextLine();
            if(!hi.equalsIgnoreCase("y")) {
                again = false;
            }
        }
        input.close();
    }
}