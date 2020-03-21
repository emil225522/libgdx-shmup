package com.mygdx.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.objects.*;

public class StageHandler {
	Random random = new Random();
	int spawnTimer = 0;
	long startTime = System.currentTimeMillis();
	static long elapsedGameTime = 0;

	public void update(ArrayList<Enemy> enemies, Player player, ArrayList<Bullet> bullets) {
		elapsedGameTime = (System.currentTimeMillis() - startTime) / 1000;
		if (elapsedGameTime < 30) {
			spawnTimer++;
			if (spawnTimer + elapsedGameTime > 100) {
				spawnTimer = 0;
				enemies.add(
						new Bird(new Vector2(MyGame.WINDOW_WIDTH, random.nextInt(MyGame.WINDOW_HEIGHT - 45 * 4) + 45),
								TextureManager.BIRD_TEXTURE, 5));
				if (random.nextInt(5) == 1) {
					enemies.add(new vapeMormon(
							new Vector2(MyGame.WINDOW_WIDTH, random.nextInt(MyGame.WINDOW_HEIGHT - 45 * 4) + 45),
							TextureManager.VAPE_MORMON_TEXTURE, 5, bullets, player));
				}
			}
		}
	}
}
