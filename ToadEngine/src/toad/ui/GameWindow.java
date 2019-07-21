package toad.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.util.ArrayList;

import toad.game.Main;

public class GameWindow {

	public static Font standardFont = new Font("Comic sans ms", Font.BOLD, 5);
	static FontMetrics fm;

	public GameButton[] buttons;

	public int w, h;
	public int x, y;

	private boolean active;

	Color colour;

	ArrayList<Message> messages = new ArrayList<>();

	public GameWindow(int w, int h, int colour, GameButton[] buttons) {
		x = Main.width() / 2 - w / 2;
		y = Main.height() / 2 - h / 2;
		this.buttons = buttons;
		this.colour = new Color(colour);
	}

	public static class Message {
		int x, y;
		Font f = standardFont;
		String text;

		public Message(int x, int y, String text) {
			this.x = x;
			this.y = y;
			this.text = text;
		}
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
		for (Message m : messages) {
			Main.g.drawString(m.text, m.x, m.y);
		}
	}

	public void addMessage(int x, int y, String text) {
		messages.add(new Message(x, y, text));
	}

}
