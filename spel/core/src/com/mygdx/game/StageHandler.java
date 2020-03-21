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
	static long gameTime = 0;

	public void update(ArrayList<Enemy> enemies, Player player, ArrayList<Bullet> bullets, ArrayList<Boss> bosses) {
		gameTime = (System.currentTimeMillis() - startTime) / 1000;
		spawnTimer++;
		if (spawnTimer + gameTime > 100) {
			spawnTimer = 0;
			if (gameTime < 10) {
				enemies.add(
						new Bird(new Vector2(MyGame.WINDOW_WIDTH, random.nextInt(MyGame.WINDOW_HEIGHT - 45 * 4) + 45),
								TextureManager.BIRD_TEXTURE, 5));

			} else if (gameTime > 10 && gameTime < 20) {
				if (random.nextInt(5) == 1) {
					enemies.add(new vapeMormon(
							new Vector2(MyGame.WINDOW_WIDTH, random.nextInt(MyGame.WINDOW_HEIGHT - 45 * 4) + 45),
							TextureManager.VAPE_MORMON_TEXTURE, 5, bullets, player));
				}
			} else if (gameTime > 1 && !bossSpawned) {
				bossSpawned = true;
				bosses.add(new Boss(
						new Vector2(MyGame.WINDOW_WIDTH - 100, random.nextInt(MyGame.WINDOW_HEIGHT - 45 * 4) + 45),
						TextureManager.SPIRIT_TEXTURE, 50, bullets, player));
			}
		}
	}
}
