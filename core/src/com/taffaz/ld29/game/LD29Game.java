package com.taffaz.ld29.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.taffaz.ld29.game.model.Level;
import com.taffaz.ld29.game.model.entities.Player;
import com.taffaz.ld29.game.screens.GameScreen;
import com.taffaz.ld29.game.screens.MenuScreen;
import com.taffaz.ld29.game.utils.AudioManager;

public class LD29Game extends Game {

	// constant useful for logging
	public static final String LOG = LD29Game.class.getSimpleName();

	SpriteBatch batch;
	Texture img;
	FPSLogger fpsLogger;
	public boolean debug;
	public Player player;
	public int levelNum;
	public boolean won;
	public Level level;
	public boolean reload;

	@Override
	public void create() {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		fpsLogger = new FPSLogger();
		debug = true;
		levelNum = 0;
		this.won = false;
		this.reload = false;
		AudioManager.music.setLooping(true);
		AudioManager.music.play();
		player = new Player(640, 360, new Texture(
				"data/textures/diver.png"), 10);
		
		setScreen(getMenuScreen());
	}
	
	public void reInitPlayer(){
		player = new Player(640, 360, new Texture(
				"data/textures/diver.png"), 5);
	}
	
	@Override
	public void render() {
		super.render();

		// output the current FPS
		fpsLogger.log();
	}

	@Override
	public void resize(int width, int height) {
		Gdx.app.log(LD29Game.LOG, "Resizing game to: " + width + " x "
				+ height);
		super.resize(width, height);

		// show the splash screen when the game is resized for the first time;
		// this approach avoids calling the screen's resize method repeatedly
		if (getScreen() == null) {
			setScreen(new MenuScreen(this));

		}
	}

	@Override
	public void pause() {
		super.pause();
		Gdx.app.log(LD29Game.LOG, "Pausing game");
	}

	@Override
	public void resume() {
		super.resume();
		Gdx.app.log(LD29Game.LOG, "Resuming game");
	}

	@Override
	public void dispose() {
		super.dispose();
		Gdx.app.log(LD29Game.LOG, "Disposing game");
	}

	public GameScreen getGameScreen() {
		return new GameScreen(this);
	}
	
	public MenuScreen getMenuScreen() {
		return new MenuScreen(this);
	}

	
	public SpriteBatch getSpriteBatch(){
		return batch;
	}

}
