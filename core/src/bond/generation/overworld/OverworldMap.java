package bond.generation.overworld;

import bond.generation.overworld.locgen.TownType;
import bond.generation.overworld.locgen.roadgeneration.RoadType;
import bond.generation.overworld.terraingen.TerrainType;

public class OverworldMap {

	public final OverworldTile[][] map;
	
	public OverworldMap(TerrainType[][] terrain, TownType[][] locs, RoadType[][] roads) {
		
		int width = terrain.length;
		int height = terrain[0].length;
		
		map = new OverworldTile[width][height];
		
		for(int x = 0; x < width; ++x) {
			for(int y = 0; y < height; ++y) {
				map[x][y] = new OverworldTile(x, y, terrain[x][y], locs[x][y], roads[x][y]);
			}
		}
		
	}
	
	public OverworldTile getTile(int x, int y) {
		return map[x][y];
	}
	
	public OverworldTile[][] getMap(){
		return map;
	}
	
}
