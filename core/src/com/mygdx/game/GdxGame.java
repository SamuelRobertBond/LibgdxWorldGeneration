package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;

import bond.generation.display.OverworldTiledMapGenerator;
import bond.generation.terraingen.TerrainMap;

public class GdxGame extends ApplicationAdapter {
	
	TiledMap map;
	OrthogonalTiledMapRenderer renderer;
	OrthographicCamera cam;
	float speed = 3;
	
	@Override
	public void create () {
		AssetLoader.load();
		
		cam = new OrthographicCamera(AssetLoader.V_WIDTH, AssetLoader.V_HEIGHT);
		map = OverworldTiledMapGenerator.generateOverworld();
		renderer = new OrthogonalTiledMapRenderer(map);
		renderer.setView(cam);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		debugCamera();
		cam.update();
		renderer.setView(cam);
		renderer.render();
		
	}
	
	private void debugCamera() {
		
		if(Gdx.input.isKeyPressed(Keys.W)){
			cam.translate(new Vector2(0, -speed));
		}
		
		if(Gdx.input.isKeyPressed(Keys.S)){
			cam.translate(new Vector2(0, speed));
		}
		
		if(Gdx.input.isKeyPressed(Keys.A)){
			cam.translate(new Vector2(-speed, 0));
		}

		if(Gdx.input.isKeyPressed(Keys.D)){
			cam.translate(new Vector2(speed, 0));
		}

		if(Gdx.input.isKeyPressed(Keys.Z)){
			cam.zoom += speed * .05;
		}

		if(Gdx.input.isKeyPressed(Keys.X)){
			cam.zoom -= speed * .05;
		}
		
		
		
	}

	@Override
	public void dispose () {
		
	}
}
