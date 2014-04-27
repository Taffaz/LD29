package com.taffaz.ld29.game.model.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class Bubble extends MovingEntity{
	
	Texture bubbleTex;

	public Bubble(int x, int y) {
		this.position = new Vector2(x,y);
		this.velocity = new Vector2(0,50f);
		
		bubbleTex = new Texture("data/textures/bubble.png");
	}

	@Override
	public void render(SpriteBatch batch) {
		batch.draw(bubbleTex, position.x, position.y);
		
	}

	@Override
	public void update(float delta) {
		position.add(0, velocity.y * delta);
		
		if(position.y > Gdx.graphics.getHeight()){
			position.x = MathUtils.random(0, Gdx.graphics.getWidth());
			position.y = 0 - bubbleTex.getHeight();
		}
		
	}

}
