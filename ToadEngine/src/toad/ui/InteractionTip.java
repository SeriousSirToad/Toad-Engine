package toad.ui;

import toad.game.GameState;
import toad.game.Main;

import java.awt.*;

import static toad.ui.GameWindow.bodyFont;

public class InteractionTip {

	private String text;
	private final Color tipColor = new Color(42, 200, 25, 255);
	private int initX, initY, currentX, currentY, w, h;

	public boolean active;

	public InteractionTip(String text, int x, int y, int w, int h) {
		this.w = w;
		this.h = h;
		initX = x;
		initY = y;
		currentX = initX;
		currentY = initY;
		this.text = text;
	}

	public void showText() {
		// For 'E' dialogs
		Main.g.setColor(Color.white);
		Main.g.setFont(bodyFont);
		Main.g.drawString(text, currentX + 2, currentY + 4);
		Main.g.setFont(InGameUI.standardFont);
	}

	public void update() {
		if (active) {
			currentX = initX - GameState.camera.x;
			currentY = initY - GameState.camera.y;
			show();
		}
	}

	private void show() {
		Main.g.setColor(tipColor);
		Main.g.fillOval(currentX, currentY, w, h);
		showText();
	}

	public void activate() {
		active = true;
		InGameUI.addToRendOrder(this);
	}

	public void deactivate() {
		active = false;
		InGameUI.removeFromRendOrder(this);
	}
}
