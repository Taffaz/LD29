package com.taffaz.ld29.game.model.entities.enemies;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.taffaz.ld29.game.controllers.ai.BlowfishAI;
import com.taffaz.ld29.game.controllers.ai.EnemyAI;
import com.taffaz.ld29.game.model.entities.CircleBoundedEntity;

public class Blowfish extends CircleBoundedEntity{

	EnemyAI ai;
	
	public Blowfish(int x, int y, Texture sprite, float radius) {
		this.position = new Vector2(x,y);
		this.velocity = new Vector2();
		this.sprite = sprite;
		this.radius = radius;
		this.bounds = new Circle(x+radius/2, y+radius/2, radius/2);
		this.ai = new BlowfishAI(this);
	}
	
	@Override
	public void render(SpriteBatch batch) {
		//batch.draw(sprite, position.x, position.y, 4*sprite.getWidth(), 4*sprite.getHeight());
		batch.draw(sprite, position.x, position.y,radius, radius);
	}
	
	@Override
	public void update(float delta) {
		ai.update(delta);
		
		
	}
	
	public float getRadius() {
		return radius;
	}

	public void setRadius(float radius) {
		this.radius = radius;
		setBounds(position, radius);
	}
	
	public void incRadius(float inc) {
		Vector2 oldCentre = new Vector2(position.x + radius/2, position.y + radius/2);
		this.radius += inc;
		position.set(oldCentre.x - radius/2, oldCentre.y - radius/2);
		setBounds(position, radius);
	}
	
	public void decRadius(float dec) {
		Vector2 oldCentre = new Vector2(position.x + radius/2, position.y + radius/2);
		this.radius -= dec;
		position.set(oldCentre.x - radius/2, oldCentre.y - radius/2);
		setBounds(position, radius);
	}

	@Override
	public void setBounds(Vector2 position, float radius) {
		this.bounds.set(position.x+radius/2, position.y+radius/2, radius/2);
		
	}
	
}
