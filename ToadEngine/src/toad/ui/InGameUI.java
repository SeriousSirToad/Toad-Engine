package toad.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.util.ArrayList;

import toad.game.Main;

public class InGameUI {

	private static ArrayList<GameWindow> windows = new ArrayList<>();
	private static ArrayList<InteractionTip> tips = new ArrayList<>();
	private static ArrayList<String> strings = new ArrayList<>();
	static int totalStringWidth = 0;

	public static Font standardFont = new Font("Comic sans ms", Font.PLAIN, 5);
	static FontMetrics fm;

	public static void render(int type, Graphics g) {
		if (fm == null)
			fm = Main.g.getFontMetrics(standardFont);
		if (type == 0) {
			for (int i = 0; i < windows.size(); i++) {
				GameWindow w = windows.get(i);
				w.update();
			}
			for (int i = 0; i < tips.size(); i++) {
				InteractionTip tip = tips.get(i);
				tip.update();
			}
		}
		if (type == 1) {
			g.setColor(Color.green);
			totalStringWidth = 0;
			for (int i = 0; i < strings.size(); i++) {
				totalStringWidth += 3 + (strings.get(i).length() * fm.charWidth(0));
				g.drawString(strings.get(i), totalStringWidth - strings.get(i).length() * fm.charWidth(0) - 3,
						standardFont.getSize());
			}
		}
	}

	public static void addToRendOrder(GameWindow window) {
		windows.add(window);
	}

	public static void addToRendOrder(InteractionTip interactionTip) {
		tips.add(interactionTip);
	}

	public static int addToRendOrder(String s) {
		strings.add(s);
		return strings.indexOf(s);
	}

	public static void clearRenderOrder() {
		windows.clear();
		tips.clear();
	}

	public static void replaceRenderOrder(String s, String s2) {
		if (!strings.contains(s)) {
			strings.add(s);
			strings.set(strings.indexOf(s), s2);
		} else {
			strings.set(strings.indexOf(s), s2);
		}
	}

	public static void removeFromRendOrder(GameWindow window) {
		windows.remove(window);
	}

	public static void removeFromRendOrder(InteractionTip interactionTip) {
		tips.remove(interactionTip);
	}

	public static void removeFromRendOrder(String s) {
		strings.remove(s);
	}

	public boolean active() {
		return windows.size() > 0;
	}

}
