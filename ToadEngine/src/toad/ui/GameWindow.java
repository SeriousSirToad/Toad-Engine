package toad.ui;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;

import toad.game.Main;

public class GameWindow {

	static FontMetrics fm;

	public GameButton[] buttons;

	public int w, h;
	public int x, y;

	private boolean active;

	Color colour;

	public GameWindow(int w, int h, int colour, GameButton[] buttons) {
		x = Main.width() / 2 - w / 2;
		y = Main.height() / 2 - h / 2;
		this.buttons = buttons;
		this.colour = new Color(colour);

	}

	public void activate() {
		active = true;
	}

	public void deactivate() {
		active = false;
	}

	public void update() {
		if (active) {
			show();
			for (GameButton b : buttons) {
				b.tick();
			}
			if (Main.input.esc.isPressed()) {
				deactivate();
			}
		}
	}

	public void show() {

		Graphics g = Main.g;
		g.setColor(colour);
		g.fillRect(x, y, w, h);
		for (GameButton b : buttons) {
			b.render();
		}
		showText();

	}

	public void showText() {

	}

}
