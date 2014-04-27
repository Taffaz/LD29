package com.taffaz.ld29.game.controllers.ai;

import com.badlogic.gdx.math.Vector2;
import com.taffaz.ld29.game.model.entities.RectBoundedEntity;
import com.taffaz.ld29.game.utils.Constants;

public class SharkAI extends EnemyAI {

	protected final Vector2 POS1;
	protected final Vector2 POS2;
	protected Vector2 direction;
	RectBoundedEntity e;
	boolean pos;

	public SharkAI(Vector2 pos1, Vector2 pos2, RectBoundedEntity e) {
		this.POS1 = pos1;
		this.POS2 = pos2;
		this.direction = new Vector2(pos2.x - pos1.x, pos2.y - pos1.y).nor();
		this.e = e;
		pos = true;
	}

	@Override
	public void update(float delta){
		Vector2 temp = direction.cpy();
		Vector2 travelTo = null;
		Vector2 travelFrom = null;
		Vector2 travelDir = null;
		Vector2 currentDir = null;
		if(pos){
			
			travelFrom = POS1;
			travelTo = POS2;
			travelDir = new Vector2(travelTo.x - travelFrom.x, travelTo.y - travelFrom.y);
			currentDir = new Vector2(travelTo.x - e.getBounds().x, travelTo.y - e.getBounds().y);
			e.setVelocity(currentDir);
			
			e.getPosition().add(temp.scl(Constants.SHARK_SPEED*delta));
			
			if(travelDir.hasOppositeDirection(currentDir)){
				pos = false;
			}
		}else{
			
			travelFrom = POS2;
			travelTo = POS1;
			travelDir = new Vector2(travelTo.x - travelFrom.x, travelTo.y - travelFrom.y);
			currentDir = new Vector2(travelTo.x - e.getBounds().x, travelTo.y - e.getBounds().y);
			
			e.getPosition().add(temp.scl(-Constants.SHARK_SPEED*delta));

			if(travelDir.hasOppositeDirection(currentDir)){
				pos = true;
			}
		}
		
	}
}
