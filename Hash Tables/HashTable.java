import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class HashTable {

    private final int SIZE = 1000; //array size
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
        table = new Node[SIZE];
        for (int i = 0; i < SIZE; i++) {
            table[i] = null;
        } //sets all values to null
    }

    //for adding items to the table
    public void add(DataItem item) {
        int hash = item.hashCode();
        int index = hash % SIZE;
        Node newNode = new Node(item);

        if (table[index] == null) { //if there is no item at that index
            table[index] = newNode; //add item at that index
        } else {
            Node current = table[index];
            Node parent;

            while (true) {
                parent = current;
                if (item.compareTo(current.item) < 0) {
                    current = current.left;
                    if (current == null) {
                        parent.left = newNode;
                        break;
                    }
                } else {
                    current = current.right;
                    if (current == null) {
                        parent.right = newNode;
                        break;
                    }
                }
            }
        }
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

        return null; // item not found
    }

    public static void main(String[] args) throws FileNotFoundException {
        HashTable hashTable = new HashTable();
        Scanner scanner = new Scanner(new File("hashData.txt"));

        while (scanner.hasNextLine()) {
            String[] line = scanner.nextLine().split(" ");
            int number = Integer.parseInt(line[0]);
            String word = line[1];
            DataItem item = new DataItem(number, word);
            hashTable.add(item);
        }

        scanner.close();

        Scanner input = new Scanner(System.in);
        System.out.print("Enter a word to find its associated number: ");
        String word = input.nextLine();
        DataItem item = hashTable.get(word);
        if (item != null) {
            System.out.println("The number associated with the word \"" + word + "\" is " + item.getNumber());
        } else {
            System.out.println("No number associated with the word \"" + word + "\" found.");
        }
        input.close();
    }
}
