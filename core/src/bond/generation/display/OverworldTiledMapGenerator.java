package bond.generation.display;

import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.mygdx.game.AssetLoader;

import bond.generation.locgen.LocationMap;
import bond.generation.locgen.TownType;
import bond.generation.terraingen.TerrainMap;
import bond.generation.terraingen.TerrainType;

public class OverworldTiledMapGenerator {

	public static TiledMap generateOverworld(){
		
		//Generate Terrain Map
		TerrainMap terrainMap = new TerrainMap(30);
		terrainMap.generate();
		
		TerrainType terrainArray[][] = terrainMap.toArray();
		
		int width = terrainArray.length;
		int height = terrainArray[0].length;
		
		TiledMap map = new TiledMap();
		MapLayers layers = map.getLayers();
		
		//Add Terrain Layer
		TiledMapTileLayer terrain = new TiledMapTileLayer(width, height, 16, 16);
		
		//Adding all the terrain cells
		for(int x = 0; x < width; ++x){
			for(int y = 0; y < height; ++y){
				Cell cell = new Cell();
				cell.setTile(new StaticTiledMapTile(AssetLoader.terrainTextureMap.get(terrainArray[x][y])));
				terrain.setCell(x, y, cell);
			}
		}
		
		layers.add(terrain);
		
		LocationMap locMap = new LocationMap(terrainArray);
		TownType locArr[][] = locMap.getTownMap();
		
		//Add locations Layer
		TiledMapTileLayer locations = new TiledMapTileLayer(width, height, 16, 16);
				
		//Adding all the location cells
		for(int x = 0; x < width; ++x){
			for(int y = 0; y < height; ++y){
				if(locArr[x][y] != null) {
					Cell cell = new Cell();
					cell.setTile(new StaticTiledMapTile(AssetLoader.townTextureMap.get(locArr[x][y])));
					locations.setCell(x, y, cell);
				}
			}
		}
				
		layers.add(locations);
		
		return map;
	}
	
}
