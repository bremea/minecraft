import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Keyboard extends KeyAdapter {
	InputManager inputManager;

	Keyboard(InputManager inputManager) {
		this.inputManager = inputManager;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		inputManager.addKeyPressed(e.getKeyCode());
	}

	@Override
	public void keyReleased(KeyEvent e) {
		inputManager.removeKeyPressed(e.getKeyCode());
	}
}
