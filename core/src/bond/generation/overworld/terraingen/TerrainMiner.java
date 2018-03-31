package bond.generation.overworld.terraingen;

import bond.generation.utils.Miner;

public class TerrainMiner extends Miner{

	private TerrainType[][] map;
	private TerrainType type;
	
	public TerrainMiner(int x, int y, TerrainType[][] map, TerrainType type) {
		
		super(x, y, map.length, map[0].length);
		
		this.map = map;
		this.type = type;
		
	}
	
	@Override
	public boolean move() {
		
		boolean moved = super.move();
		
		if(!moved) {
			return false;
		}
		
		map[x][y] = type;
		return true;
	}
	
	@Override
	protected boolean canMove(int x_offset, int y_offset) {
		
		if(!super.canMove(x_offset, y_offset)) {
			return false;
		}
		
		if(map[x + x_offset][y + y_offset] != null){
			return false;
		}
		
		return true;
	}
	
	public TerrainType getType() {
		return type;
	}

}
