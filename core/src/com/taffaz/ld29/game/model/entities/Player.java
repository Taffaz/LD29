package com.taffaz.ld29.game.model.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.taffaz.ld29.game.LD29Game;
import com.taffaz.ld29.game.utils.AudioManager;
import com.taffaz.ld29.game.utils.Constants;

public class Player extends MovingEntity {

	private Rectangle bounds;
	private boolean collidingXL, collidingXR, collidingYU, collidingYD, stunned;
	private long stunnedTimer;
	private int health;
	private int score;
	public boolean onChest;
	
	private boolean spaceHeld, leftHeld, rightHeld;

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public Player(int x, int y, Texture sprite, int health) {
		this.position = new Vector2(x, y);
		this.sprite = sprite;
		this.velocity = new Vector2();
		this.moveSpeed = 75f;
		this.bounds = new Rectangle(x, y, sprite.getWidth()*2, sprite.getHeight()*2);
		this.collidingXL = false;
		this.collidingYU = false;
		this.collidingXR = false;
		this.collidingYD = false;
		this.stunned = false;
		this.health = health;
		this.score = 0;
		this.onChest = false;
		
		this.spaceHeld = false;
		this.leftHeld = false;
		this.rightHeld = false;
	}

	public void render(SpriteBatch batch) {
		if(velocity.x < 0){
			//batch.draw(texture, x, y, width, height, srcX, srcY, srcWidth, srcHeight, flipX, flipY)
			batch.draw(sprite, position.x, position.y, sprite.getWidth(), sprite.getHeight(),0,0,sprite.getWidth(), sprite.getHeight(),true,false);
		} else {
			batch.draw(sprite, position.x, position.y, sprite.getWidth(), sprite.getHeight());
		}
	}

	public void update(float delta) {
		
		if(health < 1){
			//Gdx.app.log(LD29Game.LOG, "DEAD!");
		}
		
		if (!collidingYD)
			velocity.y -= Constants.GRAVITY;

		if(!stunned){
		if ((Gdx.input.isKeyPressed(Keys.RIGHT) || Gdx.input.isKeyPressed(Keys.D)) && !collidingXR) {
			velocity.x = moveSpeed;
			if(!rightHeld)
				AudioManager.swim.play();
			rightHeld = true;
		}else{
			rightHeld = false;
		}
		if ((Gdx.input.isKeyPressed(Keys.LEFT) || Gdx.input.isKeyPressed(Keys.A)) && !collidingXL) {
			velocity.x = -moveSpeed;
			
			if(!leftHeld)
				AudioManager.swim.play();
			leftHeld = true;
		}else{
			leftHeld = false;
		}
		
		if (Gdx.input.isKeyPressed(Keys.M)) {
			position.x += 10;
			position.y += 10;
		}

		if (Gdx.input.isKeyPressed(Keys.SPACE)  || Gdx.input.isKeyPressed(Keys.W)  || Gdx.input.isKeyPressed(Keys.UP)) {
			
			if(!spaceHeld)
				AudioManager.swim.play();
			spaceHeld = true;
			
			if (collidingYD) {
				position.y += 1;
				velocity.y = moveSpeed;
			}
			if (collidingYU) {
					velocity.y = 0;
				}
				if (!collidingYD && !collidingYU) {
				velocity.y = moveSpeed;
			}

		}else{
			spaceHeld = false;
		}
		}else{
			if(System.currentTimeMillis() - stunnedTimer > 500){
				stunned = false;
			}
		}
		//Gdx.app.log(LD29Game.LOG, "YVel = " + velocity.y);

	}

	public boolean isStunned() {
		return stunned;
	}

	public void setStunned() {
		if(!stunned){
			health--;
			AudioManager.hit.play();
		}
		this.stunned = true;
		
		Gdx.app.log(LD29Game.LOG, "Health: " + health);
		stunnedTimer = System.currentTimeMillis();
	}

	public void updatePosition(float delta) {
		if (!collidingXR && !collidingXL) {
			getPosition().x += (velocity.x * delta);
		}

		if (collidingXR) {
			if (velocity.x < 0) {
				getPosition().x += (velocity.x * delta);
			} else {
				getPosition().x -=1;
				velocity.x = 0;
			}
		}
		
		if (collidingXL) {
			if (velocity.x > 0) {
				getPosition().x += (velocity.x * delta);
			} else {
				getPosition().x +=1;
				velocity.x = 0;
			}
		}

		if (!collidingYD) {
			if (/* (collidingYU && getVelocity().y < 0) || */!collidingYU)
				getPosition().y += (velocity.y * delta);
		}
		setBounds(getPosition());

	}

	public Rectangle getBounds() {
		return bounds;
	}

	public void setBounds(Vector2 position) {
		this.bounds.set(position.x, position.y, sprite.getWidth(),
				sprite.getHeight());
	}

	public boolean isCollidingXL() {
		return collidingXL;
	}

	public void setCollidingXL(boolean collidingXL) {
		this.collidingXL = collidingXL;
	}

	public boolean isCollidingXR() {
		return collidingXR;
	}

	public void setCollidingXR(boolean collidingXR) {
		this.collidingXR = collidingXR;
	}

	public boolean isCollidingYU() {
		return collidingYU;
	}

	public void setCollidingYU(boolean collidingYU) {
		this.collidingYU = collidingYU;
	}

	public boolean isCollidingYD() {
		return collidingYD;
	}

	public void setCollidingYD(boolean collidingYD) {
		this.collidingYD = collidingYD;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
	public void incScore(int inc) {
		
		if(!onChest)
		this.score = score + inc;
		onChest = true;
	}

}
