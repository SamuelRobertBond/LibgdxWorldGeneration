package bond.ai.pathfinding;

import com.badlogic.gdx.ai.pfa.Connection;
import com.badlogic.gdx.utils.Array;

public class Node{
	
	public int x;
	public int y;
	
	public final int index;
	public boolean isWall;
	
	private final Array<Connection<Node>> connections;
	
	public Node(AStarMap map, int x, int y, boolean isWall) {
		this.x = x;
		this.y = y;
		this.isWall = isWall;
		this.index = x * map.getHeight() + y;
		this.connections = new Array<Connection<Node>>();
	}
	
    public int getIndex () {
        return index;
    }

    public Array<Connection<Node>> getConnections () {
        return connections;
    }

    @Override
    public String toString() {
        return "Node: (" + x + ", " + y + ")";
    }
	
}