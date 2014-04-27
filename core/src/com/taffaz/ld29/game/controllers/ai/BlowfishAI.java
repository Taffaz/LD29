package com.taffaz.ld29.game.controllers.ai;

import com.taffaz.ld29.game.model.entities.CircleBoundedEntity;

public class BlowfishAI extends EnemyAI{
	
	private CircleBoundedEntity e;
	private long waitTime;
	private boolean expanded;

	public BlowfishAI(CircleBoundedEntity e) {
		this.e = e;
		this.waitTime = System.currentTimeMillis();
	}

	@Override
	public void update(float delta) {

		if(System.currentTimeMillis() - waitTime > 1000){
			if(!expanded){
			e.incRadius(32);
			waitTime = System.currentTimeMillis();
			}else{
			e.decRadius(32);
			waitTime = System.currentTimeMillis() + 2000;
			}
			
			expanded = !expanded;
		}
		
		
	}

}
