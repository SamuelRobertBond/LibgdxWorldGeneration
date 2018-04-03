package bond.generation.overworld;

import java.util.HashMap;

import bond.generation.overworld.locgen.TownType;
import bond.generation.overworld.locgen.roadgeneration.RoadType;
import bond.generation.overworld.terraingen.TerrainType;
import bond.generation.roomgeneration.Room;
import bond.generation.utils.Direction;
import bond.generation.utils.Utils;

public class OverworldTile {

	public final int x;
	public final int y;
	public final long seed;
	
	public final TerrainType terrainType;
	public final TownType locType;
	public final RoadType roadType;
	
	public HashMap<Direction, OverworldTile> adjacentTiles;
	
	public OverworldTile(int x, int y, TerrainType terrainType, TownType locType, RoadType roadType) {
		
		this.x = x;
		this.y = y;
		this.seed = Utils.getRandomLong(); //Adjust this to load seeds
		
		this.terrainType = terrainType;
		this.locType = locType;
		this.roadType = roadType;
		
		adjacentTiles = new HashMap<Direction, OverworldTile>();
	}

	public Room getRoom() {
		return new Room(this);
	}
	
}
