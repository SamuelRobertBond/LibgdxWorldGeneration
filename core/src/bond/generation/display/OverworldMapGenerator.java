package bond.generation.display;

import java.util.LinkedList;
import java.util.Queue;

import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.AssetLoader;

import bond.ai.pathfinding.AStarMap;
import bond.ai.pathfinding.AStarPathfinder;
import bond.ai.pathfinding.Connection;
import bond.ai.pathfinding.Node;
import bond.ai.pathfinding.PathFindingUtils;
import bond.ai.pathfinding.PathfindingResult;
import bond.generation.overworld.OverworldTile;
import bond.generation.overworld.OverworldMap;
import bond.generation.overworld.locgen.LocationMap;
import bond.generation.overworld.locgen.TownType;
import bond.generation.overworld.locgen.roadgeneration.RoadType;
import bond.generation.overworld.terraingen.TerrainMap;
import bond.generation.overworld.terraingen.TerrainType;
import bond.generation.utils.Utils;

public class OverworldMapGenerator {

	private static final int WORLD_SIZE = 30;


	public static TiledMap generateOverworld(OverworldMap overworld){
		
		OverworldTile[][] overworldTileMap = overworld.getMap();
		
		int width = overworldTileMap.length;
		int height = overworldTileMap[0].length;
		
		TiledMap map = new TiledMap();
		MapLayers layers = map.getLayers();
		
		//Add Terrain Layer
		TiledMapTileLayer terrain = new TiledMapTileLayer(width, height, 16, 16);
		TiledMapTileLayer roads = new TiledMapTileLayer(width, height, 16, 16);
		TiledMapTileLayer locations = new TiledMapTileLayer(width, height, 16, 16);
		
		//Adding all the location cells
		for(int x = 0; x < width; ++x){
			for(int y = 0; y < height; ++y){
				
				//Terrain settings
				if(overworldTileMap[x][y].terrainType != null) {
					Cell cell = new Cell();
					cell.setTile(new StaticTiledMapTile(AssetLoader.terrainTextureMap.get(overworldTileMap[x][y].terrainType)));
					terrain.setCell(x, y, cell);
				}
				
				//Location setting
				if(overworldTileMap[x][y].locType != null) {
					Cell cell = new Cell();
					cell.setTile(new StaticTiledMapTile(AssetLoader.townTextureMap.get(overworldTileMap[x][y].locType)));
					locations.setCell(x, y, cell);
				}
				
				//Roads setting
				if(overworldTileMap[x][y].roadType != null) {
					Cell cell = new Cell();
					cell.setTile(new StaticTiledMapTile(AssetLoader.roadTextureMap.get(overworldTileMap[x][y].roadType)));
					roads.setCell(x, y, cell);
				}
				
			}
		}
				
		layers.add(terrain);
		layers.add(roads);
		layers.add(locations);
		
		return map;
	}
	
	
	private static RoadType[][] getRoadsArray(TerrainType[][] terrainArray, TownType[][] locArr) {
		
		//Init Path Finder
		AStarPathfinder pathfinder = new AStarPathfinder(new AStarMap(terrainArray));
		Queue<Connection> connections = generateLocationConnections(locArr);
		Queue<PathfindingResult> paths = new LinkedList<PathfindingResult>();
				
		while(!connections.isEmpty()) {
			Connection c = connections.poll();
			paths.add(PathFindingUtils.getPath(new Vector2(c.x_1, c.y_1), new Vector2(c.x_2, c.y_2), pathfinder));
		}
		
		int roads[][] = new int[terrainArray.length][terrainArray[0].length];
		
		while(!paths.isEmpty()) {
			PathfindingResult result = paths.poll();
			if(result.pathFound) {
				while(!result.path.isEmpty()) {
					Node node = result.path.poll();
					roads[node.x][node.y] = 1;
				}
			}
			
		}
		
		for(int x = 0; x < locArr.length; ++x) {
			for(int y = 0; y < locArr[0].length; ++y) {
				if(locArr[x][y] != null) {
					roads[x][y] = 2;
				}
			}
		}
		
		return setRoadsTypes(roads);
		
	}


