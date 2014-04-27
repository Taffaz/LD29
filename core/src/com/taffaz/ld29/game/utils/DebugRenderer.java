package com.taffaz.ld29.game.utils;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.utils.Array;
import com.taffaz.ld29.game.model.entities.MovingEntity;

public class DebugRenderer {
	
	private ShapeRenderer debugRenderer;
	private Array<MovingEntity> entities;

	public DebugRenderer() {
		this.entities = new Array<MovingEntity>();
		this.debugRenderer = new ShapeRenderer();
	}
	
	public void addEntity(MovingEntity e){
		entities.add(e);
	}
	
	public void removeEntity(MovingEntity e){
		entities.removeValue(e, true);
	}
	
	public void render(SpriteBatch batch){
		debugRenderer.begin(ShapeType.Line);
		for(int i = 0; i< entities.size; i++){
			MovingEntity temp = entities.get(i);
			debugRenderer.rect(temp.getPosition().x, temp.getPosition().y, temp.getSprite().getWidth(), temp.getSprite().getHeight());
		}
		debugRenderer.end();
	}

}
