import static org.junit.Assert.*;

import org.junit.Test;


public class GivenTreeTests
{
	
	
	@Test
	public void testInsert() {
		
	  
	     Tree t2 = new Tree();
	     t2.insert(20);
	     assertEquals(t2.getRoot().keys.length, 3);
	     assertEquals(t2.getRoot().childs.length, 4);
	     t2.insert(19);
	     t2.insert(18);
	     t2.insert(17);
	     t2.insert(16);
	     t2.insert(15);    
	     t2.insert(14);
	     t2.insert(13);
	     t2.insert(12);
	     t2.insert(11);
	     t2.insert(10);
	     t2.insert(9);
	     t2.insert(8);
	     t2.insert(7);
	     t2.insert(6);
	     t2.insert(5);
	     t2.insert(4);
	     t2.insert(3);
	     t2.insert(2);
	     t2.insert(1);
	     t2.insert(0);
	     t2.insert(21);
	     
	     assertEquals(t2.getRoot().keys[0], 13, 0.00001);
	     assertEquals(t2.get(0), 0);
	     assertEquals(t2.get(1), 1);
	     assertEquals(t2.get(2), 2);
	     assertEquals(t2.get(3), 3);
	     assertEquals(t2.get(4), 4);
	     assertEquals(t2.get(5), 5);
	     assertEquals(t2.get(6), 6);
	     assertEquals(t2.get(7), 7);
	     assertEquals(t2.get(8), 8);
	     assertEquals(t2.get(9), 9);
	     assertEquals(t2.get(10), 10);
	     assertEquals(t2.get(11), 11);
	     assertEquals(t2.get(12), 12);
	     assertEquals(t2.get(13), 13);
	     assertEquals(t2.get(14), 14);
	     assertEquals(t2.get(15), 15);
	     assertEquals(t2.get(16), 16);
	     assertEquals(t2.get(17), 17);
	     assertEquals(t2.get(18), 18);
	     assertEquals(t2.get(19), 19);
	     assertEquals(t2.get(20), 20);
	     assertEquals(t2.get(21), 21);

	  
	     assertEquals(true, t2.getRoot().childs[0].isFull());
	     assertEquals(false, t2.getRoot().isFull());

	     
	}
	@Test
	public void ascending() {
	      Tree t = new Tree();
	      for(int i = 0; i < 10000; i++) {
	    	  t.insert(i);
	      }
/*
	      for(int i = 0; i < 10000; i++) {
	    	  assertEquals(i, t.get(i));
	      }
	      */ //i = 8191
	}
	
	public void random() {
	    Tree t = new Tree();
		int[] store = new int[1000000];
		int temp = 0;
		for(int i = 0; i < 1000001; i++) {
			temp = (int)Math.random() * 100000000;
			store[i] = temp;
			t.insert(temp);
		}
		
		for (int i = 1; i < 10001; i++) {
            int key = store[i];
            int j = i - 1;
 
            while (j >= 0 && store[j] > key) {
                store[j + 1] = store[j];
                j = j - 1;
            }
            store[j + 1] = key;
        }
		for(int i = 0; i < 10001; i++) {
			assertEquals(store[i],t.get(i));
		}	
	}

   @Test
   public void singleNodeTree()
   {
      Tree t = new Tree();
      t.insert(9);
   
      assertEquals(1, t.size(9));
      assertEquals(0, t.size(8));
      assertEquals(0, t.size(10));
      
      t.insert(15);
      assertEquals(2, t.size(9));
      assertEquals(0, t.size(8));
      assertEquals(0, t.size(10));
      assertEquals(2, t.size(15));
      assertEquals(0, t.size(18));

      t = new Tree();
      t.insert(15);
      t.insert(9);
      assertEquals(2, t.size(9));
      assertEquals(0, t.size(8));
      assertEquals(0, t.size(10));
      assertEquals(2, t.size(15));
      assertEquals(0, t.size(18));
      
      assertEquals(9, t.get(0));
      assertEquals(15, t.get(1));


   }

   @Test
   public void oneSplitLeft()
   {
      Tree t = new Tree();
      t.insert(9);
      t.insert(15);
      t.insert(1);
      
      assertEquals(3, t.size(9));
      assertEquals(1, t.size(15));
      assertEquals(0, t.size(17));
      assertEquals(0, t.size(11));

      assertEquals(1, t.size(1));
      assertEquals(0, t.size(0));
      assertEquals(0, t.size(3));

      assertEquals(1, t.get(0));
      assertEquals(9, t.get(1));
      assertEquals(15, t.get(2));
      
      assertEquals(3,t.size());
   }



   @Test
   public void oneSplitRight()
   {
      Tree t = new Tree();
      t.insert(1);
      t.insert(9);
      t.insert(15);
      
      assertEquals(3, t.size(9));
      assertEquals(1, t.size(15));
      assertEquals(0, t.size(17));
      assertEquals(0, t.size(11));

      assertEquals(1, t.size(1));
      assertEquals(0, t.size(0));
      assertEquals(0, t.size(3));
      
      assertEquals(1, t.get(0));
      assertEquals(9, t.get(1));
      assertEquals(15, t.get(2));
      assertEquals(3,t.size());


   }

   @Test
   public void oneSplitMiddle()
   {
      Tree t = new Tree();
      t.insert(1);
      t.insert(15);
      t.insert(9);
      
      
      assertEquals(3, t.size(9));
      assertEquals(1, t.size(15));
      assertEquals(0, t.size(17));
      assertEquals(0, t.size(11));

      assertEquals(1, t.size(1));
      assertEquals(0, t.size(0));
      assertEquals(0, t.size(3));
      
      assertEquals(1, t.get(0));
      assertEquals(9, t.get(1));
      assertEquals(15, t.get(2));
      assertEquals(3,t.size());


   }

   
   
   @Test
   public void testDuplicates()
   {
      Tree t = new Tree();
      t.insert(1);
      t.insert(9);
      t.insert(15);
      t.insert(13);
      t.insert(20);
      t.insert(7);
      t.insert(4);
      
      t.insert(1);
      t.insert(9);
      t.insert(15);
      t.insert(1);
      t.insert(9);
      t.insert(15);
      t.insert(13);
      t.insert(20);
      t.insert(7);
      t.insert(4);
      t.insert(13);
      t.insert(20);
      t.insert(7);
      t.insert(4);

      assertEquals(7, t.size(9));
      assertEquals(3, t.size(4));
      assertEquals(3, t.size(15));

      assertEquals(0, t.size(12));
      assertEquals(1, t.size(13));
      assertEquals(0, t.size(14));
      assertEquals(0, t.size(19));
      assertEquals(1, t.size(20));
      assertEquals(0, t.size(21));

      assertEquals(1, t.size(1));
      assertEquals(0, t.size(0));
      assertEquals(0, t.size(3));

      assertEquals(0, t.size(6));
      assertEquals(1, t.size(7));
      assertEquals(0, t.size(8));
   

      
      assertEquals(1, t.get(0));
      assertEquals(4, t.get(1));
      assertEquals(7, t.get(2));
      assertEquals(9, t.get(3));
      assertEquals(13, t.get(4));
      assertEquals(15, t.get(5));
      assertEquals(20, t.get(6));
      assertEquals(7,t.size());    
   }
}



   

