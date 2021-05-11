import java.util.ArrayList;
import java.util.List;

public class DFS {
	private Graph graph;
	// TODO: declare private members

	public DFS(Graph graph) {
		this.graph = graph;
	}

	/*
	 * Performs the Depth-first search routine between the source and
	 * destination nodes received as parameters and returns the traversed path
	 * if found.
	 * 
	 * Must reset the visit state of all nodes each time this method is called.
	 * 
	 * @return the traversed path from source to destination or
	 * "Destination unreachable." in case there is no such path
	 */
	public String runDFS(int source, int destination) {
		// TODO: implement runDFS
		List<Integer> path = new ArrayList<>();
		List<Integer> result = dfsHelper(source, destination, path);
		if (result == null) {
			return "Destination unreachable";
		}
		else {
			return result.toString();
		}
	}
	
	public List<Integer> dfsHelper(int source, int dest, List<Integer> path) {
		// This method acts as a help for the runDFS method by passing in an additional parameter to 
		// keep track of the path being travel during the recursive calls.
		path.add(source);
		if (source == dest) {
			return path;
		}
		List<Integer> neigh = graph.getNeighbors(source);
		for (Integer item : neigh) {
			if (!path.contains(item)) {
				List<Integer> result = dfsHelper(item, dest, path);
				if (result != null) {
					return result;
				}
			}
		}
		
		path.remove(path.size()-1);
		return null;
	}
}
