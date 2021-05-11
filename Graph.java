import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class Graph {
	// TODO: declare private members
	private Map<Integer, List<Integer>> edges;
	private int numNodes;
	

	public Graph(int nodes) {
		// TODO: implement Graph constructor
		edges = new TreeMap<Integer, List<Integer>>();
		numNodes = nodes;
	}

	/*
	 * Creates a directional edge between nodes u and v in the adjacency list,
	 * i.e., node u has node v as direct neighbor but not the other way around.
	 */
	public void addEdge(int u, int v) {
		// TODO: implement addEdge method
		Set<Integer> keySet = edges.keySet();
		if (!keySet.contains(u)) {
			List<Integer> newList = new ArrayList<>();
			newList.add(v);
			edges.put(u, newList);
		}
		else {
			List<Integer> values = edges.get(u);
			values.add(v);
		}
		
		
	}

	/*
	 * Retrieves the total nodes in the graph
	 * 
	 * @return the total nodes in the graph
	 */
	public int getNodes() {
		// TODO: implement getNodes method
		return numNodes;
	}

	/*
	 * This method is intentionally left blank as a student should figure out
	 * the return type of this method
	 * 
	 * In general, the method should look something like this:
	 * 
	 * public return_type getNeighbors(int node) { return return_type; }
	 * 
	 * This method takes a node number as a parameter and returns its neighbors
	 * in the graph in a sorted order. The sorted order should produce the
	 * following string format when we call the toString on the returned result
	 * type: [2, 3]. This means, if the neighbors of a node are 2,3 and the
	 * return_type of getNeigbors is a List<Integer>, then if we capture the
	 * return in another list in the caller method, then performing
	 * list.toString() would generate [2, 3]
	 * 
	 * @return the neighbors of node in sorted order
	 */
	public List<Integer> getNeighbors(int node) {
		// TODO: implement getNeighbors(int node) method
		List<Integer> neighbors = edges.get(node);
		Collections.sort(neighbors);
		return neighbors;
		
		
	}
	

	/*
	 * Overrides the toString method of the Object class. This method must
	 * return the adjacency list is a complete sorted order, i.e., source nodes
	 * in the adjacency list must be sorted, as well as the neighbor list of
	 * each node must be sorted in ascending order.
	 * 
	 * For a two node graph (1)<---->(2), the toString of adjacency list would
	 * look like: {1=[2], 2=[1]}
	 * 
	 * For a two node graph (1)---->(2), the toString of adjacency list would
	 * look like: {1=[2], 2=[]}
	 * 
	 * @return the string representation of the adjacency list
	 */
	public String toString() {
		// TODO: implement toString()
		String output = "{";
		Set<Integer> keySet = edges.keySet();
		for (Integer key : keySet) {
			output += key + "=";
			output += getNeighbors(key);
			output += ", ";
		}
		int lastComma = output.lastIndexOf(",");
		output = output.substring(0, lastComma);
		output += "}";
		return output;
	}

	// OPTIONAL: add helper methods
}