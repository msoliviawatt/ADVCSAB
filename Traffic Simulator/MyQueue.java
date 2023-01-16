  //d oberle 2005
  
    public class MyQueue<E> implements Queueable<E>
   {
      private java.util.LinkedList<E> list;
   
      public MyQueue()
      {
         list = new java.util.LinkedList<E>();
      }
   
      public void add(E x)
      {
         list.add(x);
      }
   
      public E remove()
      {
         try {
            return list.removeFirst();
         } catch (Exception e) {
            System.out.println(e);
         }
         return null;
      }
   
      public boolean isEmpty()
      {
         return list.size() == 0;
      }
   
      public E peek()
      {
         return list.get(0);
      }
   
      public int size()
      {
         return list.size();
      }
   
      public String toString()
      {
         return list.toString();
      }
   
   }