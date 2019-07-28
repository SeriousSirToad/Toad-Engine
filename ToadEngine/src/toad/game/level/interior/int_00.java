package toad.game.level.interior;

import toad.game.entities.npcs.NPC_generic;
import toad.game.level.Level;
import toad.gfx.Assets;

public class int_00 extends Level{

	public int_00() {
		super("src/res/interior/int_00.png");
	}

	@Override
	public void init() {
		new NPC_generic(this, 0, 0, Assets.player, "...");
	}

}
