import java.util.*;
public class MyStack<anyType> implements Stackable<anyType>{
    private LinkedList<anyType> list;

    public MyStack() {
        list = new LinkedList<anyType>();
    }

    public void push(anyType x) {
        list.add(x);
    }

    public anyType pop() {
        if(list.size() > 0) {
            anyType x = list.removeLast();
            return x;
        }
        return null;
    }

    public anyType peek() {
        if(list.size() > 0) {
            anyType x = list.get(list.size() - 1);
            return x;
        }
        return null;
    }

    public boolean isEmpty() {
        if(list.size() > 0) {
            return false;
        }
        return true;
    }

    public int size() {
        return list.size();
    }

    public String toString() {
        String x = "";
        for(int i = list.size() - 1; i >= 0; i--) {
            x += "[" + list.get(i) + "], ";
        }
        return x;
    }
}
