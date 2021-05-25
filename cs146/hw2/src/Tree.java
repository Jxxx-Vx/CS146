
public class Tree {	
	private Node root;
	public Tree() {
		root = null;
	}
	
	public boolean insert(int x) {
		if(root == null) {
			root = new Node(x);
			root.size++;
		}
		else {
			Node n = root.find(x);
			if(n != null)
				return false;
			n = root.findInsert(x);
			n.orderKeys(x);
		}
		return true;
	}
	
	public int size() {
		return root.size;
	}
	
	public int size(int x) {
		Node n = root.find(x);
		if(n == null)
			return 0;
		return n.size;
	}
	
	public int get(int x) {
		return root.search(x, 0);
	}
	
	public Node getRoot() {
		return root;
	}
	class Node
	{  
		public Node parent;
		public int numKeys;
		public int size;
		
		public Integer[] keys = new Integer[3]; 
		
		public Node[] childs = new Node[4];
		
	    
	    public Node(int x) {
	    	keys[0] = x;
	    	numKeys = 1;
	    }
	    
	    public Node(int x, Node parent) {
	    	keys[0] = x;
	    	this.parent = parent;
	    	numKeys = 1;
	    	size++;
	    }
	    
	    public boolean isFull() {
	    	if(keys[1] != null)
	    		return true;
	    	return false;
	    }
	    
	    public boolean isLeaf() {
	    	if(childs[0] == null)
	    		return true;
	    	return false;
	    }
	    	    
	    public void orderKeys(int x) {
	    	int n = numKeys-1;
	    	while(n > -1 && keys[n] > x) { 
	    		keys[n+1] = keys[n]; 
	    		n--;
	    	}
	    	keys[n+1] = x;
	    	numKeys++;
	    	
	    	if(numKeys == 3)
	    		splitNode(this);
	    }
	
	    public void splitNode(Node n) {
	    	
	    	Node left = new Node(n.keys[0], n.parent);
	    	Node right = new Node(n.keys[2], n.parent);
	    	int child = 0;
	    	
	    	if(!n.isLeaf()) {
    			for(int i = 0; i < 2; i++) {
    				left.childs[i] = n.childs[i];
    				right.childs[i] = n.childs[i+2];
    				left.childs[i].parent = left;
    				right.childs[i].parent = right;
    				left.size += left.childs[i].size;
    				right.size += right.childs[i].size;
    			}
    		}
	   
	    	if(n.parent != null) {
	    		for(int i = 0; i < n.numKeys; i++) {
	    			if(n == n.parent.childs[i])
	    				child = i;
	    		}
	    		n.parent.orderChilds(child, left, right); 
	    		n.parent.orderKeys(n.keys[1]);	
	    	}
    		 	
	    	else { // root
	    		Node center = new Node(n.keys[1]);
		    	center.childs[0] = left;
		    	center.childs[1] = right;
		    	left.parent = center;
		    	right.parent = center;
		    	root = center;
		    	center.size = left.size+right.size+1;
	    	}
	    }
	   
	    public void orderChilds(int childIndex, Node left, Node right) {
	    	childs[childIndex] = left;
	    	int n = 2;
	    	
	    	while(childs[n]!= null)
	    		n++;
	    	n--;

	    	while(childs[n].keys[0] > right.keys[0]) { 
	    		childs[n+1] = childs[n]; 
	    		n--;
	    	}
	    	childs[n+1] = right;	    	
	    }
	    
	    public int search(int find, int count) {
			
			if(isLeaf()) {
				for(int i = 0; i < numKeys; i++) {
					if(count == find)
						return keys[i];
					count++;
				}
			}
			if(size(childs[0].keys[0]) + count == find) 
				return keys[0];
			
			else if(size(childs[0].keys[0]) + size(childs[1].keys[0]) + 1 + count == find)
				return keys[1];
		
			int i = 0; 
			while(size(childs[i].keys[0]) + count < find) {
				i++;
				count += size(childs[i-1].keys[0])+1;
			}
						
			return childs[i].search(find, count);
		}
	    
	    public Node find(int x) {
		   	
		    for(int i = 0; i < numKeys; i++) {
		    	if(keys[i] == x)
		    		return this;
		    	else if(x < keys[i] &&!isLeaf())
		    		return childs[i].find(x);
		    }
		    if(!isLeaf())
		    	return childs[numKeys].find(x);
		    return null;
		}
		
		public Node findInsert(int x) {
			this.size++;
	    	if(this.isLeaf())
	    		return this;
	    	int i = 0;
	    	
	    	if(x > this.keys[i])
	    		i++;
			return this.childs[i].findInsert(x); 	
	    }
	}
}
	

	