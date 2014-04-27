package com.taffaz.ld29.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Array;
import com.taffaz.ld29.game.LD29Game;
import com.taffaz.ld29.game.model.entities.Bubble;

public class InstructionsScreen extends AbstractScreen {

	Skin skin;
	Table table;

	Texture sharkTex;
	Texture swordfishTex;
	Texture blowfishTex;
	Texture terrainTex;
	Array<Bubble> bubbleArray;

	public InstructionsScreen(LD29Game game) {
		super(game);
		create();
	}

	@Override
	public void render(float delta) {
		super.render(delta);

		Gdx.gl.glClearColor(96 / 255f, 141 / 255f, 255 / 255f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.begin();

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

		this.sharkTex = new Texture("data/textures/shark.png");
		this.swordfishTex = new Texture("data/textures/swordfish.png");
		this.blowfishTex = new Texture("data/textures/blowfish.png");

		bubbleArray = new Array<Bubble>();

		Image shark = new Image(sharkTex);
		Image swordfish = new Image(swordfishTex);
		Image blowfish = new Image(blowfishTex);

		bubbleArray = new Array<Bubble>();

		Gdx.input.setInputProcessor(stage);

		for (int i = 0; i < 20; i++) {
			bubbleArray.add(new Bubble(MathUtils.random(0,
					Gdx.graphics.getWidth()), MathUtils.random(0,
					Gdx.graphics.getHeight())));
		}

		table = new Table();
		stage.addActor(table);
		table.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		table.setPosition(0, 0);
		// table.left().top();
		// table.debug();
		table.row().colspan(2);
		skin = new Skin(Gdx.files.internal("data/ui/uiskin.json"));

		table.add(new Label("Instructions", skin));
		table.row().left().colspan(2);
		table.add(new Label(
				"Navigate the diver to the chest while avoiding the enemies.",
				skin));
		table.row().left().colspan(2);
		table.add(new Label("Enemies:", skin)).padTop(25);
		table.row().left();
		table.add(shark).padTop(5).padBottom(5);
		table.add(new Label(
				"The shark prowls slowly between two areas looking for prey",
				skin));
		table.row().left();
		table.add(swordfish).padTop(5).padBottom(5);
		table.add(new Label(
				"The swordfish charges wildly hoping to spear some dinner",
				skin));
		table.row().left();
		table.add(blowfish).padTop(5).padBottom(5);
		table.add(new Label(
				"The blowfish expands rapidly to defend itself with it's sharp spikes",
				skin));
		table.row().left().colspan(2).padTop(25);
		table.add(new Label("Controls:", skin));
		table.row().left().colspan(2);
		table.add(new Label("Swim Left: Use 'A', or 'Left Arrow'", skin));
		table.row().left().colspan(2);
		table.add(new Label("Swim Right: Use 'D', or 'Right Arrow'", skin));
		table.row().left().colspan(2);
		table.add(new Label("Swim Up: Use 'W', 'Spacebar' or 'Up Arrow'", skin));
		table.row().center().pad(10).colspan(2);
		final TextButton menuButton = new TextButton("Return to Menu", skin);
		// menuButton.setPosition(Gdx.graphics.getWidth()/2 - 250, 50);
		table.add(menuButton);
		table.row().minHeight(55).maxHeight(55);

		menuButton.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				// System.out.println("Clicked! Is checked: " +
				// button.isChecked());
				game.setScreen(new MenuScreen(game));

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
