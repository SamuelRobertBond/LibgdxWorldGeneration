package bond.generation.overworld.locgen;

import bond.generation.overworld.terraingen.TerrainType;
import bond.generation.utils.Quadrant;
import bond.generation.utils.Utils;

public class LocationQuadrant extends Quadrant{

	public int city_x;
	public int city_y;
	
	public TerrainType[][] map;
	public boolean placed;
	
	public LocationQuadrant(Quadrant quadrant, TerrainType[][] map) {
		
		super(quadrant.x, quadrant.y, quadrant.width, quadrant.height);
		this.map = map;
		placed = false;
		
		for(int i = 0; i < width * height; ++i) {
			if(setLocation()) {
				placed = true;
				break;
			}
		}
		
	}
	
	public boolean setLocation() {
		
		city_x = Utils.getRandomInt(width) + x;
		city_y = Utils.getRandomInt(height) + y;
		
		if(map[city_x][city_y] != null && map[city_x][city_y] != TerrainType.WATER) {
			return true;
		}
		
		return false;
	}

}
