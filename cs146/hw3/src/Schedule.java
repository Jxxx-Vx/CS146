import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Schedule {
	
	private ArrayList<Job> order;
	private ArrayList<Job> sort;
	private boolean changeR;
	private boolean changef;
	private boolean cycle;
	private int max;
	
	public Schedule() {
		order = new ArrayList<Job>();
		sort = new ArrayList<Job>();
		changeR = true;
		changef = true;
	}
	
	public Job insert(int time) {
		order.add(new Job(time));
		changeR = true;
		changef = true;
		return order.get(order.size()-1);
	}
	
	public Job get(int index) {
		return order.get(index);
	}
	
	public void sort() { 
		Queue<Job> q = new LinkedList<Job>();
		sort.clear();
		
		for(Job j: order) {
			if((j.inDegrees = j.incoming.size()) == 0)
				q.add(j);
		}
		
		while(!q.isEmpty()) {
			Job j = q.remove();
			sort.add(j);
			for(Job n: j.outcoming) {
				if((n.inDegrees -= 1) == 0)
					q.add(n);
			}
		}
	}
	
	public int finish() {
		if(cycle)
			return -1;
		
		if(!changef) //if finished is called repeatedly
			return max;
		
		changef = false; // checks for if finish is called after finish
		
		sort(); 
				
		if(sort.size() != order.size()) { // tests for cycle for new sorted list
			cycle = true; // finish will always return -1 now.
			return -1;
		}
			
		for(Job j: sort) {
			for(Job j2: j.outcoming) {
				if(j2.distance < j.distance + j.time)
					j2.distance = j.distance + j.time;
				}
			if(max < j.distance + j.time)
				max = j.distance + j.time;
			}
		
		changeR = false; // will be used if start is called after finish, list should be relaxed by now.
		return max;
	}
	
	class Job{
	
		private ArrayList<Job> outcoming;
		private ArrayList<Job> incoming;
		private int inDegrees;
		private int distance;
		public int time;
	
		private Job(int time) {
			this.time = time;
			outcoming = new ArrayList<Job>();
			incoming  = new ArrayList<Job>();
		}
		
		public void requires(Job j) {
			j.outcoming.add(this);
			incoming.add(j);
			changeR = true;
			changef = true;
		}
		
		public int start() {
			if(!changeR && inDegrees == 0) // if no changes, list already relaxed, return distance
				return distance;
			
			else if(inDegrees > 0)// cycle detection for no change 
				return -1;
			
			sort();
			
			if(inDegrees > 0) //cycle detection after sort
				return -1;
			
			for(Job j: sort) { //relax after start
				for(Job j2: j.outcoming) {
					if(j2.distance < j.distance + j.time)
						j2.distance = j.distance + j.time;
				}
			}
			changeR = false;
			return distance;			
		}
	}
}


