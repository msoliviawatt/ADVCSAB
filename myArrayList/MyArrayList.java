    //create your own version of the ArrayList by completing this class definition
	 //look in ListInterface to see what methods you need to create
    
    public class MyArrayList<anyType> implements ListInterface<anyType>
   {
      private Object[] list;		//stores the actual elements
      private int numElements;	//used to keep track of the number of valid elements in the list
   	
       public MyArrayList()
      {
         list = new Object[10];	//start with a buffer size of 10
         numElements = 0;
      }
   
       private void doubleCapacity()	//private because this is a helper method that need not be used outside of the class
      {
         Object[] result = new Object[list.length * 2];
         for(int i = 0; i < list.length; i++) {
            result[i] = list[i];
         }
         list = result;
      	//make list twice as big, i.e. given [A, B, C, null], results with [A, B, C, null, null, null, null, null]
      	//to be used if we add an element that would be over the capacity of list
      }
      
       private void cutCapacity()	//private because this is a helper method that need not be used outside of the class
      {
         Object[] result = new Object[list.length / 2];
         for(int i = 0; i < result.length; i++) {
            result[i] = list[i];
         }
         list = result;
      	//make list half as big, i.e. given [A, B, C, null, null, null, null, null], results with [A, B, C, null]
      	//to be used if after removing an element, we have less than 1/3 of the capacity of list being used
      }

      public boolean add(anyType x) {
         if(numElements >= list.length) {
            doubleCapacity();
         }
         list[numElements] = x;
         numElements++;
         return true;
      }				//adds element x to the end of the list
 
      public boolean add(int index, anyType x) {
         if(numElements >= list.length) {
            doubleCapacity();
         }
         for(int i = numElements; i >= index; i--) {
            list[i] = list[i - 1];
         }
         list[index] = x;
         numElements++;
         return true;
      }	//adds element x at a particular index in list
                                                   //elements to the right of that index are moved over one space to the right
                                 
      public int size() {
         return numElements;
      }							//returns the number of elements in the list
      
      public anyType set(int index, anyType x) {
         Object ans = list[index];
         list[index] = x;
         return (anyType)ans;
      }	//changes the element at a specific index to x, returning the element that was originally there
      
      public anyType get(int index) {
         return (anyType)list[index];
      }				//returns the object at a specific index (first element is index 0)
      
      public anyType remove(int index) {
         if(list.length >= 3 * (numElements)) {
            cutCapacity();
         }
         Object ans = list[index];
         for(int i = index; i < numElements; i++) {
            list[i] = list[i + 1];
         }
         numElements--;
         return (anyType)ans;
      }			// removes element from position index, moving
   				// elements at position index + 1 and higher to the
   				// left (subtracts 1 from their indices) and adjusts
   				// size;  returns the element formerly at index
      public String toString()
      {
         String ans = "[";
         if(list[0] != null) {
            ans += list[0];
         }
         for(int i = 1; i < list.length; i++) {
            if(list[i] != null) {
               ans += ", " + list[i];
            }
         }
         //add all array elements with a comma separating each, i.e. [A, B, C] 
         return ans + "]";
      }
      
   }