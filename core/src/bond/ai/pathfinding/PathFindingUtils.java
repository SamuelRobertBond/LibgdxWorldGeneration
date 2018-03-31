package bond.ai.pathfinding;

import java.util.LinkedList;
import java.util.Queue;

import com.badlogic.gdx.math.Vector2;

public class PathFindingUtils {

	public static PathfindingResult getPath(Vector2 src, Vector2 dst, AStarPathfinder pathfinder) {
		
		LinkedList<Node> list = new LinkedList<Node>();
		System.out.println(src + " " + dst);
		
		while(!src.equals(dst)) {
			
			Node node = pathfinder.findNextNode(src, dst);
			
			if(node != null) {
				
				//This is for testing an error where the node goes back and forth
				if(list.size() > 2 && list.get(list.size() - 2).equals(node)) {
					System.out.println("\n-----------PATH ERROR---------------");
					while(!list.isEmpty()) {
						node = list.remove();
						src = new Vector2(node.x, node.y);
						System.out.println(src + " " + dst);
					}
					return new PathfindingResult(false, null);
				}
				
				list.offer(node);
				src = new Vector2(node.x, node.y);
			}else {
				System.out.println("Filed");
				return new PathfindingResult(false, null);
			}
		}
		
		
		return new PathfindingResult(true, list);
		
	}
	
}
