package bond.generation.roomgeneration;

import java.util.Random;

import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.mygdx.game.AssetLoader;

import bond.generation.display.RoomGenerator;
import bond.generation.overworld.OverworldTile;
import bond.generation.overworld.locgen.TownType;
import bond.generation.overworld.locgen.roadgeneration.RoadType;
import bond.generation.overworld.terraingen.TerrainType;
import bond.generation.utils.Direction;
import bond.generation.utils.Utils;

public class Room {

	public final int ROOM_SIZE = 15;
	
	private TiledMap map;
	
	private TerrainType terrain;
	private RoomType roomType;
	private Direction dirs;
	
	private final long seed;
	private final OverworldTile tile;
	
	public Room(OverworldTile tile) {
		
		this.tile = tile;
		this.seed = Utils.getRandomLong();
		
		terrain = tile.terrainType;
		roomType = RoomType.WILDERNESS;
		
		if(tile.locType == TownType.CITY){
			roomType.equals(TownType.CITY);
		}
		
		System.out.println("Room: ( " + tile.x + ", " + tile.y);
		map = RoomGenerator.generateRoom(tile.seed, ROOM_SIZE, ROOM_SIZE, tile);
	}
	
	public TiledMap generateTileMap(){
		
		int width = ROOM_SIZE;
		int height = ROOM_SIZE;
		
		Random rand = new Random();
		rand.setSeed(seed);
		
		TiledMap map = new TiledMap();
		MapLayers layers = map.getLayers();
		
		TiledMapTileLayer floor = new TiledMapTileLayer(width, height, 16, 16);
		
		for(int x = 0; x < width; ++x){
			for(int y = 0; y < height; ++y){
				Cell cell = new Cell();
				cell.setTile(new StaticTiledMapTile(AssetLoader.terrainTextureMap.get(tile.terrainType)));
				floor.setCell(x, y, cell);
			}
		}
		
		layers.add(floor);
		layers.add(generateWalls(width, height));
		
		return map;
		
	}
	
	public TiledMapTileLayer generateWalls(int width, int height){
		
		TiledMapTileLayer walls = new TiledMapTileLayer(width, height, 16, 16);
		
		for(int x = 0; x < width; ++x){
			
			if(tile.adjacentTiles.get(Direction.UP) == null){
				Cell cell = new Cell();
				cell.setTile(new StaticTiledMapTile(AssetLoader.terrainTextureMap.get(TerrainType.WATER)));
				walls.setCell(x, 0, cell);
			}
			
			if(tile.adjacentTiles.get(Direction.DOWN) == null){
				Cell cell = new Cell();
				cell.setTile(new StaticTiledMapTile(AssetLoader.terrainTextureMap.get(TerrainType.WATER)));
				walls.setCell(x, height-1, cell);
			}
			
		}
		
		for(int y = 0; y < height; ++y){
			
			if(tile.adjacentTiles.get(Direction.LEFT) == null){
				Cell cell = new Cell();
				cell.setTile(new StaticTiledMapTile(AssetLoader.terrainTextureMap.get(TerrainType.WATER)));
				walls.setCell(0, y, cell);
			}
			
			if(tile.adjacentTiles.get(Direction.RIGHT) == null){
				Cell cell = new Cell();
				cell.setTile(new StaticTiledMapTile(AssetLoader.terrainTextureMap.get(TerrainType.WATER)));
				walls.setCell(width - 1, y, cell);
			}
			
		}
		
		return walls;
	}
	
	public TiledMap getMap() {
		return map;
	}
	
}
