import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.TreeSet;

import org.junit.Test;


public class SimpleGivenTests
{

   @Test
   public void oneStudent()
   {
	  MaxHeap heap = new MaxHeap(10);
      heap.insert(new Student("Susan", 3.5, 60));
      assertEquals(3.5, heap.extractMax().gpa(), .000001);
      assertEquals(0, heap.size());  
      
      ArrayList<Student> testCollection = new ArrayList<Student>();
      testCollection.add(new Student("Susan", 3.5, 60));
      MaxHeap heap2 = new MaxHeap(testCollection);
      assertEquals(3.5, heap2.extractMax().gpa(), .000001);
      assertEquals(0, heap2.size());  
      
      ArrayList<Student> testCollection2 = new ArrayList<Student>();
      MaxHeap heap3 = new MaxHeap(testCollection2);
      assertEquals(0, heap3.size()); 
   }

   @Test
   public void aInsertAFewStudents()
   {
      MaxHeap heap = new MaxHeap(10);
      heap.insert(new Student("Susan", 3.5, 60));     
      heap.insert(new Student("Ben", 3.4, 70));    
      heap.insert(new Student("Reed", 4.0, 120));
      heap.insert(new Student("Johnny", 1.2, 50));
  
      assertEquals(4.0, heap.extractMax().gpa(), .000001);
      assertEquals(3.5, heap.extractMax().gpa(), .000001);
      heap.insert(new Student("Billy", 2.7, 20));
      assertEquals(3.4, heap.extractMax().gpa(), .000001);
      assertEquals(2.7, heap.extractMax().gpa(), .000001);
      assertEquals(1.2, heap.extractMax().gpa(), .000001);
   }
  
   @Test
   public void personalTest() {
	   MaxHeap heap = new MaxHeap(30);
	   Student john = new Student("John");
	   heap.insert(john);
	   assertEquals("John", john.getName());
	   
	   Student john2 = new Student("John", 4.0, 50);
	   Student john3 = new Student("John", 4.0, 50);
	   
	   assertEquals(john2.equals(john3), true);
	   assertEquals(john2.equals(john), false);
	   heap.insert(john2);
	   heap.insert(john3);
	   
	   assertEquals(john2, john3);
   }
 

   @Test
   public void testCollection() {
	   
	   HashSet <Student> testCollection = new HashSet <Student>(5);
	   
	  
	   
	   Student susan = new Student("Susan", 3, 6);
	   Student ben = new Student("Ben", 2.4, 10);
	   Student reed = new Student("Reed", 3.3, 3);
	   Student johnny = new Student("Johnny", 1, 4);
	   
	   testCollection.add(susan);
	   testCollection.add(ben);
	   testCollection.add(reed);
	   testCollection.add(johnny);

	   
	   MaxHeap heap = new MaxHeap(testCollection);
	  
	   heap.addGrade(susan, 4, 3);  //should give her a 3.333333333 gpa
	   heap.addGrade(reed, .7, 3);  //should give him a 2.0
	   heap.addGrade(johnny,  4,  4);  //should give him a 2.5
	   
	   Student elt = new Student("Elt", 4.0, 2);
	   heap.insert(elt);
	   
	   assertEquals(elt, heap.getMax());
	   heap.addGrade(elt, 0, 10000);
	   assertEquals(susan, heap.getMax());
	   assertEquals(3.33333333, heap.extractMax().gpa(), .000001);
	   assertEquals(2.5, heap.extractMax().gpa(), .000001);
	   assertEquals(2.4, heap.extractMax().gpa(), .000001);
	   assertEquals(2.0, heap.extractMax().gpa(), .000001);
	   
   }

   @Test
   public void testEmptyCollection() 
   {
	   HashSet <Student> testCollection = new HashSet <Student>(5);
	   
	   MaxHeap heap = new MaxHeap(testCollection);
	   
	   Student harry = new Student("Harry", 0.0, 100); 
	   heap.insert(harry);
	   heap.insert(new Student("John", 4.0, 100));
	   
	   
	   assertEquals(0, heap.getMax().getIndex());
	   assertEquals(4.0, heap.getMax().gpa(), 0.000001);
	   heap.extractMax();
	   
	   heap.addGrade(harry, 10000000, 100);
	   assertEquals(harry, heap.getMax());
	   assertEquals(0, heap.getMax().getIndex());

   }
   
   @Test
   public void testArrayList() {
	   ArrayList <Student> testCollection = new ArrayList <Student>();
	   
	   MaxHeap heap = new MaxHeap(testCollection);
	   
	   Student harry = new Student("Harry", 0.0, 100); 
	   heap.insert(harry);
	   heap.insert(new Student("John", 4.0, 100));
	   
	   assertEquals(4.0, heap.extractMax().gpa(), .000001);
	   assertEquals(0.0, heap.extractMax().gpa(), .000001);
   }
   
   @Test
   public void testTreeSet() {
	   
	   Student harry = new Student("Harry", 0.0, 100);
	   
	   ArrayList <Student> testCollection = new ArrayList <Student>();
	   testCollection.add(harry);
	   testCollection.add(new Student("John", 4.0, 100));
	   TreeSet <Student> testCollection2 = new TreeSet <Student>(testCollection);
	   
	   MaxHeap heap = new MaxHeap(testCollection2);
	   
	   assertEquals(4.0, heap.extractMax().gpa(), .000001);
	   assertEquals(0.0, heap.extractMax().gpa(), .000001);
   }
   
   @Test
   public void exceptionTest()
   {
      MaxHeap heap = new MaxHeap(10);
      heap.insert(new Student("Ben", 3.4, 70));
      assertEquals(3.4, heap.extractMax().gpa(), .000001);
      try {
    	  heap.extractMax();
    	  fail("You shouldn't reach this line, an IndexOutOfBoundsException should have been thrown.");
      } catch (IndexOutOfBoundsException except) {
    	  assertEquals(except.getMessage(), "No maximum value:  the heap is empty.");
      }

   }
   
   @Test
   public void changeKeyTest()
   {
	   MaxHeap heap = new MaxHeap(10);
	   Student susan = new Student("Susan", 3, 6);
	   Student ben = new Student("Ben", 2.4, 10);
	   Student reed = new Student("Reed", 3.3, 3);
	   Student johnny = new Student("Johnny", 1, 4);
	   heap.insert(susan);;
	   heap.insert(ben);
	   heap.insert(johnny);
	   heap.insert(reed);
	   assertEquals(reed, heap.getMax());
	   
	   heap.addGrade(susan, 4, 3);  //should give her a 3.333333333 gpa
	   assertEquals(susan, heap.getMax());
	   
	   assertEquals(3.33333333, heap.extractMax().gpa(), .000001);
	   heap.addGrade(reed, .7, 3);  //should give him a 2.0
	   heap.addGrade(johnny,  4,  4);  //should give him a 2.5
	   assertEquals(2.5, heap.extractMax().gpa(), .000001);
	   assertEquals(2.4, heap.extractMax().gpa(), .000001);
	   assertEquals(2.0, heap.extractMax().gpa(), .000001);
   }
}