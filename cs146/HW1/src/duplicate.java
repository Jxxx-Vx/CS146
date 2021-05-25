
public class duplicate {
	
	private Node root;
	
	public duplicate() {
		root = null;
	}
	
	public boolean insert(int x) {
		if(root == null) {
			root = new Node(x);
			root.size++;
		}
		else {
			Node n = find(root, x);
			if(n != null)
				return false;
			n = findInsert(root, x);
			n.orderKeys(x);
		}
		return true;
	}
	
	public int size() {
		return root.size;
	}
	
	public int size(int x) {
		Node n = find(root, x);
		if(n == null)
			return 0;
		return n.size;
	}
	
	public int get(int x) {
		return root.search(root, x, 0);
	}
	
	public Node find(Node n, int x) {
	   	if(n == null)
	   		return n;
	   	
	    for(int i = 0; i < n.numKeys; i++) {
	    	if(n.keys[i] == x)
	    		return n;
	    	else if(x < n.keys[i])
	    		return find(n.childs[i], x);
	    }
	    return find(n.childs[n.numKeys], x);
	}
	
	public Node findInsert(Node n, int x) {
    	
    	n.size++;
    	if(n.isLeaf())
    		return n;
    	int i = 0;
    	if(!n.isFull()) {
	    	if(x > n.keys[i])
	    		i++;
		    return findInsert(n.childs[i], x);
    	}
    	else {
    		while(i!= 2 && x > n.keys[i])
    			i++;
    		return findInsert(n.childs[i], x);
    	}  
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
	    public Node(int x, Node parent, int size) {
	    	keys[0] = x;
	    	this.parent = parent;
	    	numKeys = 1;
	    	this.size = size;
	    }
	    
	    
	    public boolean isFull() {
	    	if(keys[1] != null)
	    		return true;
	    	return false;
	    }
	    
	    public boolean isLeaf() {
	    	if(childs[0] == null && childs[1] == null)
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
	    	if(n.childs[3] != null){
	    		  		
	    		Node center = new Node(n.keys[1], n.parent, n.size);
		    	Node left = new Node(n.keys[0], center);
		    	Node right = new Node(n.keys[2], center);
		    	center.childs[0] = left;
		    	center.childs[1] = right;
		        	
		    	for(int i = 0; i < 2; i++) {
		    		left.childs[i] = n.childs[i];
		    		right.childs[i] = n.childs[i+2];
		    		left.childs[i].parent = center.childs[0];
		    		right.childs[i].parent = center.childs[1];
		    		center.childs[0].size += left.childs[i].size;
		    		center.childs[1].size += right.childs[i].size;
		    	}	
		    if(n == root)
		    	root = center;  
	    	}
	    		
	    	else if(n.parent != null) {
	    		int child = 0;
	    		for(int i = 0; i < 3; i++) {
	    			if(n == n.parent.childs[i])
	    				child = i;
	    		}
	    	 	Node left = new Node(n.keys[0], n.parent);
		    	Node right = new Node(n.keys[2], n.parent);
	    		n.parent.orderChilds(child, left, right); 
	    		n.parent.orderKeys(n.keys[1]);
	    	
	    	}
	    	else { // root
	    		Node center = new Node(n.keys[1], null, 3);
		    	center.childs[0] = new Node(n.keys[0], center);
		    	center.childs[1] = new Node(n.keys[2], center);
		    	root = center;
		    	
	    	}
	    }
	    
	    public void orderChilds(int childIndex, Node left, Node right) {
	    	childs[childIndex] = left;
	    	int n = 2;
	    	
	    	while(childs[n]!= null)
	    		n++;
	    	n--;

	    	while(n > -1 && childs[n].keys[0] > right.keys[0]) { 
	    		childs[n+1] = childs[n]; 
	    		n--;
	    	}
	    	childs[n+1] = right;	    	
	    }
	    
	    public int search(Node n,int find, int count) {
			
			if(n.isLeaf()) {
				for(int i = 0; i < n.numKeys; i++) {
					if(count == find)
						return n.keys[i];
					count++;
				}
			}
			
			if(size(n.childs[0].keys[0]) + count == find)
				return n.keys[0];
			else if(size(n.childs[0].keys[0]) + 1 + count + size(n.childs[1].keys[0]) == find)
				return n.keys[1];
			

			int i = 0; 
			while(size(n.childs[i].keys[0]) + count < find) {
				i++;
				count += size(n.childs[i-1].keys[0])+1;
			}
			return search(n.childs[i], find, count);
		}
	}
}
	

	