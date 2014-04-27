package com.taffaz.ld29.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.taffaz.ld29.game.LD29Game;
import com.taffaz.ld29.game.utils.AudioManager;
import com.taffaz.ld29.game.utils.LevelFactory;

public class GameScreen extends AbstractScreen {

	LevelFactory lvlFact;
	Table table;
	Skin skin;
	Texture health;
	Texture oxygen;
	Label scoreLbl;
	int lastNumFullBubbles;

	public GameScreen(LD29Game game) {
		super(game);

		lvlFact = new LevelFactory(game);
		game.level = lvlFact.generateLevel(game.reload);
		//game.level = lvlFact.generateLevelFive();
		batch = game.getSpriteBatch();
		health = new Texture("data/textures/heart.png");
		oxygen = new Texture("data/textures/bubble.png");
		create();

	}

	public void create() {
		Gdx.input.setInputProcessor(stage);

		lastNumFullBubbles = 4;
		game.player.onChest = false;
		
		table = new Table();
		stage.addActor(table);
		table.setSize(200, 200);
		table.setPosition(Gdx.graphics.getWidth() - 220,
				Gdx.graphics.getHeight() - 210);
		table.left().top();
		//table.debug();

		table.row().left().top().expandX().fillX().minHeight(36f);
		skin = new Skin(Gdx.files.internal("data/ui/uiskin.json"));


		Label tempLbl = new Label("Health: ", skin);

		table.add(tempLbl);

		table.row().left().top().expandX().fillX().minHeight(36f);
		
		tempLbl = new Label("Score: ", skin);

		table.add(tempLbl);
		table.row().left().top().expandX().fillX().minHeight(36f);

		tempLbl = new Label("Oxygen: ", skin);

		table.add(tempLbl);

		table.row().left().top().expandX().fillX().minHeight(36f);

		scoreLbl = new Label("" + game.player.getScore(), skin);
		scoreLbl.setPosition(1120, 645);
		stage.addActor(scoreLbl);
	}

	public void renderHUD() {

		scoreLbl.setText("" + game.player.getScore());
		
		int currHealth = game.level.getPlayer().getHealth();
		
		int fullHearts = currHealth / 2;
		
		float halfHearts = currHealth/2f- fullHearts;
		
		Gdx.app.log(LD29Game.LOG, "" + currHealth);
		Gdx.app.log(LD29Game.LOG, "" + halfHearts);
		Gdx.app.log(LD29Game.LOG, "" + fullHearts);

		for (int i = 0; i < fullHearts; i++) {

			batch.draw(health, Gdx.graphics.getWidth() - 160 + (i * 32),
					Gdx.graphics.getHeight() - 42);

		}
		
		if(halfHearts > 0){
			batch.draw(health, (float)(Gdx.graphics.getWidth() - 160 + fullHearts * 32), Gdx.graphics.getHeight() - 42f, 0, 0, (int)(0.5 * oxygen.getWidth()), oxygen.getHeight());
		}

		int secsRemaining = (int) (game.level.getTimeLeft() / 1000);

		int timeOriginally = game.level.getTimer() / 1000;

		float percentLeft = ((float) secsRemaining / (float) timeOriginally) * 100;

		int numFullBubbles = (int) (percentLeft / 20);
		
		if(numFullBubbles < lastNumFullBubbles){
			AudioManager.bubble.play();
		}
		
		lastNumFullBubbles = numFullBubbles;

		float halfBubbleWidth = (percentLeft / 20) - numFullBubbles;


		for (int i = 0; i < numFullBubbles; i++) {
			batch.draw(oxygen, Gdx.graphics.getWidth() - 160 + (i * 32),
					Gdx.graphics.getHeight() - 114);
		}
		batch.draw(oxygen, Gdx.graphics.getWidth() - 160
				+ (numFullBubbles * 32), Gdx.graphics.getHeight() - 114,
				oxygen.getWidth() * halfBubbleWidth, oxygen.getHeight());
		table.row().left().top().expandX().fillX();

	}

	public void gameOverRender(SpriteBatch batch) {
		game.level.render(batch);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(96 / 255f, 141 / 255f, 255 / 255f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		if (game.won) {
			game.level.lvlWon = false;
			game.player.onChest = false;
			game.levelNum = 0;
			//game.reInitPlayer();
			game.setScreen(new WinScreen(game, this));
			
		} else if (game.level.lose) {
			game.setScreen(new LoseScreen(game, this));
		} else if (game.level.lvlWon) {
			//game.level.player.incScore(game.level.chest.getScore());
			game.level.lvlWon = false;
			
			game.setScreen(new GameScreen(game));
		} else {

			game.level.update(delta);

			game.level.checkCollisions();

			game.level.updatePlayerPosition(delta);

			batch.begin();

			game.level.render(batch);

			renderHUD();

			batch.end();

			// level.debugRender();

			stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
			stage.draw();

			//Table.drawDebug(stage);

		}
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
