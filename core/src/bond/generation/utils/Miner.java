package bond.generation.utils;

import java.util.LinkedList;
import java.util.Stack;

public class Miner {

	
	private Stack<Direction> previous;
	
	protected int x;
	protected int y;
	
	protected int mapWidth;
	protected int mapHeight;
	
	private LinkedList<Direction> directions;
	
	public Miner(int x, int y, int mapWidth, int mapHeight) {
		
		
		
		this.x = x;
		this.y = y;
		this.mapWidth = mapWidth;
		this.mapHeight = mapHeight;
		
		directions = new LinkedList<Direction>();
		directions.add(Direction.UP);
		directions.add(Direction.DOWN);
		directions.add(Direction.LEFT);
		directions.add(Direction.RIGHT);
		
		previous = new Stack<Direction>();
		
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	
	public boolean move(){
		
		if(directions.isEmpty()){
			return false;
		}
		
		//Store current direction
		Direction dir = directions.get(Utils.getRandomInt(directions.size()));
		
		int x_offset = 0;
		int y_offset = 0;
		
		if(dir.equals(Direction.UP)){
			y_offset = 1;
		}else if(dir.equals(Direction.DOWN)){
			y_offset = -1;
		}else if(dir.equals(Direction.LEFT)){
			x_offset = -1;
		}else if(dir.equals(Direction.RIGHT)){
			x_offset = 1;
		}
		
		//Check if that direction breaks the bounds
		if(!canMove(x_offset, y_offset)){
			previous.push(dir);
			directions.remove(dir);
			if(directions.isEmpty()){
				return false;
			}
			return move();
		}
		
		//Add all of the visted directions back in
		while(!previous.isEmpty()){
			directions.add(previous.pop());
		}
		
		//Change the map
		previous.push(dir);
		directions.remove(dir);
		
		//Move the miner
		this.x += x_offset;
		this.y += y_offset;
		
		return true;
		
	}

	protected boolean canMove(int x_offset, int y_offset) {
		
		//In map bounds
		
		if(x + x_offset < 0 || x + x_offset >= mapWidth || y + y_offset < 0 || y + y_offset >= mapHeight){
			return false;
		}
		return true;
	}
	
}
