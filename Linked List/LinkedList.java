//d oberle - 2014 - a linked list object

   public class LinkedList<anyType> implements ListInterface<anyType>
   {
      private ListNode<anyType> head;		//refers to the first element
   
      public LinkedList()						//constructor
      {
         head = null;
      }
   
   //WRITE THIS METHOD***********************************************
   //post:  returns true of the list is empty
      public boolean isEmpty()
      
      {
         return false;							//temporary code to keep the file compiling
      }
   //****************************************************************
   
   
   //WRITE THIS METHOD***********************************************
   //post: adds x to the front of the list
      public void addFirst(anyType x)				
      {
      
      }
   //****************************************************************
   
   //post:  adds x to the end of the list, O(n)
      public void addLast(anyType x)
      {
         if (head==null)										//if list is empty
            head = new ListNode(x,null);					//head refers to the only node
         else
         {
            ListNode current = head;
            while(current.getNext()!= null)				//make current go to the last element
               current = current.getNext();
            current.setNext(new ListNode(x, null));	//make the last element's next become a new ending node
         }
      }
   
   //pre:  the head is not null
   //post: returns the head's value - returns null if the pre-condition fails, O(1)
      public anyType getFirst()
      {
         if (head==null)							//if list is empty
            return null;							//this is our flag for an unsuccessful add
         return head.getValue();
      }
   
   //WRITE THIS METHOD***********************************************
   //pre:  the list is not empty
   //post: returns the lastNode's value - returns null if the pre-conditon fails
      public anyType getLast()
      {
      
         return null;						//temporary code to keep the file compiling
      }
   //****************************************************************
   
   
   //WRITE THIS METHOD***********************************************
   //pre:  the head is not null
   //post: removes the first element from the list and returns its value
   //      returns null if the pre-condition fails
      public anyType removeFirst() 
      {
      
         return null;						//temporary code to keep the file compiling
      }
   //****************************************************************
   
   //pre:  the head is not null
   //post: removes the last element from the list and returns its value, O(n) 
   //      returns null if the pre-condition fails
      public anyType removeLast()
      {
         if (head==null)											//if list is empty
            return null;
         anyType temp = getLast();
         if (head.getNext() == null)							//only one element in the list
            head = null;
         else
         {
            ListNode current = head;							//current will traverse the list
            while(current.getNext().getNext() != null)	//move current to the second to last node
               current=current.getNext();
            current.setNext(null);								//then cap off the end of the new, smaller list with null
         }	
         return temp;
      }
   
   //WRITE THIS METHOD***********************************************
   //post: returns the number of elements
      public int size()
      {
      
         return 0;							//temporary code to keep the file compiling
      }
   //****************************************************************
   
   //WRITE THIS METHOD***********************************************
   //pre: index >=0 and index < size()
   //post: returns the object at a specific index (first element is index 0)
      public anyType get(int index)		
      {
      
         return null;						//temporary code to keep the file compiling
      }	
   //****************************************************************	
   
   //WRITE THIS METHOD***********************************************
   //pre:  index >=0 and index < size()
   //post: changes the element at a specific index to x, returning the element that was originally there
      public anyType set(int index, anyType x)
      {
      
         return null;						//temporary code to keep the file compiling
      }	
   //****************************************************************
   
   //post: adds element x to the end of the list, returns true if successful
      public boolean add(anyType x)
      {
         addLast(x);
         return true;			
      }	
   
   //WRITE THIS METHOD***********************************************
   //pre:  index >=0 and index < size()
   //post: adds element x at index i, returns true if successful
      public boolean add(int index, anyType x)
      {
      
         return true;			
      }	
   //****************************************************************
   
   //WRITE THIS METHOD***********************************************
   //pre: index >=0 and index < size()
   //post: removes and returns the object at a specific index (first element is index 0)
      public anyType remove(int index)		
      {
      
         return null;						//temporary code to keep the file compiling
      }	
   //****************************************************************	
   
   //post: returns all elements of the list as a String 
   //in the form [a0, a1, a2, . . . , an-1],  O(n)
      public String toString()
      {
         String ans = "[";									//start with left bookend						
         ListNode current =  head;
         while(current != null)
         {
            ans += current.getValue().toString();
            current = current.getNext();
            if (current != null)							//don't add comma after the last element
               ans += ",";
         }
         ans += "]";											//end with right bookend
         return ans;
      }
   }