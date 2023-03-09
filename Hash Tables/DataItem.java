import java.io.*;
import java.util.*;

public class DataItem implements Comparable {
    private int num;
    private String word;

    public DataItem (int n, String s) {
        num = n;
        word = s;
    }

    public int getNum() {
        return num;
    }

    public String getWord() {
        return word;
    }

    public void setNum(int n) {
        num = n;
    }

    public void setWord(String s) {
        word = s;
    }

    public String toString() {
        return num + " ";
    }

    public int hashCode() {
        return null;
    }

    public int compareTo(Object x) {
        return Integer.compare(this.num, x.getNum());
    }
}