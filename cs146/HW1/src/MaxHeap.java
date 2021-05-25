import java.util.*;

public class MaxHeap
{
   private ArrayList<Student> students;
   
   public MaxHeap(int capacity)
   {
      students = new ArrayList<Student>(capacity);
   }
      
   public MaxHeap(Collection<Student> collection)
   {
	
		students = new ArrayList<Student>(collection);
		
		for(int n = 0; n < size(); n++) {
		     	 students.get(n).setIndex(n);
		 }
		      
		for(int i = size()/2 - 1; i >= 0; i--)
		{
		 maxHeapify(i);
		 }
	  
	   }
   
   public void swapParent(int index) {  
	  while(students.get(index).compareTo(students.get(parent(index))) > 0) { 	
	     swap(index, parent(index));
	     index = parent(index);
	  }    	  	
   }
   
   public Student getMax()
   {
      if(size() < 1)
      {
         throw new IndexOutOfBoundsException("No maximum value:  the heap is empty.");
      }
      return students.get(0);
   }
   
   
   public Student extractMax()
   {
      Student value = getMax();
      students.set(0,students.get(size()-1));
      students.get(0).setIndex(0);
      students.remove(size()-1);
      maxHeapify(0);
      return value;
   }
    
   public int size()
   {
      return students.size();
   }
   
   public void insert(Student elt)
   {
      //Please write me.  I should add the given student into the heap, // add student to bottom then maxheapify nlgn time worst case i think
	  //following the insert algorithm from the videos.
	   students.add(elt);
	   elt.setIndex(students.size()-1);
	   swapParent(elt.getIndex());
   }
   
   public void addGrade(Student elt, double gradePointsPerUnit, int units)
   {
      //Please write me.  I should change the student's gpa (using a method      // this will be tricky
	  //from the student class), and then adjust the heap as needed using
	  //the changeKey algorithm from the videos.
	   
	   elt.addGrade(gradePointsPerUnit, units); 
	   swapParent(elt.getIndex());
	   maxHeapify(elt.getIndex()); 

	   
   }
   
   private int parent(int index)
   {
      return (index - 1)/2;
   }
   
   private int left(int index) {
	   return 2 * index + 1;
   }
   
   private int right(int index) {
	   return 2 * index + 2; 
   }
   
  
   private void swap(int from, int to)
   {
	   Student val = students.get(from); 
	   students.set(from,  students.get(to));
	   students.get(from).setIndex(from);
	   students.set(to, val);
	   students.get(to).setIndex(to);
   }
   

   
   private void maxHeapify(int index)
   {
      int left = left(index);
      int right = right(index);
      int largest = index;
      if (left <  size() && students.get(left).compareTo(students.get(largest)) > 0) 
      {
         largest = left;
      }
      if (right <  size() && students.get(right).compareTo(students.get(largest)) > 0) 
      {
         largest = right;
      }
      if (largest != index) // if node is smaller than child
      {
         swap(index, largest);
         maxHeapify(largest); 
      }  
   }
}