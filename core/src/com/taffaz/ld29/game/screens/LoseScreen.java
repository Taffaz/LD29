package com.taffaz.ld29.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.taffaz.ld29.game.LD29Game;

public class LoseScreen extends AbstractScreen {

	Skin skin;
	Table table;
	Texture overlay;
	GameScreen parent;

	public LoseScreen(LD29Game game, GameScreen parent) {
		super(game);
		this.parent = parent;
		create();
	}

	@Override
	public void render(float delta) {
		super.render(delta);

		Gdx.gl.glClearColor(96 / 255f, 141 / 255f, 255 / 255f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		Color color = batch.getColor();
		
		batch.begin();
		parent.gameOverRender(batch);
		batch.setColor(0.1f, 0.1f, 0.1f, 0.5f);
		batch.draw(overlay, 0, 0, 1280, 720);
		batch.setColor(color);
		batch.end();

		stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
		stage.draw();

		// Table.drawDebug(stage);

		if (Gdx.input.isTouched()) {
			Gdx.app.log(LD29Game.LOG, "X: " + (Gdx.input.getX()) + " Y: "
					+ (Gdx.graphics.getHeight() - Gdx.input.getY()));
		}

	}

	public void create() {
		batch = game.getSpriteBatch();
		overlay = new Texture("data/textures/brown.png");

		Gdx.input.setInputProcessor(stage);

		table = new Table();
		stage.addActor(table);
		table.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		table.setPosition(0, 0);
		// table.left().top();
		// table.debug();
		table.row().minHeight(55).maxHeight(55);
		skin = new Skin(Gdx.files.internal("data/ui/uiskin.json"));

		table.add(new Label("You Died", skin));
		table.row().left();
		table.add(new Label("Score: " + game.player.getScore(), skin));
		table.row().left();
		table.add(new Label("Try again or return to the menu",
				skin));
		table.row().center().pad(10);
		final TextButton menuButton = new TextButton("Return to Menu", skin);
		// menuButton.setPosition(Gdx.graphics.getWidth()/2 - 250, 50);
		table.add(menuButton);
		final TextButton tryAgainButton = new TextButton("Try Again", skin);
		// menuButton.setPosition(Gdx.graphics.getWidth()/2 - 250, 50);
		table.add(tryAgainButton);
		table.row().minHeight(55).maxHeight(55);

		menuButton.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				game.reInitPlayer();
				game.reload = true;
				game.setScreen(new MenuScreen(game));
			}
		});
		
		tryAgainButton.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				game.reInitPlayer();
				game.reload = true;
				game.setScreen(new GameScreen(game));

			}
		});

	}

	@Override
	public void resize(int width, int height) {
		// super.resize(width, height);

	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}
