package com.taffaz.ld29.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Array;
import com.taffaz.ld29.game.LD29Game;
import com.taffaz.ld29.game.model.entities.Bubble;
import com.taffaz.ld29.game.utils.AudioManager;

public class MenuScreen extends AbstractScreen {

	Skin skin;
	Table table;
	Texture background;
	Texture bubbles;
	Array<Bubble> bubbleArray;

	public MenuScreen(LD29Game game) {
		super(game);
		create();
	}

	@Override
	public void render(float delta) {
		super.render(delta);

		Gdx.gl.glClearColor(96 / 255f, 141 / 255f, 255 / 255f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.begin();
		batch.draw(background, 0, 0, 1280, 720);

		for (Bubble b : bubbleArray) {
			b.update(delta);
			b.render(batch);
		}
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
		background = new Texture("data/textures/menuScreen_small.png");


		bubbleArray = new Array<Bubble>();

		Gdx.input.setInputProcessor(stage);

		for (int i = 0; i < 20; i++) {
			bubbleArray.add(new Bubble(MathUtils.random(0,
					Gdx.graphics.getWidth()), MathUtils.random(0,
					Gdx.graphics.getHeight())));
		}

		table = new Table();
		stage.addActor(table);
		table.setSize(500, 220);
		table.setPosition(750, 80);
		table.left().top();
		// table.debug();
		table.row().minHeight(55).maxHeight(55);
		skin = new Skin(Gdx.files.internal("data/ui/uiskin.json"));

		final TextButton playButton = new TextButton("Play", skin);
		table.add(playButton).left().expandX().fillX();
		table.row().minHeight(55).maxHeight(55);

		final TextButton lvlSelectButton = new TextButton("Level Select", skin);
		table.add(lvlSelectButton).left().expandX().fillX();
		table.row().minHeight(55).maxHeight(55);

		final TextButton instButton = new TextButton("Instructions", skin);
		table.add(instButton).expandX().fillX();

		table.row().minHeight(55).maxHeight(55);

		
		final TextButton creditsButton = new TextButton("Credits", skin);
		table.add(creditsButton).expandX().fillX();

		table.row().minHeight(55).maxHeight(55);

		playButton.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				// System.out.println("Clicked! Is checked: " +
				// button.isChecked());
				AudioManager.music.stop();
				game.setScreen(new GameScreen(game));

			}
		});

		instButton.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				// System.out.println("Clicked! Is checked: " +
				// button.isChecked());
				game.setScreen(new InstructionsScreen(game));

			}
		});

		creditsButton.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				// System.out.println("Clicked! Is checked: " +
				// button.isChecked());
				game.setScreen(new CreditsScreen(game));

			}
		});
		
		lvlSelectButton.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				// System.out.println("Clicked! Is checked: " +
				// button.isChecked());
				game.setScreen(new LevelSelectScreen(game));

			}
		});

	}

	@Override
	public void resize(int width, int height) {
		// super.resize(width, height);

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
	public void dispose() {

	}

}
