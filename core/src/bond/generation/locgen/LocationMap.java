package bond.generation.locgen;

import bond.generation.locgen.roadgeneration.RoadMap;
import bond.generation.terraingen.TerrainType;
import bond.generation.utils.Quadrant;
import bond.generation.utils.Utils;

public class LocationMap {

	private TownType townMap[][];
	private int roadMap[][];
	
	private final int LOCATION_COUNT = 10;
	private final float GENERATION_CHANCE = .80f;
	
	public LocationMap(TerrainType[][] terrainArray) {
		
		Quadrant quadrants[] = Utils.createQuadrants(LOCATION_COUNT, terrainArray.length, terrainArray[0].length);
		LocationQuadrant locationQuadrants[] = new LocationQuadrant[quadrants.length];
		
		//Generate Locations
		for(int i = 0; i < locationQuadrants.length; ++i) {
			if(quadrants[i] != null) {
				locationQuadrants[i] = new LocationQuadrant(quadrants[i], terrainArray);
			}
		}
		
		townMap = new TownType[terrainArray.length][terrainArray[0].length];
		
		for(int i = 0; i < locationQuadrants.length; ++i) {
			if(locationQuadrants[i] != null && locationQuadrants[i].placed) {
				if(Utils.getRandomFloat() < GENERATION_CHANCE) {
					townMap[locationQuadrants[i].city_x][locationQuadrants[i].city_y] = TownType.CITY;
				}
			}
		}
		
		RoadMap map = new RoadMap(townMap);
		
		
	}
	
	public TownType[][] getTownMap() {
		return townMap;
	}
}
