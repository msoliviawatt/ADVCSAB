import java.io.*;
  
public class Tree
{
   private TreeNode myRoot;
   
   public Tree()
   {
      myRoot = null;
   }
   
   //pre: root points to an in-order Binary Search Tree
   //post:adds x to the tree such that the tree is still an in-order Binary Search Tree
   public void add(Comparable x)
   {
      myRoot = addHelper(myRoot, x);
   }
   
   private TreeNode addHelper(TreeNode root, Comparable x)
   {
      if(root == null) {
         return new TreeNode(x);
      } else if(x.compareTo(root.getValue()) < 0){
         root.setLeft(addHelper(root.getLeft(), x));
      } else if(x.compareTo(root.getValue()) > 0) {
         root.setRight(addHelper(root.getRight(), x));
      }
      return root;
   }
   
   //pre: root points to an in-order Binary Search Tree
   //post:removes x from the tree such that the tree is still an in-order Binary Search Tree
   public void remove(Comparable x)
   {
      myRoot = removeHelper(myRoot, x);
   }
   
   private TreeNode removeHelper(TreeNode root, Comparable x)
   {
      if(root == null) {
         return null;
      } else if (x.compareTo(root.getValue()) < 0) {
         root.setLeft(removeHelper(root.getLeft(), x));
      } else if (x.compareTo(root.getValue()) > 0) {
         root.setRight(removeHelper(root.getRight(), x));
      } else {
         if(isLeaf(root)) {
            root = null;
         } else if(oneKid(root)) {
            if(root.getLeft() != null) {
               root = root.getLeft();
            } else {
               root = root.getRight();
            }
         } else {
            TreeNode child = root.getRight();
            while(child.getLeft() != null) {
               child = child.getLeft();
            }
            root.setValue(child.getValue());
            root.setRight(removeHelper(root.getRight(), child.getValue()));
            return root;
         }
      }
      return root;
   }
   
   //pre: root points to an in-order Binary Search Tree
   //post:shows the elements of the tree such that they are displayed in prefix order
   public void showPreOrder()
   {
      preOrderHelper(myRoot);
      System.out.println();
   }
   
   private void preOrderHelper(TreeNode root)
   {
      if(root != null) {
         System.out.print(root.getValue() + " ");
         preOrderHelper(root.getLeft());
         preOrderHelper(root.getRight());;
      }
   }
   
   //pre: root points to an in-order Binary Search Tree
   //post:shows the elements of the tree such that they are displayed in infix order
   public void showInOrder()
   {
      inOrderHelper(myRoot);
      System.out.println();
   }
   
   private void inOrderHelper(TreeNode root)   
   {
      if(root!=null)
      {
         inOrderHelper(root.getLeft());
         System.out.print(root.getValue() + " ");    
         inOrderHelper(root.getRight());
      }
   }
      
   //pre: root points to an in-order Binary Search Tree
   //post:shows the elements of the tree such that they are displayed in postfix order
   public void showPostOrder()
   {
      postOrderHelper(myRoot);
      System.out.println();
   }
   
   private void postOrderHelper(TreeNode root)
   {
      if(root != null) {
         postOrderHelper(root.getLeft());
         postOrderHelper(root.getRight());
         System.out.print(root.getValue() + " ");
      }
      
   }
   
   //pre: root points to an in-order Binary Search Tree
   //post:returns whether or not x is found in the tree
   
   public boolean contains(Comparable x)
   {
      if (searchHelper(myRoot, x)==null)
         return false;
      return true;
   }
   
   private TreeNode searchHelper(TreeNode root, Comparable x)
   {
      if(root == null) {
         return null;
      } else if(x.compareTo(root.getValue()) < 0) {
         searchHelper(root.getLeft(), x);
      } else if(x.compareTo(root.getValue()) > 0) {
         searchHelper(root.getRight(), x);
      }
      return root;
   }
   
   //pre: root points to an in-order Binary Search Tree
   //post:returns a reference to the parent of the node that contains x, returns null if no such node exists
   //THIS WILL BE CALLED IN THE METHOD removeRecur
   private TreeNode searchParent(TreeNode root, Comparable x)
   {
      if(root == null || isLeaf(root)) {
         return null;
      } else if ((root.getLeft() != null && root.getLeft().getValue() == x) || (root.getRight() != null && root.getRight().getValue() == x)) {
         return root;
      } else  if(x.compareTo(root.getValue()) < 0) {
         searchParent(root.getLeft(), x);
      } else {
         searchParent(root.getRight(), x);
      }
      return null;
   }
   
   //post: determines if root is a leaf or not O(1)
   private boolean isLeaf(TreeNode root)
   {
      if(root != null) {
         return (root.getLeft() == null && root.getRight() == null);
      }
      return false;
   }
      
   //post: returns true if only one child O(1)
   private boolean oneKid(TreeNode root)
   {
      if(root != null) {
         return ((root.getLeft() != null && root.getRight() == null)) || ((root.getRight() != null && root.getLeft() == null));
      }
      return false;
   }
      
   //pre: root points to an in-order Binary Search Tree
   //post:returns the number of nodes in the tree
   
   public int size()
   {
      return sizeHelper(myRoot);
   }
   
   private int sizeHelper(TreeNode root)
   {
      if(root == null) {
         return 0;
      }
      int left = sizeHelper(root.getLeft());
      int right = sizeHelper(root.getRight());
      return 1 + left + right;
   }
         
   public int height()
   {
      return heightHelper(myRoot);
   }

   //pre: root points to an in-order Binary Search Tree
   //post:returns the height (depth) of the tree
   public int heightHelper(TreeNode root)
   {
      if(root == null) {
         return -1;
      }
      int left = heightHelper(root.getLeft());
      int right = heightHelper(root.getRight());
      return 1 + Math.max(left, right);
   }
   
   //EXTRA CREDIT
   //pre: root points to an in-order Binary Search Tree
   //post:returns true if p is an ancestor of c, false otherwise
   public boolean isAncestor(TreeNode root, Comparable p, Comparable c)
   {
      if(root == null) {
         return false;
      }
      if(p.compareTo(root.getValue()) < 0 && c.compareTo(root.getValue()) < 0) {
         return isAncestor(root.getLeft(), p, c);
      } else if(p.compareTo(root.getValue()) > 0 && c.compareTo(root.getValue()) > 0) {
         return isAncestor(root.getRight(), p, c);
      } else {
         return true;
      }
   }
   
   //EXTRA CREDIT
   //pre: root points to an in-order Binary Search Tree
   //post:shows all elements of the tree at a particular depth
   public void printLevel(TreeNode root, int level)
   {
      if(root != null) {
         if (level == 1) {
            System.out.print(root.getValue() + " ");
         } else {
            printLevel(root.getLeft(), level - 1);
            printLevel(root.getRight(), level - 1);
         }
      }  
   }
 
  //Nothing to see here...move along.
   private String temp;
   private void inOrderHelper2(TreeNode root)   
   {
      if(root!=null)
      {
         inOrderHelper2(root.getLeft());
         temp += (root.getValue() + ", "); 
         inOrderHelper2(root.getRight());
      }
   }

   public String toString()
   {
      temp="";
      inOrderHelper2(myRoot);
      if(temp.length() > 1)
         temp = temp.substring(0, temp.length()-2);
      return "[" + temp + "]";
   }
  
}