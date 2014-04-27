package com.taffaz.ld29.game.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.taffaz.ld29.game.LD29Game;
import com.taffaz.ld29.game.model.Level;
import com.taffaz.ld29.game.model.entities.Chest;
import com.taffaz.ld29.game.model.entities.Player;
import com.taffaz.ld29.game.model.entities.Terrain;
import com.taffaz.ld29.game.model.entities.enemies.Blowfish;
import com.taffaz.ld29.game.model.entities.enemies.Shark;
import com.taffaz.ld29.game.model.entities.enemies.Swordfish;

public class LevelFactory {

	private Player player;
	private LD29Game game;
	Level level;
	Texture chestTex;
	Texture sharkTex;
	Texture swordfishTex;
	Texture blowfishTex;
	Texture terrainTex;

	public LevelFactory(LD29Game game) {
		this.game = game;
		this.player = game.player;
		this.level = game.level;
		this.chestTex = new Texture("data/textures/chest.png");
		this.sharkTex = new Texture("data/textures/shark.png");
		this.swordfishTex = new Texture("data/textures/swordfish.png");
		this.blowfishTex = new Texture("data/textures/blowfish.png");
		this.terrainTex = new Texture("data/textures/brown.png");
	}

	public Level generateLevel(boolean reload) {

		if (!reload)
			game.levelNum++;

		switch (game.levelNum) {
		case 1:
			level = generateLevelOne();
			break;
		case 2:
			level = generateLevelTwo();
			break;
		case 3:
			level = generateLevelThree();
			break;
		case 4:
			level = generateLevelFour();
			break;
		case 5:
			level = generateLevelFive();
			break;
		default:
			game.won = true;
			break;
		}

		game.reload = false;
		return level;
	}



	public Level generateLevelOne() {

		player.getPosition().set(50, 270); // 90,70
		player.setBounds(player.getPosition());

		Chest chest = newChest(1200, 230, 100);

		level = new Level(player, chest, 60000);

		// Terrain
		newTerrain(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight() / 3);
		newTerrain(0, 2 * Gdx.graphics.getHeight() / 3,
				Gdx.graphics.getWidth(), Gdx.graphics.getHeight() / 3);

		// Enemies
		newShark(255, 255, new Vector2(255, 450), true);
		newShark(655, 450, new Vector2(655, 255), true);
		newShark(1055, 255, new Vector2(1055, 450), true);

		newSwordfish(855, 450, new Vector2(855, 255), true);
		newSwordfish(455, 450, new Vector2(455, 255), true);

		return level;

	}
	
	public Level generateLevelTwo() {

		player.getPosition().set(50, 270); // 90,70.
		player.setBounds(player.getPosition());

		Chest chest = newChest(1200, 230, 200);

		level = new Level(player, chest, 60000);

		// Terrain
		newTerrain(0, 0, Gdx.graphics.getWidth(), 20);
		newTerrain(0, 0, 20, Gdx.graphics.getHeight());
		newTerrain(0, Gdx.graphics.getHeight() - 20, Gdx.graphics.getWidth(),
				20);
		newTerrain(Gdx.graphics.getWidth() - 20, 0, 20,
				Gdx.graphics.getHeight());

		newTerrain(0, 0, Gdx.graphics.getWidth() / 3,
				Gdx.graphics.getHeight() / 3);
		newTerrain(0, Gdx.graphics.getHeight() - Gdx.graphics.getHeight() / 3,
				Gdx.graphics.getWidth() / 3, Gdx.graphics.getHeight() / 3);
		newTerrain(Gdx.graphics.getWidth() - Gdx.graphics.getWidth() / 3, 0,
				Gdx.graphics.getWidth() / 3, Gdx.graphics.getHeight() / 3);
		newTerrain(Gdx.graphics.getWidth() - Gdx.graphics.getWidth() / 3,
				Gdx.graphics.getHeight() - Gdx.graphics.getHeight() / 3,
				Gdx.graphics.getWidth() / 3, Gdx.graphics.getHeight() / 3);

		newTerrain(Gdx.graphics.getWidth()/2 - 100, 120, 200,
				Gdx.graphics.getHeight()-240);

		// Enemies
		newShark(255, 255, new Vector2(255, 450), true);
		newShark(1055, 255, new Vector2(1055, 450), true);

		newSwordfish(855, 450, new Vector2(855, 255), true);
		newSwordfish(455, 450, new Vector2(455, 255), true);
		
		newBlowfish(646 - blowfishTex.getWidth() / 2, 50, 32);
		newBlowfish(646 - blowfishTex.getWidth() / 2, 630, 32);

		return level;
	}

