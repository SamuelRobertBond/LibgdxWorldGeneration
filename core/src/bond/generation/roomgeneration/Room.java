package bond.generation.roomgeneration;

import com.badlogic.gdx.maps.tiled.TiledMap;

import bond.generation.display.RoomGenerator;
import bond.generation.overworld.OverworldTile;

public class Room {

	public final int ROOM_SIZE = 15;
	
	private TiledMap map;
	
	public Room(OverworldTile tile) {
		System.out.println("Room: ( " + tile.x + ", " + tile.y);
		map = RoomGenerator.generateRoom(tile.seed, ROOM_SIZE, ROOM_SIZE, tile);
	}
	
	public TiledMap getMap() {
		return map;
	}
	
}
