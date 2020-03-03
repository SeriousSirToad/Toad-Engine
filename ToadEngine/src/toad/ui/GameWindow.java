package toad.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import toad.game.GameState;
import toad.game.Main;

public class GameWindow {

	public GameButton[] buttons = new GameButton[1];

	static Font bodyFont = new Font("Comic sans ms", Font.PLAIN, 4);
	
	public int w, h;
	public int x, y;

	String title = "Sample text", body = "Sample text";
	String buttonName;

	public boolean active;

	Color colour = new Color(0, 0, 0, 200);

	public GameWindow(String title, String body, int w, int h, String buttonName) {
		this.buttonName = buttonName;
		if (title != null) {
			this.title = title;
		}
		if (body != null) {
			this.body = body;
		}
		if (w > GameState.gameWidth()) {
			w = GameState.gameWidth();
		}
		if (h > GameState.gameHeight()) {
			h = GameState.gameHeight();
		}
		this.w = w;
		this.h = h;
		System.out.println("w " + w);
		x = (GameState.gameWidth() / 2) - w / 2;
		y = (GameState.gameHeight() / 2)- h / 2;
		buttons[0] = new GameButton(x + (w / 2) - (GameButton.stdWidth / 2), y + h - GameButton.stdHeight, buttonName, bodyFont) {
			public void onClick() {
				deactivate();
			}
		};
	}

	public void activate() {
		active = true;
		InGameUI.addToRendOrder(this);
	}

	public void deactivate() {
		active = false;
		InGameUI.removeFromRendOrder(this);
	}

	public void update() {
		if (active) {
			show();
			for (GameButton b : buttons) {
				b.tick();
			}
		}
	}

	public void show() {

		Graphics g = Main.g;
		g.setColor(colour);
		g.fillRect(x, y, w, h);
		g.setColor(Color.white);
		g.drawRect(x, y, w, h);
		for (GameButton b : buttons) {
			b.render(g);
		}
		showText(g);

	}

	public void showText(Graphics g) {
		
		g.setColor(Color.white);
		g.drawString(title, x + 1, y + InGameUI.standardFont.getSize() + 1);
		g.setFont(bodyFont);
		int tempy = y + InGameUI.standardFont.getSize() + 1;
		for (String line : body.split("\n"))
	        g.drawString(line, x + 1, tempy += g.getFontMetrics().getHeight());
		g.setFont(InGameUI.standardFont);
		
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public void setBody(String body) {
		this.body = body;
	}

}
