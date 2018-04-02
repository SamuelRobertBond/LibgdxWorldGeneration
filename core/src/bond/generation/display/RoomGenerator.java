package bond.generation.display;

import java.util.Random;

import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.mygdx.game.AssetLoader;

import bond.generation.overworld.OverworldTile;
import bond.generation.overworld.terraingen.TerrainType;
import bond.generation.utils.Utils;

public class RoomGenerator {

	public static TiledMap generateRoom(long seed, int width, int height, OverworldTile tile) {
		
		Random rand = new Random();
		rand.setSeed(seed);
		
		TiledMap map = new TiledMap();
		MapLayers layers = map.getLayers();
		
		TiledMapTileLayer floor = new TiledMapTileLayer(width, height, 16, 16);
		TiledMapTileLayer walls = new TiledMapTileLayer(width, height, 16, 16);
		
		for(int x = 0; x < width; ++x) {
			for(int y = 0; y < height; ++y) {
				Cell cell = new Cell();
				
				//For Testing
				TerrainType type = TerrainType.SWAMP;
				if(rand.nextInt(2) == 1) {
					type = TerrainType.ICE;
				}
				
				cell.setTile(new StaticTiledMapTile(AssetLoader.terrainTextureMap.get(type)));
				floor.setCell(x, y, cell);
			}
		}
		
		for(int x = 0; x < width; ++x) {
			Cell cell = new Cell();
			cell.setTile(new StaticTiledMapTile(AssetLoader.terrainTextureMap.get(TerrainType.FOREST)));
			walls.setCell(x, 0, cell);
			walls.setCell(x, height, cell);
		}
		
		for(int y = 0; y < height; ++y) {
			Cell cell = new Cell();
			cell.setTile(new StaticTiledMapTile(AssetLoader.terrainTextureMap.get(TerrainType.FOREST)));
			walls.setCell(0, y, cell);
			walls.setCell(width, y, cell);
		}
		
		layers.add(floor);
		layers.add(walls);
		
		return map;
	}
	
}
