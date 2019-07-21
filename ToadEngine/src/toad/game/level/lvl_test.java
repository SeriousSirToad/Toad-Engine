package toad.game.level;

import toad.game.entities.flora.Tree;

public class lvl_test extends Level {


	public lvl_test() {
		super("res/level/lvl_test.png");
	}

	public void init() {
		new Tree(this, 500, 0, 1);
	}

}