	public Level generateLevelThree() {

		player.getPosition().set(
				Gdx.graphics.getWidth() / 2 - player.getSprite().getWidth(),
				Gdx.graphics.getHeight() - 50);
		player.setBounds(player.getPosition());

		// Chest
		Chest chest = newChest(
				(Gdx.graphics.getWidth() - chestTex.getWidth()) / 2, 85, 300);

		level = new Level(player, chest, 60000);

		// Left Wall
		level.addTerrain(new Terrain(0, 0, new Texture(
				"data/textures/brown.png"), Gdx.graphics.getWidth() / 4,
				Gdx.graphics.getHeight()));

		// Right Wall
		level.addTerrain(new Terrain(Gdx.graphics.getWidth()
				- Gdx.graphics.getWidth() / 4, 0, new Texture(
				"data/textures/brown.png"), Gdx.graphics.getWidth() / 4,
				Gdx.graphics.getHeight()));

		// Floor
		level.addTerrain(new Terrain(0, 0, new Texture(
				"data/textures/brown.png"), Gdx.graphics.getWidth(), 100));

		// Platforms
		level.addTerrain(new Terrain(Gdx.graphics.getWidth() - 2
				* Gdx.graphics.getWidth() / 3, Gdx.graphics.getHeight() - 100,
				new Texture("data/textures/brown.png"),
				Gdx.graphics.getWidth() / 3, 20));

		level.addTerrain(new Terrain(Gdx.graphics.getWidth() / 4, Gdx.graphics
				.getHeight() - 250, new Texture("data/textures/brown.png"),
				270, 20));

		level.addTerrain(new Terrain(Gdx.graphics.getWidth()
				- Gdx.graphics.getWidth() / 4 - 270,
				Gdx.graphics.getHeight() - 250, new Texture(
						"data/textures/brown.png"), 270, 20));

		level.addTerrain(new Terrain(Gdx.graphics.getWidth() / 4 + 250,
				Gdx.graphics.getHeight() - 280, new Texture(
						"data/textures/brown.png"), 20, 30));

		level.addTerrain(new Terrain(Gdx.graphics.getWidth() / 4 + 370,
				Gdx.graphics.getHeight() - 280, new Texture(
						"data/textures/brown.png"), 20, 30));

		level.addTerrain(new Terrain(Gdx.graphics.getWidth() / 4 + 250,
				Gdx.graphics.getHeight() - 380, new Texture(
						"data/textures/brown.png"), 20, 50));

		level.addTerrain(new Terrain(Gdx.graphics.getWidth() / 4 + 370,
				Gdx.graphics.getHeight() - 380, new Texture(
						"data/textures/brown.png"), 20, 50));

		level.addTerrain(new Terrain(Gdx.graphics.getWidth() / 4
				+ Gdx.graphics.getWidth() / 8, 200, new Texture(
				"data/textures/brown.png"), Gdx.graphics.getWidth() / 4, 20));

		level.addTerrain(new Terrain(Gdx.graphics.getWidth() / 4, Gdx.graphics
				.getHeight() - 380, new Texture("data/textures/brown.png"),
				250, 20));

		level.addTerrain(new Terrain(Gdx.graphics.getWidth()
				- Gdx.graphics.getWidth() / 4 - 250,
				Gdx.graphics.getHeight() - 380, new Texture(
						"data/textures/brown.png"), 250, 20));

		// Enemies

		newBlowfish(500, Gdx.graphics.getHeight() - 200, 64);

		newBlowfish(Gdx.graphics.getWidth() - 500 - blowfishTex.getWidth(),
				Gdx.graphics.getHeight() - 200, 64);

		level.addRectEnemy(new Swordfish(515, 400, new Texture(
				"data/textures/swordfish.png"), new Vector2(710, 400)));

		Shark shark = new Shark(350, 150,
				new Texture("data/textures/shark.png"), new Vector2(350, 275));

		// shark.getPosition().sub(shark.getSprite().getWidth()/2, 0);

		level.addRectEnemy(shark);

		shark = new Shark(Gdx.graphics.getWidth() - 350
				- shark.getSprite().getWidth(), 275, new Texture(
				"data/textures/shark.png"), new Vector2(Gdx.graphics.getWidth()
				- 350 - shark.getSprite().getWidth(), 150));
		shark.flip = true;

		// shark.getPosition().sub(shark.getSprite().getWidth() * 2, 0);

		level.addRectEnemy(shark);

		return level;
	}

