package toad.game.entities.buildings;

import toad.game.level.Level;
import toad.game.level.interior.int_generic;
import toad.gfx.Assets;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Popo extends Building {
    public Popo(Level level, int x, int y) {
        super(level, x, y, Assets.popostation, new int_generic());

        int xOffset = 8;
        int yOffset = 16;
        makeStandardDoor(32, 0, new Point(80, 95), new Point(x + (w / 2) - xOffset, y + h - yOffset));
    }
}
