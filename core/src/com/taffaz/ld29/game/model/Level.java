package com.taffaz.ld29.game.model;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.taffaz.ld29.game.model.entities.Chest;
import com.taffaz.ld29.game.model.entities.CircleBoundedEntity;
import com.taffaz.ld29.game.model.entities.MovingEntity;
import com.taffaz.ld29.game.model.entities.Player;
import com.taffaz.ld29.game.model.entities.RectBoundedEntity;
import com.taffaz.ld29.game.model.entities.Terrain;
import com.taffaz.ld29.game.utils.AudioManager;

public class Level {

	public Player player;
	public Chest chest;
	private Array<Terrain> terrain;
	private Array<RectBoundedEntity> recEnemies;
	private Array<CircleBoundedEntity> circEnemies;

	private long startTime;
	private int timer;
	public boolean lose, won, lvlWon;

	public int getTimer() {
		return timer;
	}

	public void setTimer(int timer) {
		this.timer = timer;
	}

	ShapeRenderer debugRenderer;

	public Level(Player player, Chest chest, int timer) {

		terrain = new Array<Terrain>();
		recEnemies = new Array<RectBoundedEntity>();
		circEnemies = new Array<CircleBoundedEntity>();

		this.player = player;
		this.chest = chest;
		this.timer = timer;
		this.startTime = System.currentTimeMillis();
		this.lose = false;
		this.won = false;
		this.lvlWon = false;

		debugRenderer = new ShapeRenderer();
	}

	public void addRectEnemy(RectBoundedEntity e) {
		recEnemies.add(e);
	}

	public void addCircEnemy(CircleBoundedEntity e) {
		circEnemies.add(e);
	}

	public void addTerrain(Terrain t) {
		terrain.add(t);
	}

	public void render(SpriteBatch batch) {
		player.render(batch);
		for (MovingEntity e : recEnemies) {
			e.render(batch);
		}
		for (CircleBoundedEntity e : circEnemies) {
			e.render(batch);
		}
		for (Terrain t : terrain) {
			t.render(batch);
		}
		chest.render(batch);

	}

	public void debugRender() {
		debugRenderer.begin(ShapeType.Line);
		// Player
		debugRenderer.rect(player.getBounds().x, player.getBounds().y,
				player.getBounds().width, player.getBounds().height);
		for (RectBoundedEntity e : recEnemies) {
			debugRenderer.rect(e.getBounds().x, e.getBounds().y,
					e.getBounds().width, e.getBounds().height);
		}
		for (CircleBoundedEntity e : circEnemies) {
			debugRenderer.circle(e.getBounds().x, e.getBounds().y,
					e.getBounds().radius);
		}
		for (Terrain t : terrain) {
			debugRenderer.rect(t.getBounds().x, t.getBounds().y,
					t.getBounds().width, t.getBounds().height);
		}

		debugRenderer.end();
	}

	public void update(float delta) {

		if (System.currentTimeMillis() - startTime < timer) {
			player.update(delta);
			for (RectBoundedEntity e : recEnemies) {
				e.update(delta);
			}
			for (CircleBoundedEntity e : circEnemies) {
				e.update(delta);
			}
			for (Terrain t : terrain) {
				t.update(delta);
			}
			chest.update(delta);
		} else  {
			// LOSE SCREEN
			lose = true;
		}
		
		if (player.getHealth() < 1) {
			lose = true;
		}
	}

	public long getTimeLeft() {
		if (timer - (System.currentTimeMillis() - startTime) < 0)
			return 0;
		else
			return timer - (System.currentTimeMillis() - startTime);
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public void updatePlayerPosition(float delta) {
		player.updatePosition(delta);
	}

	public void checkCollisions() {
		boolean collisionDetected = false;

		// CHEST COLLISION

		if (player.getBounds().overlaps(chest.getBounds()) && !player.onChest) {
			chest.getPosition().y = -200;
			player.incScore(chest.getScore());
			this.lvlWon = true;
			AudioManager.chest.play();

		}

		// ENEMY COLLISION

		for (RectBoundedEntity e : recEnemies) {
			if (player.getBounds().overlaps(e.getBounds())) {
				
				Rectangle intersect = new Rectangle();
				
				Intersector.intersectRectangles(player.getBounds(), e.getBounds(), intersect);
				
				Vector2 playerCent = new Vector2();
				Vector2 interCent = new Vector2();
						
				player.getBounds().getCenter(playerCent);
				
				intersect.getCenter(interCent);
				
				Vector2 bounceVec = new Vector2(playerCent.x - interCent.x, playerCent.y - interCent.y);

				player.setVelocity(bounceVec.scl(2.5f));
				player.setStunned();

			}
		}

		for (CircleBoundedEntity e : circEnemies) {

			if (Intersector.overlaps(e.getBounds(), player.getBounds())) {
				Vector2 bounce = player.getVelocity().cpy();
				bounce.nor().scl(-100f);

				player.setVelocity(bounce);
				player.setStunned();
			}

		}

		// TERRAIN COLLISION

		for (Terrain t : terrain) {
			if (player.getBounds().overlaps(t.getBounds())) {

				// PLAYER ABOVE
				
				

				if (player.getBounds().getY() < (t.getBounds().getY() + t
						.getBounds().height)
						&& player.getBounds().getY() > (t.getBounds().getY() + (t
								.getBounds().height - 5))) {
					player.getVelocity().y = 0;
					// player.position.y = t.getBounds().getY() +
					// t.getBounds().height - 1;
					player.setCollidingYD(true);
				}

				// PLAYER BELOW

				if ((player.getBounds().getY() + player.getBounds().height) > (t
						.getBounds().getY())
						&& (player.getBounds().getY() + player.getBounds().height) < (t
								.getBounds().getY() + 5)) {
					player.getVelocity().y = 0;
					player.getPosition().y = t.getBounds().getY()
							- (player.getBounds().getHeight());
					player.setCollidingYU(true);
				}

				// PLAYER LEFT

				if ((player.getBounds().getX() + player.getBounds().getWidth()) > t
						.getBounds().getX()
						&& (player.getBounds().getX() + player.getBounds()
								.getWidth()) < (t.getBounds().getX() + 5)
						&& player.getBounds().getX()
								+ player.getBounds().getWidth() < t.getBounds()
								.getX() + 5) {
					player.getVelocity().x = 0;
					player.setCollidingXR(true);
				}

				// PLAYER RIGHT

				if (player.getBounds().getX() < t.getBounds().getX()
						+ t.getBounds().getWidth()
						&& player.getBounds().getX() > (t.getBounds().getX()
								+ t.getBounds().getWidth() - 5)
						&& player.getBounds().getX() < t.getBounds().getX()
								+ t.getBounds().getWidth() - 1) {
					player.getVelocity().x = 0;
					player.setCollidingXL(true);
				}

				collisionDetected = true;
				// Gdx.app.log(LD29Game.LOG, "Collision");
			}
		}

		if (!collisionDetected) {
			player.setCollidingXR(false);
			player.setCollidingYU(false);
			player.setCollidingXL(false);
			player.setCollidingYD(false);
		}

	}

	public Chest getChest() {
		return chest;
	}

	public void setChest(Chest chest) {
		this.chest = chest;
	}

}
