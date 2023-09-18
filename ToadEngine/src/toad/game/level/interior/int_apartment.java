package toad.game.level.interior;

import java.awt.Rectangle;

import toad.game.Main;
import toad.game.entities.ActionZone;
import toad.game.entities.objects.Bed;
import toad.game.level.Level;
import toad.ui.InteractionTip;
import toad.ui.GameWindow;

public class int_apartment extends Level {

	// 7-8-23 Set interior to true
	public int_apartment() {
		super("/images/interior/int_apartment.png");
		interior = true;
	}

	@Override
	public void init() {
		ActionZone a = new ActionZone(
				this, new Rectangle(112, 47, 64, 54), new GameWindow("Bed", "", 100, 80, "Sleep"), Main.input.E
		);
		a.setInteractionTip(new InteractionTip("E", 160, 47, 6, 6));
		new Bed(this, 128, 52);
		shader.addLight(5, 3, 17);
	}
}
