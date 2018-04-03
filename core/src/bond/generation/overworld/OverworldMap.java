package bond.generation.overworld;

import bond.generation.overworld.locgen.TownType;
import bond.generation.overworld.locgen.roadgeneration.RoadType;
import bond.generation.overworld.terraingen.TerrainType;
import bond.generation.utils.Direction;

public class OverworldMap {

	public final OverworldTile[][] map;
	
	public OverworldMap(TerrainType[][] terrain, TownType[][] locs, RoadType[][] roads) {
		
		int width = terrain.length;
		int height = terrain[0].length;
		
		map = new OverworldTile[width][height];
		
		for(int x = 0; x < width; ++x) {
			for(int y = 0; y < height; ++y) {
				map[x][y] = new OverworldTile(x, y, terrain[x][y], locs[x][y], roads[x][y]);
				setDirectionTiles(x, y);
			}
		}
		
	}
	
	public OverworldTile getTile(int x, int y) {
		return map[x][y];
	}
	
	public OverworldTile[][] getMap(){
		return map;
	}
	
	private void setDirectionTiles(int x, int y){
		
		if(x > 0){
			map[x][y].adjacentTiles.put(Direction.LEFT, map[x - 1][y]);
		}
		
		if(x < map.length - 1){
			map[x][y].adjacentTiles.put(Direction.RIGHT, map[x + 1][y]);
		}
		
		if(y > 0){
			map[x][y].adjacentTiles.put(Direction.DOWN, map[x][y - 1]);
		}
		
		if(y > map[0].length){
			map[x][y].adjacentTiles.put(Direction.UP, map[x][y + 1]);
		}
		
	}
}
