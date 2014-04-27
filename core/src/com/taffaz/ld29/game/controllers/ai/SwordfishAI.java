package com.taffaz.ld29.game.controllers.ai;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.taffaz.ld29.game.LD29Game;
import com.taffaz.ld29.game.model.entities.RectBoundedEntity;
import com.taffaz.ld29.game.utils.Constants;

public class SwordfishAI extends EnemyAI {

	protected final Vector2 POS1;
	protected final Vector2 POS2;
	protected Vector2 direction;
	RectBoundedEntity e;
	boolean pos;
	boolean charge;
	private long waitTime;

	public SwordfishAI(Vector2 pos1, Vector2 pos2, RectBoundedEntity e) {
		this.POS1 = pos1;
		this.POS2 = pos2;
		this.direction = new Vector2(pos2.x - pos1.x, pos2.y - pos1.y).nor();
		this.e = e;
		pos = true;
		charge = false;
		waitTime = System.currentTimeMillis() - 1001;
	}

	@Override
	public void update(float delta) {

		Vector2 temp = direction.cpy();
		Vector2 travelTo = null;
		Vector2 travelFrom = null;
		Vector2 travelDir = null;
		Vector2 currentDir = null;

		if (pos) {
			if (System.currentTimeMillis() - waitTime > 1000) {
				travelFrom = POS1;
				travelTo = POS2;
				travelDir = new Vector2(travelTo.x - travelFrom.x, travelTo.y
						- travelFrom.y);
				currentDir = new Vector2(travelTo.x - e.getBounds().x,
						travelTo.y - e.getBounds().y);

				if (!charge) {

					Vector2 chargeVel = new Vector2(Constants.SWORDFISH_SPEED
							* direction.x, Constants.SWORDFISH_SPEED
							* direction.y);
					
					e.getVelocity().set(chargeVel);

					charge = true;
				} else {
					if (Math.abs(e.getVelocity().x) > 100) {
						e.getVelocity().add(temp.scl(-Constants.DRAG));
					}
				}
				if (travelDir.hasOppositeDirection(currentDir)) {
					pos = false;
					charge = false;
					waitTime = System.currentTimeMillis();
					direction.scl(-1);
					e.getVelocity().set(0, 0);
				}
			}
		} else {
			if (System.currentTimeMillis() - waitTime > 1000) {
				travelFrom = POS2;
				travelTo = POS1;
				travelDir = new Vector2(travelTo.x - travelFrom.x, travelTo.y
						- travelFrom.y);
				currentDir = new Vector2(travelTo.x - e.getBounds().x,
						travelTo.y - e.getBounds().y);
				if (!charge) {
					Vector2 chargeVel = new Vector2(Constants.SWORDFISH_SPEED
							* direction.x, Constants.SWORDFISH_SPEED
							* direction.y);

					Gdx.app.log(LD29Game.LOG, "vel: " + chargeVel);
					e.getVelocity().set(chargeVel);

					charge = true;
				} else {
					if (Math.abs(e.getVelocity().x) > 100) {
						e.getVelocity().add(temp.scl(-Constants.DRAG));
					}
				}

				if (travelDir.hasOppositeDirection(currentDir)) {
					pos = true;
					charge = false;
					waitTime = System.currentTimeMillis();
					direction.scl(-1);
					e.getVelocity().set(0, 0);
				}
			}
		}

	}

}
