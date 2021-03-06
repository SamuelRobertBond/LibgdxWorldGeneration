package com.mygdx.game;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import bond.generation.overworld.locgen.TownType;
import bond.generation.overworld.locgen.roadgeneration.RoadType;
import bond.generation.overworld.terraingen.TerrainType;

public class AssetLoader {
	
	public static int V_WIDTH = 160 * 5;
	public static int V_HEIGHT = 90 * 5;
	
	public static HashMap<TerrainType, TextureRegion> terrainTextureMap;
	public static HashMap<TownType, TextureRegion> townTextureMap;
	public static HashMap<RoadType, TextureRegion> roadTextureMap;
	
	public static void load(){
		
		//Splitting the textures
		TextureRegion terrainTexture = new TextureRegion(new Texture(Gdx.files.internal("overworld.png")));
		TextureRegion terrainTextures[][] = terrainTexture.split(16, 16);
		
		terrainTextureMap = new HashMap<TerrainType, TextureRegion>();
		terrainTextureMap.put(TerrainType.PLAINS, terrainTextures[0][0]);
		terrainTextureMap.put(TerrainType.FOREST, terrainTextures[0][1]);
		terrainTextureMap.put(TerrainType.MOUNTAINS, terrainTextures[0][2]);
		terrainTextureMap.put(TerrainType.SWAMP, terrainTextures[0][3]);
		terrainTextureMap.put(TerrainType.DESERT, terrainTextures[0][4]);
		terrainTextureMap.put(TerrainType.ICE, terrainTextures[0][5]);
		terrainTextureMap.put(TerrainType.WATER, terrainTextures[0][6]);
		
		TextureRegion townTexture = new TextureRegion(new Texture(Gdx.files.internal("towns.png")));
		TextureRegion townTextures[][] = townTexture.split(16, 16);
		
		townTextureMap = new HashMap<TownType, TextureRegion>();
		townTextureMap.put(TownType.CITY, townTextures[0][0]);
		
		//Roads
		TextureRegion roadTexture = new TextureRegion(new Texture(Gdx.files.internal("roads.png")));
		TextureRegion roadTextures[][] = roadTexture.split(16, 16);
		
		roadTextureMap = new HashMap<RoadType, TextureRegion>();
		roadTextureMap.put(RoadType.HORIZONTAL, roadTextures[0][0]);
		roadTextureMap.put(RoadType.VERTICLE, roadTextures[0][1]);
		
		roadTextureMap.put(RoadType.LEFT_UP, new TextureRegion(roadTextures[0][2]));
		roadTextures[0][2].flip(false, true);
		roadTextureMap.put(RoadType.LEFT_DOWN, new TextureRegion(roadTextures[0][2]));
		roadTextures[0][2].flip(true, false);
		roadTextureMap.put(RoadType.RIGHT_DOWN, new TextureRegion(roadTextures[0][2]));
		roadTextures[0][2].flip(false, true);
		roadTextureMap.put(RoadType.RIGHT_UP, new TextureRegion(roadTextures[0][2]));
		
		roadTextureMap.put(RoadType.ALL, roadTextures[0][3]);
		
		roadTextureMap.put(RoadType.HORIZONTAL_UP, new TextureRegion(roadTextures[0][4]));
		
		roadTextures[0][4].flip(false, true);
		roadTextureMap.put(RoadType.HORIZONTAL_DOWN, new TextureRegion(roadTextures[0][4]));
		
		roadTextureMap.put(RoadType.VERTICAL_RIGHT, new TextureRegion(roadTextures[0][5]));
		
		roadTextures[0][5].flip(true, false);
		roadTextureMap.put(RoadType.VERTICAL_LEFT, new TextureRegion(roadTextures[0][5]));
		
		Gdx.app.log("AssetLoader", "All Assets Loaded");
		
	}
}
