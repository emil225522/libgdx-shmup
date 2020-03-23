package com.mygdx.game;

import java.util.ArrayList;
import java.util.Random;
import java.util.function.Consumer;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.objects.*;

public class StageHandler {
	Random random = new Random();
	int spawnTimer = 0;
	long startTime = System.currentTimeMillis();
	boolean bossSpawned = false;
	int stage = 1;
	static long gameTime = 0;

	ArrayList<Enemy> enemies;
	Player player;
	ArrayList<Bullet> bullets;
	ArrayList<Boss> bosses;
	ArrayList<Pickup> pickups;

	public StageHandler(ArrayList<Enemy> enemies, Player player, ArrayList<Bullet> bullets, ArrayList<Boss> bosses,
			ArrayList<Pickup> pickups) {
		this.enemies = enemies;
		this.player = player;
		this.bullets = bullets;
		this.bosses = bosses;
		this.pickups = pickups;
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
			stageHandle(stageTwo, 10, -1, -1);
			break;
		}
	}
	public int getStage() {
		return this.stage;
	}
	
	private void birdAdd() {
		enemies.add(new Bird(new Vector2(MyGame.WINDOW_WIDTH, random.nextInt(MyGame.WINDOW_HEIGHT - 45 * 4) + 45),
				TextureManager.BIRD_TEXTURE, 5, pickups));
	}

	private void vapeAdd() {
		enemies.add(
				new vapeMormon(new Vector2(MyGame.WINDOW_WIDTH, random.nextInt(MyGame.WINDOW_HEIGHT - 45 * 4) + 45),
						TextureManager.VAPE_MORMON_TEXTURE, 5, bullets, player, pickups));
	}

	private void bossAdd() {
		bosses.add(new Boss(new Vector2(MyGame.WINDOW_WIDTH, random.nextInt(MyGame.WINDOW_HEIGHT - 45 * 4) + 45),
				TextureManager.SPIRIT_TEXTURE, 50, bullets, player, pickups));
	}

	
	Runnable stagefour = () ->{
		//spawn here
	};
	Runnable stageThree = () ->{
		if (!bossSpawned) {
			bossSpawned = true;
			bossAdd();
		}else if(bosses.size() == 0) {
			stage++;
		}
	};
	Runnable stageTwo = () ->{
		if (random.nextInt(5) == 1) {
			vapeAdd();
		}
		if (random.nextInt(3) == 1) {
			birdAdd();
		}
	};
	Runnable stageOne = () ->{
		birdAdd();
	};
	

	private void stageHandle(Runnable spawnFunction,int spawnRate, long stageTime, int scoreLimit) {
		spawnTimer++;
		if (spawnTimer >= spawnRate) {
			spawnTimer = 0;
			spawnFunction.run();
		}
		if (gameTime > stageTime && stageTime > 0) {
			stage++;
		}
		if (player.getScore() > scoreLimit && scoreLimit > 0) {
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
