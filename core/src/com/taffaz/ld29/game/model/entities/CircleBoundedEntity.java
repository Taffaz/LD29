package com.taffaz.ld29.game.model.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

public abstract class CircleBoundedEntity extends MovingEntity{

	protected Circle bounds;
	protected float radius;

	public abstract float getRadius();

	public abstract void setRadius(float radius);
	
	public abstract void incRadius(float inc);
	
	public abstract void decRadius(float dec);

	@Override
	public void render(SpriteBatch batch) {
		//batch.draw(sprite, position.x, position.y, 4*sprite.getWidth(), 4*sprite.getHeight());
		batch.draw(sprite, position.x, position.y,bounds.radius, bounds.radius);
	}

	@Override
	public abstract void update(float delta);
	
	public Circle getBounds() {
		return bounds;
	}

	public abstract void setBounds(Vector2 position, float radius);

}
