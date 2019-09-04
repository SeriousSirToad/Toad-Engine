package toad.game.level.interior;

import toad.game.entities.npcs.NPC_generic;
import toad.game.level.Level;
import toad.gfx.Assets;

public class int_generic extends Level{

	public int_generic() {
		super("/images/interior/int_00.png");
		interior = true;
	}

	@Override
	public void init() {
		new NPC_generic(this, 10, 0, Assets.npc1, 2, new String[] {"shopping be like"});
	}

}
