package toad.ui;

import java.awt.*;
import java.util.ArrayList;

import toad.game.GameState;
import toad.game.Main;

public class GameWindow {

	ArrayList<GameButton> buttons = new ArrayList<>();

	static Font bodyFont = new Font("Comic sans ms", Font.PLAIN, 4);

	public int w, h;
	public int x, y;

	private Rectangle bounds;

	String title = "Sample text", body = "Sample text";
	String buttonName;

	public boolean active;

	Color colour = new Color(0, 0, 0, 200);
	Color tipColor = new Color(42, 42, 42, 200);

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
		this.x = (GameState.gameWidth() / 2) - w / 2;
		this.y = (GameState.gameHeight() / 2) - h / 2;

		if (buttonName == null) return;
		buttons.add(new GameButton(x + (w / 2) - (GameButton.stdWidth / 2), y + h - GameButton.stdHeight, buttonName,
				bodyFont) {
			public void onClick() {
				deactivate();
			}
		});
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

		if (buttonName != null) {
			g.setColor(colour);
			g.fillRect(x, y, w, h);

			g.setColor(Color.white);
			g.drawRect(x, y, w, h);

			for (GameButton b : buttons) {
				b.render(g);
			}
		} else {
			x = bounds.x - GameState.camera.x;
			y = bounds.y - GameState.camera.y;

			g.setColor(tipColor);
			g.fillOval(x, y, w, h);
		}
		showText(g);
		g.setFont(InGameUI.standardFont);
	}

	public void showText(Graphics g) {
		g.setColor(Color.white);

		g.drawString(title, x + 1, y + InGameUI.standardFont.getSize() + 1);
		g.setFont(bodyFont);

		// For 'E' dialogs
		if (title.equals("")) {
			g.drawString(body, x, y + 4);
			return;
		}

		int tempy = y + InGameUI.standardFont.getSize() + 1;
		for (String line : body.split("\n"))
			g.drawString(line, x + 1, tempy += g.getFontMetrics().getHeight());
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public void setBounds(Rectangle bounds) {
		this.bounds = bounds;
	}
}
