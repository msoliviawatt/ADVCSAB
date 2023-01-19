   //d oberle - 2003

   public class ListNode<anyType>
   {
      private anyType value;		//the data contained in the node
      private ListNode next;		//a reference to the next object in the list
      private ListNode prev;
   
      public ListNode(anyType initValue, ListNode initNext, ListNode initPrev)		//constructor
      {
         value = initValue;
         next = initNext;
         prev = initPrev;
      }
   
      public ListNode(anyType initValue)		//constructor
      {
         this(initValue, null, null);					//this calls the objects other constructor
      }
   
   //pre:  the ListNode object for which this is called is not null
   //post: returns the data stored in the ListNode,  O(1)
      public anyType getValue()				
      {
         return value;
      }
   
   //pre:  the ListNode object for which this is called is not null
   //post: returns the reference to the next ListNode object in the Linked List,  O(1)
   
      public ListNode getNext()
      {
         return next;
      }

      public ListNode getPrev() {
         return prev;
      }
   
   //pre:  the ListNode object for which this is called is not null
   //post: changes the objects data to newValue,  O(1)
      public void setValue(anyType newValue)
      {
         value = newValue;
      }
   
   //pre:  the ListNode object for which this is called is not null
   //post: changes the ListNode's next reference to newNext,  O(1)
      public void setNext(ListNode newNext)
      {
         next = newNext;
      }

      public void setPrev(ListNode newPrev) {
         prev = newPrev;
      }
   }
