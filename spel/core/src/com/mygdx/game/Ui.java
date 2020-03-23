package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.objects.Player;

public class Ui {
	Texture texture;
	BitmapFont font;
	public Ui(Texture texture, BitmapFont font) {
		this.texture = texture;
		this.font = font;
	}
	
	public void draw(SpriteBatch spriteBatch) {
		spriteBatch.draw(this.texture, 0, MyGame.WINDOW_HEIGHT - texture.getHeight());
		spriteBatch.draw(TextureManager.HEALTHBAR_TEXTURE, 130, 700,Player.getHealth()*40,TextureManager.HEALTHBAR_TEXTURE.getHeight());
		font.draw(spriteBatch, "Score: " + Player.getScore(), 400, 730);
		font.draw(spriteBatch, "Stage: " + StageHandler.getStage(), 850, 730);
		font.draw(spriteBatch, "Health: ", 10, 730);
	}
}
