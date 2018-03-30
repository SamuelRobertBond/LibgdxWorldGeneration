package bond.generation.display;

import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.mygdx.game.AssetLoader;

import bond.generation.utils.TerrainType;

public class OverworldTiledMapGenerator {

	public static TiledMap generateOverworld(TerrainType[][] terrainMap){
		
		int width = terrainMap.length;
		int height = terrainMap[0].length;
		
		TiledMap map = new TiledMap();
		MapLayers layers = map.getLayers();
		
		TiledMapTileLayer terrain = new TiledMapTileLayer(width, height, 16, 16);
		
		//Adding all the cells
		for(int x = 0; x < width; ++x){
			for(int y = 0; y < height; ++y){
				Cell cell = new Cell();
				cell.setTile(new StaticTiledMapTile(AssetLoader.textureMap.get(terrainMap[x][y])));
				terrain.setCell(x, y, cell);
			}
		}
		
		layers.add(terrain);
		
		return map;
	}
	
}
