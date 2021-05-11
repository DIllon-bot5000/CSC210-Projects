
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class BFS {
	// TODO: declare private members
	private Graph graph;

	public BFS(Graph graph) {
		// TODO: implement BFS constructor
		this.graph = graph;
	}

	/*
	 * Performs the Breadth-first search routine between the source and
	 * destination nodes received as parameters and returns the traversed path
	 * if found.
	 * 
	 * Must reset the visit state of all nodes each time this method is called.
	 * 
	 * @return the traversed path from source to destination or
	 * "Destination unreachable." in case there is no such path.
	 */
	public String runBFS(int source, int destination) {
		boolean visited[] = new boolean[graph.getNodes()+1];
		List<Integer> path = new ArrayList<>();
		Queue <Integer> queue = new LinkedList<>();
		Map <Integer, Integer> test = new HashMap<>();
		visited[source] = true;
		queue.add(source);
		
		while (!queue.isEmpty()) {
			int item = queue.remove();
			if (item == destination) {
				path.add(item);
				while (item != source) {
					path.add(0, (test.get(item)));
					item = test.get(item);
				}
				return path.toString();
			}

			List<Integer> neigh = graph.getNeighbors(item);
			for (Integer adj : neigh) {
				
				if (visited[adj] == false) {
					test.put(adj, item);
					queue.add(adj);
					visited[adj] = true;
				}
			}
		}
		
		return "Destination unreachable";
	}

}
