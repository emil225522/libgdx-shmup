package com.mygdx.game;

import java.util.ArrayList;
import java.util.Random;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.objects.*;

public class StageHandler {
	Random random = new Random();
	int spawnTimer = 0;
	long startTime = System.currentTimeMillis();
	boolean bossSpawned = false;
	static int stage = 1;
	static long gameTime = 0;

	ArrayList<Enemy> enemies;
	ArrayList<Bullet> bullets;
	ArrayList<Boss> bosses;
	ArrayList<Pickup> pickups;

	public StageHandler(ArrayList<Enemy> enemies, ArrayList<Bullet> bullets, ArrayList<Boss> bosses,
			ArrayList<Pickup> pickups) {
		this.enemies = enemies;
		this.bullets = bullets;
		this.bosses = bosses;
		this.pickups = pickups;
		stage = 1;
	}

	public void update() {
		gameTime = (System.currentTimeMillis() - startTime) / 1000;
		switch (stage) {
		case 1:
			stageHandle(stageOne, 100, 30, 1000);
			break;
		case 2:
			stageHandle(stageTwo, 90, 60, 2000);
			break;
		case 3:
			stageHandle(stageThree, 100, -1, -1);
			break;
		case 4:
			stageHandle(stageTwo, 20, -1, 10000);
			break;
		case 5:
			stageHandle(stageFive, 20, -1, -1);
			break;
		}
	}

	public static int getStage() {
		return stage;
	}

	private void birdAdd() {
		enemies.add(new Bird(TextureManager.BIRD_TEXTURE, pickups));
	}

	private void crowAdd() {
		enemies.add(new Crow(TextureManager.CROW_TEXTURE, bullets, pickups));
	}

	private void bossAdd() {
		bosses.add(new Spirit(new Vector2(MyGame.WINDOW_WIDTH, random.nextInt(MyGame.WINDOW_HEIGHT - 45 * 4) + 45),
				TextureManager.SPIRIT_TEXTURE, 50, bullets, pickups));
	}
	
	Runnable stageFive = () -> {
		if (!bossSpawned) {
			bossSpawned = true;
			bossAdd();
			bossAdd();
			bossAdd();
		} else if (bosses.size() == 0) {
			stage++;
			bossSpawned =  false;
		}
		if(random.nextInt(100) > 90) {
			if (random.nextInt(5) == 1) {
				crowAdd();
			}
			if (random.nextInt(3) == 1) {
				birdAdd();
			}
		}
	};
	Runnable stagefour = () -> {
		// spawn here
	};
	Runnable stageThree = () -> {
		if (!bossSpawned) {
			bossSpawned = true;
			bossAdd();
		} else if (bosses.size() == 0) {
			stage++;
			bossSpawned =  false;
		}
	};
	Runnable stageTwo = () -> {
		if (random.nextInt(5) == 1) {
			crowAdd();
		}
		if (random.nextInt(3) == 1) {
			birdAdd();
		}
	};
	Runnable stageOne = () -> {
		birdAdd();
	};

	private void stageHandle(Runnable spawnFunction, int spawnRate, long stageTime, int scoreLimit) {
		spawnTimer++;
		if (spawnTimer >= spawnRate) {
			spawnTimer = 0;
			spawnFunction.run();
		}
		if (gameTime > stageTime && stageTime > 0) {
			stage++;
		}
		if (Player.getScore() > scoreLimit && scoreLimit > 0) {
			stage++;
		}
	}

	public void reset() {
		spawnTimer = 0;
		startTime = System.currentTimeMillis();
		bossSpawned = false;
		gameTime = 0;
	}
}