	private static RoadType[][] setRoadsTypes(int[][] roadsArray) {
		
		int width = roadsArray.length;
		int height = roadsArray[0].length;
		
		RoadType roads[][] = new RoadType[width][height];
		
		for(int x = 0; x < width; ++x) {
			for(int y = 0; y < height; ++y) {
				if(roadsArray[x][y] == 1) {
					roads[x][y] = getRoadType(x, y, roadsArray);
				}
			}
		}
		
		return roads;
		
	}


	public static OverworldMap getOverworldTypeMap() {
		
		//Generate Terrain Map
		TerrainMap terrainMap = new TerrainMap(WORLD_SIZE);
		terrainMap.generate();
		TerrainType terrainArray[][] = terrainMap.toArray();
		
		LocationMap locMap = new LocationMap(terrainArray);
		TownType locArr[][] = locMap.getTownMap();
		
		RoadType[][] roadArray = getRoadsArray(terrainArray, locArr);
		
		return new OverworldMap(terrainArray, locArr, roadArray);
		
	}
	
	
	
	private static RoadType getRoadType(int x, int y, int roads[][]) {
		
		int width = roads.length;
		int height = roads[0].length;
		
		boolean up = false;
		if(y + 1 < height && roads[x][y + 1] != 0) {
			up = true;
		}
		
		boolean down = false;
		if(y - 1 > 0 && roads[x][y - 1] != 0) {
			down = true;
		}
		
		boolean left = false;
		if(x > 0 && roads[x - 1][y ] != 0) {
			left = true;
		}
		
		boolean right = false;
		if(x + 1 < width && roads[x + 1][y ] != 0) {
			right = true;
		}
		//-------------------------------------------
		
		if(up && down && left && right) {
			return RoadType.ALL;
		}
		
		if(up || down) {
			
			if(up && down) {
				
				if(!left && !right) {
					return RoadType.VERTICLE;
				}
				
				if(left) {
					return RoadType.VERTICAL_LEFT;
				}else if(right){
					return RoadType.VERTICAL_RIGHT;
				}
				
			}
			
			if(up) {
				
				if(left && !right) {
					return RoadType.LEFT_UP;
				}else if(right && !left) {
					return RoadType.RIGHT_UP;
				}
				
			}else if(down){
				if(left && !right) {
					return RoadType.LEFT_DOWN;
				}else if(right && !left) {
					return RoadType.RIGHT_DOWN;
				}
			}
			
		}
		
		if(left && right) {
			
			if(up) {
				return RoadType.HORIZONTAL_UP;
			}
			
			if(down) {
				return RoadType.HORIZONTAL_DOWN;
			}
			
			return RoadType.HORIZONTAL;
		}
		
		if(up || down) {
			return RoadType.VERTICLE;
		}else if(left || right) {
			return RoadType.HORIZONTAL;
		}
		
		return RoadType.ALL;
		
	}


	private static Queue<Connection> generateLocationConnections(TownType locArray[][]) {
		
		//Get all the locations world vectors
		LinkedList<Vector2> locs = new LinkedList<Vector2>();
		
		for(int x = 0; x < locArray.length; ++x) {
			for(int y = 0; y < locArray[0].length; ++y) {
				if(locArray[x][y] != null) {
					locs.offer(new Vector2(x, y));
				}
			}
		}
		
		//Generate random connections
		Queue<Connection> connections = new LinkedList<Connection>();
		
		while(locs.size() > 1) {
			
			Vector2 src = locs.get(Utils.getRandomInt(locs.size()));
			Vector2 dst = locs.get(Utils.getRandomInt(locs.size()));
			
			if(src != dst) {
				connections.offer(new Connection((int)src.x, (int)src.y, (int)dst.x, (int)dst.y));
				locs.remove(src);
			}
			
		}
		
		return connections;
	}
	
	
	
}
