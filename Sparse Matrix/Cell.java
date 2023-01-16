public class Cell<anyType> {

    private int row;
    private int col;
    private int key;
    private anyType value;

    public Cell(int r, int c, int k, anyType v) {
        row = r;
        col = c;
        key =k;
        value = v;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public int getKey() {
        return key;
    }

    public anyType getValue() {
        return value;
    }
}
