package com.taffaz.ld29.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.taffaz.ld29.game.LD29Game;

public abstract class AbstractScreen implements Screen {

	protected final LD29Game game;
	protected final OrthographicCamera camera;
	protected SpriteBatch batch;
	public Stage stage;

	public AbstractScreen(LD29Game game) {
		this.game = game;
		this.camera = new OrthographicCamera();
		this.stage = new Stage();
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0f, 0f, 0f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		camera.update();

	}

	@Override
	public void show() {
	}

	@Override
	public void hide() {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void resize(int width, int height) {
		Gdx.app.log(LD29Game.LOG, "Resizing game to: " + width + " x "
				+ height);
		game.resize(width, height);
	}

	@Override
	public void dispose() {
	}

}