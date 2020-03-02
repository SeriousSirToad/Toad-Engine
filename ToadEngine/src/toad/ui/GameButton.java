package toad.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import toad.game.GameState;
import toad.game.Main;
import toad.io.InputHandler;

public class GameButton {

	private Rectangle buttonRect;

	private String text;
	private Font font;

	public boolean hasBeenClicked = false;
	public boolean attatchedToEntity = false;
	protected boolean onThis = false;

	public Color color;
	Color mainColor = new Color(255, 255, 255);
	public boolean bordered = true;
	public int textNum;
	public int numTimesClicked;

	public static int stdWidth = 20, stdHeight = 10;
	public int x, y, width = 16, height = 8;

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

	public GameButton(int x, int y, String text, Font font, int width, int height, int textNum) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.font = font;
		this.textNum = textNum;
		buttonRect = new Rectangle(x, y, width, height);

		input = Main.input;
		this.text = text;
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

	public GameButton(int x, int y, String text, Font font) {
		this.x = x;
		this.y = y;

		this.font = font;

		buttonRect = new Rectangle(x, y, width, height);

		input = Main.input;
		this.text = text;

	}

	public GameButton(int x, int y, int width, int height, InputHandler input) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;

		buttonRect = new Rectangle(x, y, width, height);

		this.input = input;
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
	Color TestColor = new Color(225, 124, 5, 56);

	public void setWidth(int width) {
		this.width = width;
	}

	public void render(Graphics g) {

		g.setColor(kindaTransparent);

		if (onThis) {
			g.fillRect(x, y, width, height);
		}

		if (bordered) {
			g.setColor(Color.black);
			g.drawRect(x, y, width, height);
		}

		if (text != null) {
			g.setColor(Color.white);
			g.drawString(text, x, y + (height / 2 + font.getSize() / 2));
		}

	}

	public void setColor(Color color) {
		kindaTransparent = new Color(color.getRed(), color.getGreen(),
				color.getBlue(), 100);
	}
	
	public void setTextNum(int num){
		textNum = num;
	}


}
