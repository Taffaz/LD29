package com.taffaz.ld29.game.model.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.taffaz.ld29.game.controllers.ai.EnemyAI;

public abstract class RectBoundedEntity extends MovingEntity{

	protected Rectangle bounds;
	EnemyAI ai;
	public boolean flip;

	@Override
	public void render(SpriteBatch batch) {
		if(velocity.x < 0){
			//batch.draw(texture, x, y, width, height, srcX, srcY, srcWidth, srcHeight, flipX, flipY)
			batch.draw(sprite, position.x, position.y, sprite.getWidth(), sprite.getHeight(),0,0,sprite.getWidth(), sprite.getHeight(),true,false);
			flip = true;
		} else if (velocity.x > 0) {
			batch.draw(sprite, position.x, position.y, sprite.getWidth(), sprite.getHeight());
			flip = false;
		} else {
			batch.draw(sprite, position.x, position.y, sprite.getWidth(), sprite.getHeight(),0,0,sprite.getWidth(), sprite.getHeight(),flip,false);
		}
		
	}

	@Override
	public abstract void update(float delta);
	
	public Rectangle getBounds() {
		return bounds;
	}

	public void setBounds(Vector2 position) {
		this.bounds.set(position.x, position.y, sprite.getWidth(), sprite.getHeight());
	}

}
