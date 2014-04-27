package com.taffaz.ld29.game.model.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Chest extends MovingEntity{
	
	private Rectangle bounds;
	private int score;

	public Chest(int x, int y, Texture sprite, int score) {
		this.position = new Vector2(x,y);
		this.setScore(score);
		this.sprite = sprite;
		this.velocity = new Vector2();
		this.bounds = new Rectangle(x,y,sprite.getWidth(), sprite.getHeight());
	}

	@Override
	public void render(SpriteBatch batch) {
		batch.draw(sprite, position.x, position.y, sprite.getWidth(), sprite.getHeight());
		
	}

	@Override
	public void update(float delta) {
		setBounds(position);
		
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public Rectangle getBounds() {
		return bounds;
	}

	public void setBounds(Vector2 position) {
		this.bounds.set(position.x, position.y, sprite.getWidth(),
				sprite.getHeight());
	}

}
