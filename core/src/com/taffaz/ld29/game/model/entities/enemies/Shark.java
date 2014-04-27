package com.taffaz.ld29.game.model.entities.enemies;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.taffaz.ld29.game.controllers.ai.EnemyAI;
import com.taffaz.ld29.game.controllers.ai.SharkAI;
import com.taffaz.ld29.game.model.entities.RectBoundedEntity;

public class Shark extends RectBoundedEntity {
	
	EnemyAI ai;
	
	public Shark(int x, int y, Texture sprite, Vector2 pos2) {
		this.position = new Vector2(x,y);
		this.velocity = new Vector2();
		this.sprite = sprite;
		this.bounds = new Rectangle(x, y, sprite.getWidth()*2, sprite.getHeight()*2);
		this.ai = new SharkAI(new Vector2(x,y), pos2, this);
	}

	@Override
	public void update(float delta) {
		ai.update(delta);
		setBounds(position);
		
	}

}
