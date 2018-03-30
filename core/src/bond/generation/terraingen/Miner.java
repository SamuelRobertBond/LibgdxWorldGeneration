package bond.generation.terraingen;

import java.util.LinkedList;
import java.util.Stack;

import bond.generation.utils.Direction;
import bond.generation.utils.TerrainType;
import bond.generation.utils.Utils;

public class Miner {

	private TerrainType type;
	private Stack<Direction> previous;
	
	private int x;
	private int y;
	
	private TerrainType[][] map;
	
	private LinkedList<Direction> directions;
	
	public Miner(int x, int y, TerrainType[][] map, TerrainType type) {
		
		this.x = x;
		this.y = y;
		this.map = map;
		this.type = type;
		
		directions = new LinkedList<Direction>();
		directions.add(Direction.UP);
		directions.add(Direction.DOWN);
		directions.add(Direction.LEFT);
		directions.add(Direction.RIGHT);
		
		previous = new Stack<Direction>();
		
	}

	public TerrainType getType() {
		return type;
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
		x += x_offset;
		y += y_offset;
		
		//Change the map
		map[x][y] = type;
		
		return true;
		
	}

	private boolean canMove(int x_offset, int y_offset) {
		
		//In map bounds
		if(x + x_offset < 0 || x + x_offset >= map.length || y + y_offset < 0 || y + y_offset >= map[0].length){
			return false;
		}
		
		//Checks if hitting terrain
		if(map[x + x_offset][y + y_offset] != null){
			return false;
		}
		
		//both conditions are met
		return true;
	}
	
}
