public class DataItem implements Comparable<DataItem> {
    private int number;
    private String word;

    public DataItem(int number, String word) {
        this.number = number;
        this.word = word;
    }

    public int getNumber() {
        return number;
    }

    public String getWord() {
        return word;
    }

    public void setNumber(int n) {
        this.number = n;
    }

    public void setWord(String s) {
        this.word = s;
    }

    public String toString() {
        return number + " " + word;
    }

    public int compareTo(DataItem other) {
        return Integer.compare(this.number, other.number);
    }

    public int hashCode() {
        int sum = 0;
        for (int i = 0; i < word.length(); i++) {
            char letter = word.charAt(i);
            sum += (int)(letter) * i;
        }
        return sum;
    }
}