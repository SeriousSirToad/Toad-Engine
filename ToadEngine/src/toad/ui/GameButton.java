package toad.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import toad.game.GameState;
import toad.game.Main;
import toad.io.InputHandler;

public class GameButton {

	private Rectangle buttonRect;

	private String text;

	public boolean hasBeenClicked = false;
	public boolean attatchedToEntity = false;
	protected boolean onThis = false;

	public Color color;
	public int numTimesClicked;

	public int x, y, width = 32, height = 16;

	protected InputHandler input;

	public BufferedImage buttonImage;

	public GameButton(int x, int y, BufferedImage image) {

		this.x = x;
		this.y = y;
		this.width = image.getWidth();
		this.height = image.getWidth();

		input = Main.input;

		buttonRect = new Rectangle(x, y, width, height);

	}

	public GameButton(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;

		buttonRect = new Rectangle(x, y, width, height);

		input = Main.input;
	}

	public GameButton(int x, int y) {
		this.x = x;
		this.y = y;

		buttonRect = new Rectangle(x, y, width, height);

		input = Main.input;
	}

	public GameButton(int x, int y, String text) {
		this.x = x;
		this.y = y;

		buttonRect = new Rectangle(x, y, width, height);

		input = Main.input;
	}

	protected void onClick() {

	}

	public void tick() {

		buttonRect.x = x * GameState.renderScale;
		buttonRect.y = y * GameState.renderScale;
		buttonRect.width = width * GameState.renderScale;
		buttonRect.height = height * GameState.renderScale;

		if (onThis && input.clicking())
			hasBeenClicked = true;

		if (hasBeenClicked && !input.clicking()) {
			onClick();
			hasBeenClicked = false;
		}

		if (buttonRect.contains(input.MouseX, input.MouseY)) {
			onThis = true;
		} else {
			onThis = false;
		}

	}

	Color kindaTransparent = new Color(255, 255, 255, 127);

	public void setWidth(int width) {
		this.width = width;
	}
	
	public void render() {
		Graphics g = Main.g;
		g.setColor(kindaTransparent);

		if (text != null) {
			g.drawString(text, x, y);
		}

		if (onThis) {
			g.fillRect(x, y, width, height);
		}
	}

	public void setColor(Color color) {
		kindaTransparent = new Color(color.getRed(), color.getGreen(), color.getBlue(), 100);
	}

}
