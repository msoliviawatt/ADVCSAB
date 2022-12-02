import java.util.*;

public class SparseMatrix<anyType> implements Matrixable<anyType> {
    private int rows;
    private int cols;
    private LinkedList<Cell<anyType>> matrix;

    public SparseMatrix(int r, int c) {
        rows = r;
        cols = c;
        matrix = new LinkedList<Cell<anyType>>();
    }

    public int size() {
        return matrix.size();
    }
    public int numRows() {
        return rows;
    }
    public int numColumns() {
        return cols;
    }


    public anyType get(int r, int c) {
        if (matrix.size() > 0) {
            int key = r * cols + c;
            for (int i = 0; i < matrix.size(); i++) { 
               if (matrix.get(i).getKey() == key) {
                  return matrix.get(i).getValue();
               }
            }
        }
        return null;
    }

    public anyType set(int r, int c, anyType x) {
        int k = r * cols + c;
        Cell<anyType> replacement = new Cell<anyType>(r, c, k, x);
        if(matrix.size() > 0) {
            for(int i = 0; i < matrix.size(); i++) {
                if (matrix.get(i).getKey() == k) {
                    Cell<anyType> helper = matrix.set(i, replacement);
                    return helper.getValue();
                }
            }
        }
        matrix.add(replacement);
        return null;
    }

    public void add(int r, int c, anyType x){
        int key = r * cols + c;
        Cell<anyType> thing = new Cell<anyType>(r, c, key, x);
        for(int i = 0; i < matrix.size(); i++) {
            if(matrix.get(i).getKey() > key) {
                matrix.add(i, thing);
                break;
            }
        }
        matrix.add(thing);
    }

    public anyType remove(int r, int c) {
        if (matrix.size() > 0) {
            int key = r * cols + c; 
            for (int i = 0; i < matrix.size(); i++) {
                if (matrix.get(i).getKey() == key) {  
                    return matrix.remove(i).getValue();
                }
            }
        }
        return null;
    }

    public String toString() {
        String s = "";
        for(int r = 0; r < rows; r++) {
            for(int c = 0; c < cols; c++) {
                int key = r * cols + c;

                anyType thing = null;
                for (int i = 0; i < matrix.size(); i++) {

                   if (matrix.get(i).getKey() == key) {
                      thing = matrix.get(i).getValue();
                      s += thing + " ";
                      break;
                    }           
                }

                if(thing == null) {
                    s += "- ";
                } 
            }

            s += "\n";
        }
        return s;
    }
}
