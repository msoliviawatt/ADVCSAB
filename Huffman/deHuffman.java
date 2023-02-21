// Name:              Date:
   import java.util.Scanner;
   import java.io.*;
   
    public class deHuffman
   {
      /*
       *    The main method should ask the user to enter a name for the file which will 
       * be the center part of the message and Huffman code filenames.  If the user enters 
       * "special", then the program should look for the code file with name scheme_special.txt
       * and the message file at message_special.txt  The codes are loaded into the tree and
       * then used to translate the message.
       */

       public static void main(String[] args) throws IOException
      {
      //Ask the user to enter the middle part of the filenames
      
      //Open a scanner on the message.filename.txt and another on scheme.filename.txt
      
      //Read in the message from the message text file, then you are done with that scanner
      
      //Now call the huffmanTree method, passing it the scheme Scanner so it can read
      //  and construct the tree.
      
      //Then pass the encoded message and the root of the tree to dehuff to uncompress
      //  it.  Print the result of this method.
      }
      
      /*
       *    Method huffmanTree is passed a scanner that is already linked to the file
       * containing the Huffman codes to load.  These should be loaded into a binary
       * tree where the left child represents a 0 and the right child represents a 1.
       * Nodes which are not leaves have a null value, while leaves hold the letter
       * corresponding to the path of 0s and 1s followed to get to the leaf.
       * No recursion is needed in this method.
       */

       public static TreeNode huffmanTree(Scanner schemeScan)
      {
      //Create the root node and save a reference to it (you have to return it at the end)
      
      //Let the file reading drive the processing.  Read each line until there are no more lines.
      
      //For each line, take off the first letter and save it - it is the actual letter.
      
      //Now read the ones and the zeroes.  Keep track of the current node you are on.
      //  When you find a zero, move to the left.  when you find a one, move right.
      //  If there is a node there keep going, and if the node is null,
      //  create a new node with a null VALUE and attach it to the current node.
      //  When you run out of ones and zeroes in the code, set the current node's value to
      //    the letter. (This should be the leaf at the end of the code path.)  
      }
      
      /*
       *    Method dehuff is passed the encoded text to be decoded, and the root of the tree that was
       * built by the huffmanTree method.  Characters are read from the text String and direct
       * the movement through the tree.  When a leaf is reached, a letter has been decoded and
       * can be added to the decoded message.  Then the next part of the message will begin
       * again at the root and trace down to a letter of the tree again.  When the message is 
       * completely decoded, the decoded version of the message is returned.
       * You can do this method recursively or iteratively.  (Iteratively is easier here)
       * If you want to do recursion, you should add the top node as another parameter
       */
       public static String dehuff(String text, TreeNode root)
      {
         //read through the tree, following the text's ones and zeroes, until you
         //   reach a letter (VALUE not equal to null).
         
         // When you reach a letter, add it to the message and then start again at 
         //   the top of the Huffman tree.
         
         // Keep track of the current node you are on and loop through the digits of the message.
      }
   }
