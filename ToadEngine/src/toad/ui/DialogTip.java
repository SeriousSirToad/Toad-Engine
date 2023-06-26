package toad.ui;

import toad.game.GameState;
import toad.game.Main;

import java.awt.*;

import static toad.ui.GameWindow.bodyFont;

public class DialogTip{

    private String text;
    private final Color tipColor;
    private int x, y, w, h;
    private Rectangle bounds;

    public boolean active;

    public DialogTip(String text, Rectangle bounds, int w, int h) {
        tipColor = new Color(42, 42, 42, 200);
        this.w = w;
        this.h = h;
        this.bounds = bounds;
        this.text = text;
    }

    public void showText() {
        // For 'E' dialogs
        Main.g.setColor(Color.white);
        Main.g.setFont(bodyFont);
        Main.g.drawString(text, x, y + 4);
        Main.g.setFont(InGameUI.standardFont);
    }

    public void update() {
        x = bounds.x - GameState.camera.x;
        y = bounds.y - GameState.camera.y;
        if (active) show();
    }

    private void show() {
        Main.g.setColor(tipColor);
        Main.g.fillOval(x, y, w, h);
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
