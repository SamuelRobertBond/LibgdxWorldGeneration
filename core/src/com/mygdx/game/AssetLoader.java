package com.mygdx.game;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import bond.generation.utils.TerrainType;

public class AssetLoader {
	
	public static int V_WIDTH = 160 * 5;
	public static int V_HEIGHT = 90 * 5;
	
	public static HashMap<TerrainType, TextureRegion> textureMap;
	
	public static void load(){
		
		//Splitting the textures
		TextureRegion tex = new TextureRegion(new Texture(Gdx.files.internal("overworld.png")));
		TextureRegion textures[][] = tex.split(16, 16);
		
		textureMap = new HashMap<TerrainType, TextureRegion>();
		textureMap.put(TerrainType.PLAINS, textures[0][0]);
		textureMap.put(TerrainType.FOREST, textures[0][1]);
		textureMap.put(TerrainType.MOUNTAINS, textures[0][2]);
		textureMap.put(TerrainType.SWAMP, textures[0][3]);
		textureMap.put(TerrainType.DESERT, textures[0][4]);
		textureMap.put(TerrainType.ICE, textures[0][5]);
		textureMap.put(TerrainType.WATER, textures[0][6]);
		
		Gdx.app.log("AssetLoader", "All Assets Loaded");
		
	}
}
