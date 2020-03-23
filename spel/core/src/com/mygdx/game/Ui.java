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
		spriteBatch.draw(TextureManager.HEALTHBAR_TEXTURE, 130, MyGame.WINDOW_HEIGHT - TextureManager.HEALTHBAR_TEXTURE.getHeight()-(this.texture.getHeight()-TextureManager.HEALTHBAR_TEXTURE.getHeight())/2,((float)Player.getHealth()/(float)Player.getMaxHealth())*200,TextureManager.HEALTHBAR_TEXTURE.getHeight());
		font.draw(spriteBatch, "Score: " + Player.getScore(), 350, MyGame.WINDOW_HEIGHT - font.getXHeight());
		font.draw(spriteBatch, "Stage: " + StageHandler.getStage(), 850, MyGame.WINDOW_HEIGHT - font.getXHeight());
		font.draw(spriteBatch, "Health: ", 10, MyGame.WINDOW_HEIGHT - font.getXHeight());
	}
}
