package toad.game.entities.flora;

import toad.game.level.Level;
import toad.gfx.Assets;

public class Tree extends Plant {

	public int type;

	public Tree(Level level, int x, int y, int type) {
		super(level, x, y, Assets.getTree(type), null);
		this.type = type;
		isSolid = true;
	}

}
