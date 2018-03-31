package bond.ai.pathfinding;

import java.util.Queue;

public class PathfindingResult {

	public boolean pathFound;
	public Queue<Node> path;
	
	public PathfindingResult(boolean pathFound, Queue<Node> path) {
		this.pathFound = pathFound;
		this.path = path;
	}
	
}
