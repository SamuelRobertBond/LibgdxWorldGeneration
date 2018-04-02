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

import bond.generation.display.OverworldMapGenerator;
import bond.generation.overworld.OverworldMap;
import bond.generation.overworld.terraingen.TerrainMap;
import bond.generation.roomgeneration.Room;

public class GdxGame extends ApplicationAdapter {
	
	OrthogonalTiledMapRenderer renderer;
	OrthographicCamera cam;
	float speed = 3;
	int x = 15;
	int y = 15;
	OverworldMap map;
	
	@Override
	public void create () {
		AssetLoader.load();
		
		cam = new OrthographicCamera(AssetLoader.V_WIDTH, AssetLoader.V_HEIGHT);
		
		map = OverworldMapGenerator.getOverworldTypeMap();
		Room start = map.getTile(15, 15).getRoom();
		
		renderer = new OrthogonalTiledMapRenderer(OverworldMapGenerator.generateOverworld(map));
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
		
		if(Gdx.input.isKeyPressed(Keys.SHIFT_LEFT)) {
			
			int x_offset = 0;
			int y_offset = 0;
			
			if(Gdx.input.isKeyJustPressed(Keys.W)){
				y_offset += 1;
			}
			
			if(Gdx.input.isKeyJustPressed(Keys.S)){
				y_offset -= 1;
			}
			
			if(Gdx.input.isKeyJustPressed(Keys.A)){
				x_offset -= 1;
			}

			if(Gdx.input.isKeyJustPressed(Keys.D)){
				x_offset += 1;
			}
			
			if(x_offset != 0 || y_offset != 0) {
				
				System.out.print("( " + x + ", " + y +") - > ");
				
				x += x_offset;
				y += y_offset;
				
				System.out.println("( " + x + ", " + y +")");
				
				Room room = map.getTile(x, y).getRoom();
				renderer.setMap(room.getMap());
				
			}
			
			
		} else{
			
			if(Gdx.input.isKeyPressed(Keys.W)){
				cam.translate(new Vector2(0, speed));
			}
			
			if(Gdx.input.isKeyPressed(Keys.S)){
				cam.translate(new Vector2(0, -speed));
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
		
		
		
	}

	@Override
	public void dispose () {
		
	}
}
