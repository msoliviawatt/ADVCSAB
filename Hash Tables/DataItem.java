import java.io.*;
import java.util.*;

public class DataItem implements Comparable {
    private int num;
    private String word;

    //constructor
    public DataItem (int n, String s) {
        num = n;
        word = s;
    }

    //getter methods
    public int getNum() {
        return num;
    }

    public String getWord() {
        return word;
    }

    //setter methods
    public void setNum(int n) {
        this.num = n;
    }

    public void setWord(String s) {
        this.word =s;
    }

    //to string
    public String toString() {
        return num + " ";
    }

    //sum of the ASCII values * index position
    public int hashCode() {
        int sum = 0;
        for(int i = 0; i < word.length(); i++) {
            char letter = word.charAt(i);
            sum += (int)(letter) * i;
        }
        return sum;
    }

    //takes in an object which is casted to a data item
    //negative if the sent in object's num is < the parameter
    //positive if greater
    //zero if equal
    public int compareTo(Object x) {
        DataItem item = (DataItem) x;
        return Integer.compare(this.num, item.getNum());
    }

}