package toad.io;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import toad.game.Main;

public class InputHandler implements KeyListener, MouseListener, MouseMotionListener {

	public int MouseX = 0;
	public int MouseY = 0;
	private boolean clicking = false;

	public InputHandler(Main m) {

		m.addKeyListener(this);
		m.addMouseListener(this);
		m.addMouseMotionListener(this);

	}

	public class Key {

		private boolean isPressed = false;

		public boolean isPressed() {

			return isPressed;

		}

		public void toggle(boolean b) {

			isPressed = b;

		}

	}

	public Key up = new Key();
	public Key left = new Key();
	public Key down = new Key();
	public Key right = new Key();
	public Key shift = new Key();
	public Key R = new Key();
	public Key E = new Key();
	public Key F = new Key();
	public Key W = new Key();
	public Key A = new Key();
	public Key S = new Key();
	public Key D = new Key();
	public Key esc = new Key();
	public Key space = new Key();
	public Key NUM_1 = new Key();
	public Key NUM_2 = new Key();
	public Key NUM_3 = new Key();

	@Override
	public void keyPressed(KeyEvent e) {

		if (e.getKeyCode() == KeyEvent.VK_SHIFT) {

			shift.toggle(true);

		}

		if (e.getKeyCode() == KeyEvent.VK_R) {

			R.toggle(true);

		}

		if (e.getKeyCode() == KeyEvent.VK_F) {

			F.toggle(true);

		}

		if (e.getKeyCode() == KeyEvent.VK_E) {

			E.toggle(true);

		}

		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {

			esc.toggle(true);

		}

		if (e.getKeyCode() == KeyEvent.VK_1) {

			NUM_1.toggle(true);

		}

		if (e.getKeyCode() == KeyEvent.VK_2) {

			NUM_2.toggle(true);

		}

		if (e.getKeyCode() == KeyEvent.VK_3) {

			NUM_3.toggle(true);

		}

		if (e.getKeyCode() == KeyEvent.VK_SPACE) {

			space.toggle(true);

		}

		if (e.getKeyCode() == KeyEvent.VK_W) {

			W.toggle(true);

		}

		if (e.getKeyCode() == KeyEvent.VK_A) {

			A.toggle(true);

		}

		if (e.getKeyCode() == KeyEvent.VK_S) {

			S.toggle(true);

		}

		if (e.getKeyCode() == KeyEvent.VK_D) {

			D.toggle(true);

		}

		if (e.getKeyCode() == KeyEvent.VK_UP) {

			up.toggle(true);

		}

		if (e.getKeyCode() == KeyEvent.VK_LEFT) {

			left.toggle(true);

		}

		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {

			right.toggle(true);

		}

		if (e.getKeyCode() == KeyEvent.VK_DOWN) {

			down.toggle(true);

		}

	}

	@Override
	public void keyReleased(KeyEvent e) {

		if (e.getKeyCode() == KeyEvent.VK_F) {

			F.toggle(false);

		}

		if (e.getKeyCode() == KeyEvent.VK_E) {

			E.toggle(false);

		}

		if (e.getKeyCode() == KeyEvent.VK_SHIFT) {

			shift.toggle(false);

		}

		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {

			esc.toggle(false);

		}

		if (e.getKeyCode() == KeyEvent.VK_R) {

			R.toggle(false);

		}

		if (e.getKeyCode() == KeyEvent.VK_1) {

			NUM_1.toggle(false);

		}

		if (e.getKeyCode() == KeyEvent.VK_2) {

			NUM_2.toggle(false);

		}

		if (e.getKeyCode() == KeyEvent.VK_3) {

			NUM_3.toggle(false);

		}

		if (e.getKeyCode() == KeyEvent.VK_SPACE) {

			space.toggle(false);

		}

		if (e.getKeyCode() == KeyEvent.VK_W) {

			W.toggle(false);

		}

		if (e.getKeyCode() == KeyEvent.VK_A) {

			A.toggle(false);

		}

		if (e.getKeyCode() == KeyEvent.VK_S) {

			S.toggle(false);

		}

		if (e.getKeyCode() == KeyEvent.VK_D) {

			D.toggle(false);

		}

		if (e.getKeyCode() == KeyEvent.VK_UP) {

			up.toggle(false);

		}

		if (e.getKeyCode() == KeyEvent.VK_LEFT) {

			left.toggle(false);

		}

		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {

			right.toggle(false);

		}

		if (e.getKeyCode() == KeyEvent.VK_DOWN) {

			down.toggle(false);

		}

	}

	@Override
	public void keyTyped(KeyEvent arg0) {

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		MouseX = e.getX();
		MouseY = e.getY();
	}

	@Override
	public void mouseMoved(MouseEvent e) {

		MouseX = e.getX();
		MouseY = e.getY();

	}

	@Override
	public void mouseClicked(MouseEvent arg0) {

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {

	}

	@Override
	public void mouseExited(MouseEvent arg0) {

	}

	@Override
	public void mousePressed(MouseEvent arg0) {

		clicking = true;

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {

		clicking = false;

	}

	public boolean clicking() {
		return clicking;
	}

}