	public Level generateLevelFour() {

		player.getPosition().set(90, 70); // 90,70
		player.setBounds(player.getPosition());

		Chest chest = newChest(1200, 10, 400);

		level = new Level(player, chest, 60000);

		// Terrain
		newTerrain(0, 0, Gdx.graphics.getWidth(), 20);
		newTerrain(0, 0, 20, Gdx.graphics.getHeight());
		newTerrain(0, Gdx.graphics.getHeight() - 20, Gdx.graphics.getWidth(),
				20);
		newTerrain(Gdx.graphics.getWidth() - 20, 0, 20,
				Gdx.graphics.getHeight());

		newTerrain(1020, 100, 280, 620);

		newTerrain(180, 0, 40, 175);
		newTerrain(180, 250, 40, 175);
		newTerrain(180, 480, 40, 80);
		newTerrain(220, 520, 650, 40);
		newTerrain(360, 0, 40, 460);
		newTerrain(830, 100, 40, 420);

		// Enemies

		newShark(430, 420, new Vector2(650, 420));
		newBlowfish(750, 420, 32);

		newShark(430, 220, new Vector2(650, 220));
		newBlowfish(750, 220, 32);

		newShark(750, 320, new Vector2(490, 320));
		newBlowfish(430, 320, 32);

		newShark(750, 120, new Vector2(490, 120));
		newBlowfish(430, 120, 32);

		newBlowfish(920, 320, 32);
		newBlowfish(265, 586, 80);
		newBlowfish(525, 586, 80);
		newBlowfish(800, 586, 80);
		newBlowfish(925, 50, 32);
		newBlowfish(260, 300, 32);

		newSwordfish(185, 190, new Vector2(30, 190));
		newSwordfish(185, 435, new Vector2(30, 435));

		return level;

	}

	public Level generateLevelFive() {
		player.getPosition().set(50, 50); // 90,70
		player.setBounds(player.getPosition());

		Chest chest = newChest(640 - chestTex.getWidth() / 2, 270, 500);

		level = new Level(player, chest, 60000);

		// Terrain
		newTerrain(0, 0, 1280, 20);
		newTerrain(0, 700, 1280, 20);
		newTerrain(1260, 0, 20, 720);
		newTerrain(0, 0, 20, 720);

		newTerrain(440, 260, 700, 20);
		newTerrain(440, 280, 20, 350);
		newTerrain(820, 280, 20, 200);
		newTerrain(460, 460, 130, 20);
		newTerrain(690, 460, 130, 20);

		newTerrain(630, 20, 20, 150);
		newTerrain(940, 360, 320, 20);
		newTerrain(840, 460, 320, 20);
		// newTerrain(1160, 480, 20, 20);

		newTerrain(210, 290, 230, 40);
		newTerrain(20, 370, 230, 40);
		newTerrain(210, 450, 230, 30);
		newTerrain(20, 530, 230, 40);
		newTerrain(340, 530, 100, 40);
		newTerrain(20, 610, 230, 20);
		newTerrain(340, 610, 100, 20);

		// Enemies

		newBlowfish(646 - blowfishTex.getWidth() / 2, 212, 32);
		newBlowfish(646 - blowfishTex.getWidth() / 2, 515, 32);
		newBlowfish(1030 - blowfishTex.getWidth() / 2,
				332 - blowfishTex.getWidth() / 2, 32);
		newBlowfish(1030 - blowfishTex.getWidth() / 2,
				418 - blowfishTex.getWidth() / 2, 32);

		newShark(340, 40, new Vector2(550, 200));
		newShark(670, 200, new Vector2(940, 40));

		newSwordfish(50, 260, new Vector2(350, 260));
		newSwordfish(350, 340, new Vector2(50, 340));
		newSwordfish(50, 420, new Vector2(350, 420));
		newSwordfish(350, 500, new Vector2(50, 500));
		newSwordfish(50, 580, new Vector2(350, 580));

		return level;
	}

	private Chest newChest(int x, int y, int score) {
		return new Chest(x, y, chestTex, score);
	}

	private void newShark(int x, int y, Vector2 pos) {
		level.addRectEnemy(new Shark(x, y, sharkTex, pos));
	}

	private void newShark(int x, int y, Vector2 pos, boolean flip) {
		Shark tempShark = new Shark(x, y, sharkTex, pos);
		tempShark.flip = flip;
		level.addRectEnemy(tempShark);
	}

	private void newSwordfish(int x, int y, Vector2 pos) {
		level.addRectEnemy(new Swordfish(x, y, swordfishTex, pos));
	}

	private void newSwordfish(int x, int y, Vector2 pos, boolean flip) {
		Swordfish tempFish = new Swordfish(x, y, swordfishTex, pos);
		tempFish.flip = flip;
		level.addRectEnemy(tempFish);
	}

	private void newBlowfish(int x, int y, int radius) {
		level.addCircEnemy(new Blowfish(x, y, blowfishTex, radius));
	}

	private void newTerrain(int x, int y, int width, int height) {
		level.addTerrain(new Terrain(x, y, terrainTex, width, height));
	}
}
