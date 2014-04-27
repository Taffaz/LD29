package com.taffaz.ld29.game.model.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Terrain extends Entity {
	
	private Rectangle bounds;
	private Texture sprite;
	private int width, height;

	public Terrain(int x, int y, Texture sprite, int width, int height) {
		this.position = new Vector2(x, y);
		this.sprite = sprite;
		this.width = width;
		this.height = height;
		this.bounds = new Rectangle(x, y, width, height);
		
	}
	
	public void render(SpriteBatch batch){
		batch.draw(sprite, position.x, position.y, width, height);
	}
	
	public void update(float delta){
		
	}

	public Rectangle getBounds() {
		return bounds;
	}

	public void setBounds(Rectangle bounds) {
		this.bounds = bounds;
	}

	public Texture getSprite() {
		return sprite;
	}

	public void setSprite(Texture sprite) {
		this.sprite = sprite;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	

}
