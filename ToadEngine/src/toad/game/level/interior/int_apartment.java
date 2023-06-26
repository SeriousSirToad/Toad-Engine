package toad.game.level.interior;

import java.awt.Rectangle;

import toad.game.Main;
import toad.game.entities.ActionZone;
import toad.game.entities.objects.Bed;
import toad.game.level.Level;
import toad.ui.DialogTip;
import toad.ui.GameWindow;

public class int_apartment extends Level {

	public int_apartment() {
		super("/images/interior/int_apartment.png");
	}

	@Override
	public void init() {
		ActionZone a = new ActionZone(
				this, new Rectangle(112, 47, 64, 54), new GameWindow("Bed", "", 100, 80, "Sleep"), Main.input.E
		);
		a.setDialogTip(new DialogTip("'E'", new Rectangle(160, 47, 64, 54), 5, 5));
		new Bed(this, 128, 52);
	}

}
