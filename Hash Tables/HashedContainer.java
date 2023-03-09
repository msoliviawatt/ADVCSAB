public class HashedContainer {
    private final int SIZE = 1000;
    private TreeNode[] table;

    //constructor
    public HashedContainer() {
        table = new TreeNode[SIZE];
    }

    public void add(DataItem x) {
        int codeofhash = x.hashCode();
        int index = codeofhash % SIZE;

        if(table[index] == null) {
            table[index] = new TreeNode(x);
        } else {
            table[index].add(x);
        }
    }

    
}
