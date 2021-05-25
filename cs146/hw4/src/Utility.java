
public class Utility{
	
	public static boolean [] find(UndirectedGraph g) {
		boolean [] minVcover = new boolean[g.size()];
		if(g.size() == 0)
			return minVcover;
		
		for(int n = 0; n < minVcover.length; n++) {//linear time to set all booleans to false;
			minVcover[n] = false;
		}
				
		int count = 0;
		
		while(!g.has(count)) {//finds minimum # of vertex cover starting from 1				
			count++;
		}
		
		if(count == 0)
			return minVcover; //returns an array of all false booleans, works for no edges or partially some edges
		
		int check = 0;
		int size = g.size()-1;
		
		for(int n = size; n > -1; n--) {
			if(g.remove(n).has(count-1)) { // checks if new vertex has vertex count of the previous min -1 or less
				check++;
				if(check <= count)// nodes only marked true if all vertex cover nodes aren't found yet.						
					minVcover[n] = true;
			}
		}
		return minVcover;
	}
}
		
//a b c, f f f
//a-b c, g = a-b, g = a , f t f
//a b-c, g = a b, f f t
//a-c, b, g = a b, f f t
//a-b, a-c, g = a-b, g = a-c, g = b c, 
//a-b, b-c, g = a-b, g = a c, f, t, f
//a-c, b-c, g = a b, f f t
//a-b, b-c, a-c, count = 2, g = a-b, c = true, g = a-c, b = true, f t t

//a-b,a-c,a-d, count = 1, g = a b c, g = a b d, g = a c d, g = b c d, a = true, t f f f	
//a-b,b-c,a-d count = 2, g = a b c,  d = true, 
//a-b-c-d, count = 2, g = a-b-c, g = a-b d, g = a c-d, g = b-c-d, F F T T 
		
		
		
		
		
