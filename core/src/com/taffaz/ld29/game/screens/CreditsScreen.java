package com.taffaz.ld29.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Array;
import com.taffaz.ld29.game.LD29Game;
import com.taffaz.ld29.game.model.entities.Bubble;

public class CreditsScreen extends AbstractScreen {
	
	Skin skin;
	Table table;
	Array<Bubble> bubbleArray;
	
	public CreditsScreen(LD29Game game) {
		super(game);
		create();
	}
	
	@Override
	public void render(float delta) {
		super.render(delta);

		Gdx.gl.glClearColor(96 / 255f, 141 / 255f, 255 / 255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); 
        
        batch.begin();
		
		for(Bubble b : bubbleArray){
			b.update(delta);
			b.render(batch);
		}
		batch.end();


        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
        
        //Table.drawDebug(stage);
        
        if(Gdx.input.isTouched()){
			Gdx.app.log(LD29Game.LOG, "X: " + (Gdx.input.getX()) + " Y: "
					+ (Gdx.graphics.getHeight() - Gdx.input.getY()));
		}
        


	}
	
	public void create() {		
		batch = game.getSpriteBatch();
		
		bubbleArray = new Array<Bubble>();

		Gdx.input.setInputProcessor(stage);

		for (int i = 0; i < 20; i++) {
			bubbleArray.add(new Bubble(MathUtils.random(0, Gdx.graphics.getWidth()),MathUtils.random(0, Gdx.graphics.getHeight())));
		}
		
		table = new Table();
	    stage.addActor(table);
	    table.setSize(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
		table.setPosition(0,0);
		//table.left().top();
		//table.debug();
		table.row().minHeight(55).maxHeight(55);
		skin = new Skin(Gdx.files.internal("data/ui/uiskin.json"));
		
		
		
		table.add(new Label("Credits", skin));
		table.row().left();
		table.add(new Label("Special thanks to the following people: ", skin));
		table.row().left();
		table.add(new Label("Mario Zechner and all who contribute to LibGDX", skin));
		table.row().left();
		table.add(new Label("Everbody who makes Ludum Dare possible", skin));
		table.row().left();
		table.add(new Label("Christer Kaitila for motivating me to make 12 games in 2013", skin));
		table.row().left();
		table.add(new Label("And last but certainly not least...", skin));
		table.row().left();
		table.add(new Label("Stephanie for putting up with me and pushing me to finish in the very first Ludum Dare I took part in.", skin));
		table.row().center().pad(10);
        final TextButton menuButton=new TextButton("Return to Menu",skin);
        //menuButton.setPosition(Gdx.graphics.getWidth()/2 - 250, 50);
        table.add(menuButton);
        table.row().minHeight(55).maxHeight(55);

        
		
        menuButton.addListener(new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {
                //System.out.println("Clicked! Is checked: " + button.isChecked());
                game.setScreen( new MenuScreen(game));
 
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
