package com.taffaz.ld29.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Array;
import com.taffaz.ld29.game.LD29Game;
import com.taffaz.ld29.game.model.entities.Bubble;

public class LevelSelectScreen extends AbstractScreen {

	Skin skin;
	Table table;
	Array<Bubble> bubbleArray;
	Image level1;
	Image level2;
	Image level3;
	Image level4;
	Image level5;
	

	public LevelSelectScreen(LD29Game game) {
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

		bubbleArray = new Array<Bubble>();

		Gdx.input.setInputProcessor(stage);
		
		level1 = new Image(new Texture("data/textures/levels/lvl_1_s.png"));
		level2 = new Image(new Texture("data/textures/levels/lvl_2_s.png"));
		level3 = new Image(new Texture("data/textures/levels/lvl_3_s.png"));
		level4 = new Image(new Texture("data/textures/levels/lvl_4_s.png"));
		level5 = new Image(new Texture("data/textures/levels/lvl_5_s.png"));
		

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
		table.row();
		skin = new Skin(Gdx.files.internal("data/ui/uiskin.json"));

		final TextButton lvl1Button = new TextButton("Level 1", skin);
		final TextButton lvl2Button = new TextButton("Level 2", skin);
		final TextButton lvl3Button = new TextButton("Level 3", skin);
		final TextButton lvl4Button = new TextButton("Level 4", skin);
		final TextButton lvl5Button = new TextButton("Level 5", skin);
		
		table.add(level1).padRight(5).padTop(5);
		table.add(level2).padTop(5);
		table.row();
		table.add(lvl1Button).padBottom(5);
		table.add(lvl2Button).padBottom(5);
		table.row();

		
		table.add(level3).padRight(5);
		table.add(level4);
		table.row();
		table.add(lvl3Button).padBottom(5);
		table.add(lvl4Button).padBottom(5);
		table.row();
		
		table.add(level5).colspan(2).center();
		table.row();
		table.add(lvl5Button).colspan(2).center().padBottom(5);
		table.row();
		
		final TextButton menuButton=new TextButton("Return to Menu",skin);

        table.add(menuButton).colspan(2).center().padBottom(5);

        
		
        menuButton.addListener(new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {
                //System.out.println("Clicked! Is checked: " + button.isChecked());
                game.setScreen( new MenuScreen(game));
 
            }
        });
		
		lvl1Button.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				// System.out.println("Clicked! Is checked: " +
				// button.isChecked());
				game.levelNum = 0;
				game.setScreen(new GameScreen(game));
			}
		});

		lvl2Button.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				game.levelNum = 1;
				game.setScreen(new GameScreen(game));
			}
		});

		lvl3Button.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				game.levelNum = 2;
				game.setScreen(new GameScreen(game));
			}
		});

		lvl4Button.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				game.levelNum = 3;
            	game.setScreen(new GameScreen(game));
			}
		});

		lvl5Button.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				game.levelNum = 4;
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
