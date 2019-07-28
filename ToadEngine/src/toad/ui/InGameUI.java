package toad.ui;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.util.ArrayList;
import toad.game.Main;

public class InGameUI {

	private static ArrayList<GameWindow> windows = new ArrayList<>();
	private static ArrayList<String> strings = new ArrayList<>();
	static int totalStringWidth = 0;

	public static Font standardFont = new Font("Comic sans ms", Font.PLAIN, 5);
	static FontMetrics fm = Main.g.getFontMetrics(standardFont);

	public static void render(int type, Graphics g) {
		if (type == 0) {
			for (GameWindow w : windows) {
				w.update();
			}
		}
		if (type == 1) {
			totalStringWidth = 0;
			for (int i = 0; i < strings.size(); i++) {
				totalStringWidth += 3 + (strings.get(i).length() * fm.charWidth(0));
				g.drawString(strings.get(i), totalStringWidth - strings.get(i).length() * fm.charWidth(0) - 3, standardFont.getSize());
			}
			g.drawString("" + strings.size(), 0, 50);
		}
	}

	public static void addToRendOrder(GameWindow window) {
		windows.add(window);
	}

	public static void addToRendOrder(String s) {
		strings.add(s);
	}

	public static void replaceRenderOrder(String s, String s2) {
		strings.set(strings.indexOf(s), s2);
	}

	public static void removeFromRendOrder(GameWindow window) {
		windows.remove(window);
	}

	public static void removeFromRendOrder(String s) {
		strings.remove(s);
	}

}
