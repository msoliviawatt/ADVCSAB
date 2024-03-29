//d oberle - 2014 - a linked list object

public class LinkedList<anyType> implements ListInterface<anyType>
{
   private ListNode<anyType> head, tail;		//refers to the first element
   private int size;

   public LinkedList()						//constructor
   {
      head = null;
      tail = null;
      size = 0;
   }

//post:  returns true of the list is empty
   public boolean isEmpty()
   {
      if(head == null) {
         return true;
      }
      return false;						
   }

//post: adds x to the front of the list
   public void addFirst(anyType x)				
   {
      ListNode node = new ListNode<anyType>(x);
      
      if(this.isEmpty()) {
         head = node;
         tail = node;
      }
      else {
         head.setPrev(node);
         node.setNext(head);
         head = node;
      }
      size++;
   }

//post:  adds x to the end of the list, O(n)
   public void addLast(anyType x)
   {
      ListNode node = new ListNode<anyType>(x);

      if(this.isEmpty()) {
         head = node;
         tail = node;
      }
      else
      {
         node.setPrev(tail);
         tail.setNext(node);
         tail = node;
      }
      size++;
   }

//pre:  the head is not null
//post: returns the head's value - returns null if the pre-condition fails, O(1)
   public anyType getFirst()
   {
      if (head==null)							//if list is empty
         return null;						
      return head.getValue();
   }

//pre:  the list is not empty
//post: returns the lastNode's value - returns null if the pre-conditon fails
   public anyType getLast()
   {
      if(head == null) {
         return null;
      }
      return tail.getValue();						
   }

//pre:  the head is not null
//post: removes the first element from the list and returns its value
//      returns null if the pre-condition fails
   public anyType removeFirst() 
   {
      if(this.isEmpty()) {
         return null;
      }
      anyType ans = head.getValue();
      head = head.getNext();
      size--;
      return ans;
   }

//pre:  the head is not null
//post: removes the last element from the list and returns its value, O(n) 
//      returns null if the pre-condition fails
   public anyType removeLast()
   {
      if (this.isEmpty()) {
         return null;
      }
      try  {
         anyType ans = tail.getValue();
         if(size > 1) {
            tail = tail.getPrev();
            tail.setNext(null);
            size--;
         }
         else {
            this.remove(0);
         }
         return ans;
      } catch (Exception e) {
         System.out.println(e);
         return null;
      }
      
   }

//post: returns the number of elements
   public int size()
   {
      return size;
   }

//pre: index >=0 and index < size()
//post: returns the object at a specific index (first element is index 0)
   public anyType get(int index)		
   {
      if(this.isEmpty()) {
         return null;
      }
      if(index > size) {
         System.out.println("Invalid index");
         return null;
      }
      ListNode<anyType> current = head;
      for(int i = 0; i < index; i++) {
         current = current.getNext();
      }
      return current.getValue();
   }	

//pre:  index >=0 and index < size()
//post: changes the element at a specific index to x, returning the element that was originally there
   public anyType set(int index, anyType x)
   {
      if(this.isEmpty()) {
         return null;
      }
      ListNode<anyType> current = head;
      for(int i = 0; i < index; i++) {
         current = current.getNext();
      }
      anyType oldValue = current.getValue();
      current.setValue(x);
      return oldValue;
   }	

//post: adds element x to the end of the list, returns true if successful
   public boolean add(anyType x)
   {
      addLast(x);
      return true;			
   }	

//pre:  index >=0 and index < size()
//post: adds element x at index i, returns true if successful
   public boolean add(int index, anyType x)
   {
      if(this.isEmpty()) {
         return false;
      }
      if(index == 0) {
         addFirst(x);
         return true;
      }
      if(index == size) {
         addLast(x);
         return true;
      }

      ListNode<anyType> current = head;
      for(int i = 1; i < index; i++) {
         if(current != null) {
            current = current.getNext();
         }
      }
      ListNode temp = new ListNode<anyType>(x);
      temp.setNext(current.getNext());
      temp.setPrev(current);
      current.setNext(temp);
      size++;
      return true;			
   }	

//pre: index >=0 and index < size()
//post: removes and returns the object at a specific index (first element is index 0)
   public anyType remove(int index)		
   {
      if(this.isEmpty()) {
         return null;
      }
      if(index == 0) {
         return this.removeFirst();
      }
      else if(index == size  - 1) {
         return this.removeLast();
      }
      else {
         ListNode<anyType> current = head;
         for(int i = 0; i < index; i++) {
            current = current.getNext();
         }
         anyType ans = current.getValue();
         current.getPrev().setNext(current.getNext());
         current.getNext().setPrev(current.getPrev());
         size--;
         return ans;
      }					
   }	

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