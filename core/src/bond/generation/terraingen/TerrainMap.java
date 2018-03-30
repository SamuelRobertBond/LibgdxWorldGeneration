package bond.generation.terraingen;

import java.util.HashMap;
import java.util.LinkedList;

import bond.generation.utils.Quadrant;
import bond.generation.utils.TerrainType;
import bond.generation.utils.Utils;

public class TerrainMap {

	private TerrainType map[][];
	private int width;
	private int height;
	private final int MINER_COUNT = 16;
	
	//Generation Variables
	private final float SPAWN_RATE = 0.3f;
	
	private final float PLAINS_SPAWN_RATE = 0.25f;
	private final float FOREST_SPAWN_RATE = 0.20f + PLAINS_SPAWN_RATE;
	private final float DESERT_SPAWN_RATE = 0.20f + FOREST_SPAWN_RATE;
	private final float MOUNTAINS_SPAWN_RATE = 0.15f + DESERT_SPAWN_RATE;
	private final float SWAMP_SPAWN_RATE = 0.10f + MOUNTAINS_SPAWN_RATE;
	private final float ICE_SPAWN_RATE = 0.10f + SWAMP_SPAWN_RATE;
	
	private HashMap<Float, TerrainType> terrain_odds;
	private HashMap<Float, TerrainType> used_terrain;
	
	public TerrainMap(int size) {
		
		this.width = size;
		this.height = size;
		
		map = new TerrainType[width][height];
		
		terrain_odds = new HashMap<Float, TerrainType>();
		used_terrain = new HashMap<Float, TerrainType>();
		
		terrain_odds.put(PLAINS_SPAWN_RATE, TerrainType.PLAINS);
		terrain_odds.put(FOREST_SPAWN_RATE, TerrainType.FOREST);
		terrain_odds.put(DESERT_SPAWN_RATE, TerrainType.DESERT);
		terrain_odds.put(MOUNTAINS_SPAWN_RATE, TerrainType.MOUNTAINS);
		terrain_odds.put(SWAMP_SPAWN_RATE, TerrainType.SWAMP);
		terrain_odds.put(ICE_SPAWN_RATE, TerrainType.ICE);
		
		
	}
	
	public void generate(){
		
		LinkedList<Miner> miners = generateInitMiners(MINER_COUNT);
		
		while(!miners.isEmpty()){
			for(int i = 0; i < miners.size(); ++i){
				
				//Retrieve the miner
				Miner miner = miners.get(i);
				
				//Move the miner
				//Check if the miner was able to make a move
				boolean success = miner.move();
				if(!success){
					miners.remove(i);
					--i;
				}else if(spawn()){
					//Check if  new miner needs to be spawned
					miners.add(generateMiner(miner.getX(), miner.getY(), miner.getType()));
				}
				
			}
		}
		
		for(int x = 0; x < width; ++x){
			for(int y = 0; y < height; ++y){
				if(map[x][y] == null){
					map[x][y] = TerrainType.WATER;
				}
			}
		}
		
	}
	
	private boolean spawn() {
		return Utils.getRandomFloat() < SPAWN_RATE;
	}

	public void printTerrainMap(){
		for(int x = 0; x < width; ++x){
			for(int y = 0; y < height; ++y){
				if(map[x][y] == null){
					System.out.print(0);
				}else{
					System.out.print(map[x][y].toString().substring(0, 1));
				}
			}
			System.out.println();
		}
	}
	
	private LinkedList<Miner> generateInitMiners(int minerCount){
		
		LinkedList<Miner> miners = new LinkedList<Miner>();
		Quadrant[] points = getStartingPoints(MINER_COUNT);
		
		for(int i = 0; i < minerCount; ++i){
			
			TerrainType type = null;
			float gen = Utils.getRandomFloat();
			
			//First Pass Terrain Gen
			for(Float weight : terrain_odds.keySet()){
				if(weight <= gen){
					type = terrain_odds.get(weight);
					used_terrain.put(weight, type);
				}
			}
			
			//If the random weights gets nothing, sequential will take over
			if(type == null){
				for(Float weight : terrain_odds.keySet()){
					type = terrain_odds.remove(weight);
					used_terrain.put(weight, type);
					break;
				}
			}
			
			//If no terrain is left, reinstantiate terrain
			if(terrain_odds.isEmpty()){
				terrain_odds = used_terrain;
				used_terrain = new HashMap<Float, TerrainType>();
			}
			
			miners.add( new Miner(points[i].centerX, points[i].centerY, map, type));
		}
		
		return miners;
	}
	
	private Miner generateMiner(int x, int y, TerrainType type){
		return new Miner(x, y, map, type);
	}
	
	private Quadrant[] getStartingPoints(int minerCount){
		
		Quadrant[] points = new Quadrant[minerCount];
		int div = (int)Math.sqrt(minerCount);
		
		int count = 0;
		
		for(int x = 0; x < div; ++x){
			for(int y = 0; y < div; ++y){
				points[count] = new Quadrant( (int)(width * ((float)x / (float)div)), (int)(height * ((float)y / (float)div)), width/div, height/div);
				++count;
			}
		}
		
		return points;
		
	}
	
	public TerrainType[][] toArray(){
		return map;
	}
	
}
