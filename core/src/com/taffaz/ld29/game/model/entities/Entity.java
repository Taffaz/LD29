package com.taffaz.ld29.game.model.entities;

import com.badlogic.gdx.math.Vector2;

public abstract class Entity {

	protected Vector2 position;

	public Vector2 getPosition() {
		return position;
	}

	public void setPosition(Vector2 position) {
		this.position = position;
	}
	
	
	

}
